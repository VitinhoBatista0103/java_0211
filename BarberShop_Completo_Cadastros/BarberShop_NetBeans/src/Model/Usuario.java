package Model;

import java.text.ParseException;
import java.util.Date;

public class Usuario extends Pessoa {
    private String senha;
    private String nivelAcesso;

    public Usuario(int id, String nome, String senha, String nivelAcesso, char sexo,
                   String dataNascimento, String telefone, String email, String rg)
            throws ParseException {
        super(id, nome, sexo, dataNascimento, telefone, email, rg);
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario(int id, String nome, String senha, String nivelAcesso, char sexo,
                   Date dataNascimento, String telefone, String email, String rg) {
        super(id, nome, sexo, dataNascimento, telefone, email, rg);
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    // Construtor usado pelo formulário de login.
    public Usuario(int id, String nome, String senha) {
        super(id, nome, ' ', (Date) null, "", "", "");
        this.senha = senha;
        this.nivelAcesso = "Barbeiro";
    }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getNivelAcesso() { return nivelAcesso; }
    public void setNivelAcesso(String nivelAcesso) { this.nivelAcesso = nivelAcesso; }

    @Override
    public String toString() { return getNome(); }
}
