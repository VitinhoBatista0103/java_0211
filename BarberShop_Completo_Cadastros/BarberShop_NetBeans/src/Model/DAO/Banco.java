package Model.DAO;

import Model.*;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    public static final List<Usuario> usuarios = new ArrayList<>();
    public static final List<Cliente> clientes = new ArrayList<>();
    public static final List<Servico> servicos = new ArrayList<>();
    public static final List<Agendamento> agendamentos = new ArrayList<>();

    static {
        // Acesso inicial para testar o sistema: admin / 1234
        usuarios.add(new Usuario(1, "admin", "1234", "Administrador", 'M',
                (java.util.Date) null, "", "", ""));
        clientes.add(new Cliente(1, "João Silva", 'M', "(48) 99999-0000", "Rua Central"));
        clientes.add(new Cliente(2, "Maria Souza", 'F', "(48) 98888-0000", "Rua das Flores"));
        servicos.add(new Servico(1, "Corte de cabelo", 35.0f));
        servicos.add(new Servico(2, "Barba", 20.0f));
        servicos.add(new Servico(3, "Corte + barba", 50.0f));
    }

    private Banco() { }
}
