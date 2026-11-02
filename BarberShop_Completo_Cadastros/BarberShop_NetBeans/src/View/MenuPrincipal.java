package View;

import Controller.MenuPrincipalController;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        setTitle("BarberShop - Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel fundo = new JLabel(carregarImagem("/View/Imagens/fundomenu1.jpg"));
        fundo.setLayout(null);
        setContentPane(fundo);

        JMenuBar barra = new JMenuBar();
        JMenu cadastro = new JMenu("Cadastro");
        JMenuItem itemCliente = new JMenuItem("Cliente",
                carregarImagem("/View/Imagens/icons/cliente-icon.png"));
        JMenuItem itemServico = new JMenuItem("Serviço",
                carregarImagem("/View/Imagens/icons/tesoura32-icon.png"));
        cadastro.add(itemCliente);
        cadastro.add(itemServico);

        JMenu operacao = new JMenu("Operação");
        JMenuItem itemAgenda = new JMenuItem("Agenda",
                carregarImagem("/View/Imagens/icons/agenda-icon.png"));
        operacao.add(itemAgenda);
        barra.add(cadastro);
        barra.add(operacao);
        setJMenuBar(barra);

        JLabel mensagem = new JLabel("Bem-vindo ao BarberShop", SwingConstants.CENTER);
        mensagem.setFont(new Font("SansSerif", Font.BOLD, 25));
        mensagem.setForeground(new Color(245, 225, 190));
        mensagem.setBounds(210, 420, 480, 50);
        fundo.add(mensagem);

        MenuPrincipalController controller = new MenuPrincipalController(this);
        itemCliente.addActionListener(e -> new CadastroCliente().setVisible(true));
        itemServico.addActionListener(e -> new CadastroServico().setVisible(true));
        itemAgenda.addActionListener(e -> controller.navegarParaAgenda());
    }

    private ImageIcon carregarImagem(String caminho) {
        java.net.URL url = getClass().getResource(caminho);
        return url == null ? new ImageIcon() : new ImageIcon(url);
    }
}
