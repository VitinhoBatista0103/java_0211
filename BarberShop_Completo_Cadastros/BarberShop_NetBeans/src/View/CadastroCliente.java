package View;

import Model.Cliente;
import Model.DAO.ClienteDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CadastroCliente extends JFrame {
    private final ClienteDAO dao = new ClienteDAO();
    private final JTextField nome = new JTextField(), nascimento = new JTextField(), telefone = new JTextField();
    private final JTextField email = new JTextField(), rg = new JTextField(), endereco = new JTextField(), cep = new JTextField();
    private final JComboBox<String> sexo = new JComboBox<>(new String[]{"M", "F", "Outro"});
    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Sexo", "Telefone", "E-mail", "Endereço", "CEP"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabela = new JTable(modelo);

    public CadastroCliente() {
        setTitle("Cadastro de Clientes - BarberShop"); setSize(1000, 680); setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); setLayout(new BorderLayout(10,10));
        JPanel formulario = new JPanel(new GridLayout(4,4,8,6)); formulario.setBorder(BorderFactory.createTitledBorder("Dados do cliente"));
        campo(formulario,"Nome",nome); campo(formulario,"Sexo",sexo); campo(formulario,"Nascimento (dd/mm/aaaa)",nascimento); campo(formulario,"Telefone",telefone);
        campo(formulario,"E-mail",email); campo(formulario,"RG",rg); campo(formulario,"Endereço",endereco); campo(formulario,"CEP",cep);
        JButton salvar = new JButton("Cadastrar"); JButton editar = new JButton("Editar selecionado"); JButton excluir = new JButton("Excluir selecionado"); JButton limpar = new JButton("Limpar");
        JPanel botoes = new JPanel(); botoes.add(salvar); botoes.add(editar); botoes.add(excluir); botoes.add(limpar);
        JPanel topo = new JPanel(new BorderLayout()); topo.add(formulario,BorderLayout.CENTER); topo.add(botoes,BorderLayout.SOUTH); add(topo,BorderLayout.NORTH);
        tabela.setRowHeight(24); add(new JScrollPane(tabela),BorderLayout.CENTER);
        salvar.addActionListener(e -> gravar(false)); editar.addActionListener(e -> carregarSelecionado()); excluir.addActionListener(e -> excluir()); limpar.addActionListener(e -> limpar());
        atualizarTabela();
    }
    private void campo(JPanel p,String titulo,JComponent c){ JPanel x=new JPanel(new BorderLayout(3,3)); x.add(new JLabel(titulo),BorderLayout.NORTH); x.add(c,BorderLayout.CENTER); p.add(x); }
    private void gravar(boolean edicao) {
        try {
            if(nome.getText().trim().isEmpty()) { JOptionPane.showMessageDialog(this,"Informe o nome."); return; }
            String data = nascimento.getText().trim(); Date dt = null;
            if(!data.isEmpty()){ SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy"); f.setLenient(false); dt=f.parse(data); }
            int id = edicao ? Integer.parseInt(String.valueOf(modelo.getValueAt(tabela.getSelectedRow(),0))) : dao.proximoId();
            Cliente c = new Cliente(id,nome.getText().trim(),sexo.getSelectedItem().toString().charAt(0),dt,telefone.getText().trim(),email.getText().trim(),rg.getText().trim(),endereco.getText().trim(),cep.getText().trim());
            if(edicao) dao.update(c); else dao.insert(c);
            atualizarTabela(); limpar(); JOptionPane.showMessageDialog(this, edicao?"Cliente atualizado!":"Cliente cadastrado!");
        } catch(ParseException ex){ JOptionPane.showMessageDialog(this,"Data inválida. Use dd/mm/aaaa."); }
          catch(NumberFormatException ex){ JOptionPane.showMessageDialog(this,"Selecione um cliente na tabela."); }
    }
    private void carregarSelecionado(){ int r=tabela.getSelectedRow(); if(r<0){JOptionPane.showMessageDialog(this,"Selecione um cliente.");return;} int id=(int)modelo.getValueAt(r,0); Cliente c=dao.selectAll().stream().filter(x->x.getId()==id).findFirst().orElse(null); if(c==null)return;
        nome.setText(c.getNome()); sexo.setSelectedItem(String.valueOf(c.getSexo())); nascimento.setText(c.getDataNascimento()==null?"":new SimpleDateFormat("dd/MM/yyyy").format(c.getDataNascimento())); telefone.setText(c.getTelefone()); email.setText(c.getEmail()); rg.setText(c.getRg()); endereco.setText(c.getEndereco()); cep.setText(c.getCep());
        int op=JOptionPane.showConfirmDialog(this,"Altere os campos e confirme para salvar.","Editar cliente",JOptionPane.OK_CANCEL_OPTION); if(op==JOptionPane.OK_OPTION) gravar(true);
    }
    private void excluir(){int r=tabela.getSelectedRow();if(r<0){JOptionPane.showMessageDialog(this,"Selecione um cliente.");return;}int id=(int)modelo.getValueAt(r,0);if(JOptionPane.showConfirmDialog(this,"Excluir este cliente?","Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){dao.delete(id);atualizarTabela();}}
    private void atualizarTabela(){modelo.setRowCount(0);for(Cliente c:dao.selectAll())modelo.addRow(new Object[]{c.getId(),c.getNome(),c.getSexo(),c.getTelefone(),c.getEmail(),c.getEndereco(),c.getCep()});}
    private void limpar(){nome.setText("");nascimento.setText("");telefone.setText("");email.setText("");rg.setText("");endereco.setText("");cep.setText("");sexo.setSelectedIndex(0);}
}
