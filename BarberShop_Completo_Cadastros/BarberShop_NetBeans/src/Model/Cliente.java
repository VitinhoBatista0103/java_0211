package Model;

import java.text.ParseException;
import java.util.Date;

public class Cliente extends Pessoa {
    private String endereco;
    private String cep;

    public Cliente(int id, String nome, char sexo, String dataNascimento,
                   String telefone, String email, String rg, String endereco, String cep)
            throws ParseException {
        super(id, nome, sexo, dataNascimento, telefone, email, rg);
        this.endereco = endereco;
        this.cep = cep;
    }

    public Cliente(int id, String nome, char sexo, Date dataNascimento,
                   String telefone, String email, String rg, String endereco, String cep) {
        super(id, nome, sexo, dataNascimento, telefone, email, rg);
        this.endereco = endereco;
        this.cep = cep;
    }

    // Construtor mantido para continuar compatível com a tela Agenda já existente.
    public Cliente(int id, String nome, char sexo, String telefone, String endereco) {
        super(id, nome, sexo, (Date) null, telefone, "", "");
        this.endereco = endereco;
        this.cep = "";
    }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    @Override
    public String toString() { return getNome(); }
}
