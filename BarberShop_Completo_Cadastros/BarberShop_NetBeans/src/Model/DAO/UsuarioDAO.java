package Model.DAO;

import Model.Usuario;

public class UsuarioDAO {
    public Usuario selectPorNomeESenha(Usuario usuario) {
        if (usuario == null) return null;
        for (Usuario cadastrado : Banco.usuarios) {
            if (cadastrado.getNome().equals(usuario.getNome())
                    && cadastrado.getSenha().equals(usuario.getSenha())) {
                return cadastrado;
            }
        }
        return null;
    }

    public void insert(Usuario usuario) { Banco.usuarios.add(usuario); }

    public boolean delete(int id) {
        return Banco.usuarios.removeIf(usuario -> usuario.getId() == id);
    }
}
