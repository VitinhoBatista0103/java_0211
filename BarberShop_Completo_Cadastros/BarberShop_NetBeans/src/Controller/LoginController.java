package Controller;

import Controller.Helper.LoginHelper;
import Model.Usuario;
import Model.DAO.UsuarioDAO;
import View.Login;
import View.MenuPrincipal;
import javax.swing.JOptionPane;

public class LoginController {
    private final Login view;
    private final LoginHelper helper;

    public LoginController(Login view) {
        this.view = view;
        this.helper = new LoginHelper(view);
    }

    public void entrarNoSistema() {
        Usuario usuario = helper.obterModelo();
        Usuario usuarioAutenticado = new UsuarioDAO().selectPorNomeESenha(usuario);

        if (usuarioAutenticado != null) {
            new MenuPrincipal().setVisible(true);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Usuário ou senha inválidos.");
            helper.limparTela();
            view.getCampologin().requestFocus();
        }
    }
}
