package Model.DAO;

import Model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public List<Cliente> selectAll() { return new ArrayList<>(Banco.clientes); }
    public void insert(Cliente cliente) { Banco.clientes.add(cliente); }
    public boolean update(Cliente atualizado) {
        for (int i = 0; i < Banco.clientes.size(); i++) {
            if (Banco.clientes.get(i).getId() == atualizado.getId()) {
                Banco.clientes.set(i, atualizado); return true;
            }
        }
        return false;
    }
    public boolean delete(int id) { return Banco.clientes.removeIf(cliente -> cliente.getId() == id); }
    public int proximoId() { return Banco.clientes.stream().mapToInt(Cliente::getId).max().orElse(0) + 1; }
}
