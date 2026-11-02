package Controller.Helper;

import Model.Usuario;
import View.Login;

public class LoginHelper implements IHelper {
    private final Login view;

    public LoginHelper(Login view) { this.view = view; }

    @Override
    public Usuario obterModelo() {
        String nome = view.getCampologin().getText().trim();
        String senha = new String(view.getCamposenha().getPassword());
        return new Usuario(0, nome, senha);
    }

    public void setarModelo(Usuario modelo) {
        view.getCampologin().setText(modelo.getNome());
        view.getCamposenha().setText(modelo.getSenha());
    }

    @Override
    public void limparTela() {
        view.getCampologin().setText("");
        view.getCamposenha().setText("");
    }
}
