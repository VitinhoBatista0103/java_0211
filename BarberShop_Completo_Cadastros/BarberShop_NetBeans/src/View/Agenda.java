package View;

import Model.*;
import Model.DAO.AgendamentoDAO;
import Model.DAO.ClienteDAO;
import Model.DAO.ServicoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Agenda extends JFrame {
    private final JComboBox<Cliente> cmbCliente = new JComboBox<>();
    private final JComboBox<Servico> cmbServico = new JComboBox<>();
    private final JTextField txtData = new JTextField();
    private final JTextField txtHora = new JTextField();
    private final JTextField txtValor = new JTextField();
    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Cliente", "Serviço", "Data", "Hora", "Valor"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };

    public Agenda() {
        setTitle("BarberShop - Agenda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 680);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel fundo = new JLabel(carregarImagem("/View/Imagens/fundoagendamento.jpg"));
        fundo.setLayout(null);
        setContentPane(fundo);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(245, 239, 229, 238));
        painel.setBorder(BorderFactory.createLineBorder(new Color(196, 153, 86), 2));
        painel.setBounds(65, 70, 850, 525);
        fundo.add(painel);

        JLabel titulo = new JLabel("AGENDA DE ATENDIMENTOS", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 23));
        titulo.setBounds(180, 15, 490, 35);
        painel.add(titulo);

        adicionarCampo(painel, "Cliente", cmbCliente, 35, 70, 240);
        adicionarCampo(painel, "Serviço", cmbServico, 305, 70, 240);
        adicionarCampo(painel, "Data (dd/mm/aaaa)", txtData, 575, 70, 230);
        adicionarCampo(painel, "Hora (hh:mm)", txtHora, 35, 140, 240);
        adicionarCampo(painel, "Valor (R$)", txtValor, 305, 140, 240);

        for (Cliente cliente : new ClienteDAO().selectAll()) cmbCliente.addItem(cliente);
        for (Servico servico : new ServicoDAO().selectAll()) cmbServico.addItem(servico);

        cmbServico.addActionListener(e -> atualizarValor());
        atualizarValor();

        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(590, 150, 145, 38);
        painel.add(btnAgendar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(745, 150, 80, 38);
        painel.add(btnLimpar);

        JTable tabela = new JTable(modelo);
        tabela.setRowHeight(24);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(25, 220, 800, 275);
        painel.add(scroll);

        btnAgendar.addActionListener(e -> salvarAgendamento());
        btnLimpar.addActionListener(e -> {
            txtData.setText("");
            txtHora.setText("");
            atualizarValor();
        });
    }

    private void salvarAgendamento() {
        Cliente cliente = (Cliente) cmbCliente.getSelectedItem();
        Servico servico = (Servico) cmbServico.getSelectedItem();
        String data = txtData.getText().trim();
        String hora = txtHora.getText().trim();
        String valorTexto = txtValor.getText().trim().replace(",", ".");

        if (cliente == null || servico == null || data.isEmpty() || hora.isEmpty() || valorTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha data, hora e valor e selecione cliente e serviço.");
            return;
        }

        try {
            float valor = Float.parseFloat(valorTexto);
            int id = new AgendamentoDAO().selectAll().size() + 1;
            Agendamento agendamento = new Agendamento(id, cliente, servico, valor, data, hora, "");
            new AgendamentoDAO().insert(agendamento);

            Date dataConvertida = agendamento.getData();
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataConvertida);
            modelo.addRow(new Object[]{cliente.getNome(), servico.getDescricao(), dataFormatada,
                    hora, String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", valor)});
            txtData.setText("");
            txtHora.setText("");
            atualizarValor();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Informe um valor válido.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Data ou hora inválida. Use dd/mm/aaaa e hh:mm.");
        }
    }

    private void atualizarValor() {
        Servico servico = (Servico) cmbServico.getSelectedItem();
        if (servico != null) txtValor.setText(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", servico.getValor()));
    }

    private void adicionarCampo(JPanel painel, String texto, JComponent campo, int x, int y, int largura) {
        JLabel label = new JLabel(texto);
        label.setBounds(x, y, largura, 22);
        painel.add(label);
        campo.setBounds(x, y + 25, largura, 30);
        painel.add(campo);
    }

    private ImageIcon carregarImagem(String caminho) {
        java.net.URL url = getClass().getResource(caminho);
        return url == null ? new ImageIcon() : new ImageIcon(url);
    }
}
