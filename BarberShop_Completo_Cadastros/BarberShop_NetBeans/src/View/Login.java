package View;

import Controller.LoginController;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtSenha = new JPasswordField();
    private final LoginController controller;

    public Login() {
        setTitle("BarberShop - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel fundo = new JLabel(carregarImagem("/View/Imagens/logo1.jpg"));
        fundo.setLayout(null);
        setContentPane(fundo);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(245, 239, 229, 238));
        painel.setBorder(BorderFactory.createLineBorder(new Color(196, 153, 86), 2));
        painel.setBounds(250, 165, 400, 270);
        fundo.add(painel);

        JLabel titulo = new JLabel("LOGIN", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setBounds(70, 18, 260, 40);
        painel.add(titulo);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(45, 85, 100, 28);
        painel.add(lblUsuario);
        txtUsuario.setBounds(140, 85, 210, 30);
        txtUsuario.setName("txtUsuario");
        painel.add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(45, 130, 100, 28);
        painel.add(lblSenha);
        txtSenha.setBounds(140, 130, 210, 30);
        txtSenha.setName("txtSenha");
        painel.add(txtSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(95, 195, 100, 34);
        painel.add(btnEntrar);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(215, 195, 100, 34);
        painel.add(btnSair);

        controller = new LoginController(this);
        btnEntrar.addActionListener(e -> {
            if (txtUsuario.getText().trim().isEmpty() || txtSenha.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Preencha usuário e senha.");
                return;
            }
            controller.entrarNoSistema();
        });
        btnSair.addActionListener(e -> dispose());
        getRootPane().setDefaultButton(btnEntrar);
    }

    private ImageIcon carregarImagem(String caminho) {
        java.net.URL url = getClass().getResource(caminho);
        return url == null ? new ImageIcon() : new ImageIcon(url);
    }

    public JTextField getCampologin() { return txtUsuario; }
    public JPasswordField getCamposenha() { return txtSenha; }
}
