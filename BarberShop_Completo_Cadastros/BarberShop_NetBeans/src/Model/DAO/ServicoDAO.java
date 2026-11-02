package Model.DAO;

import Model.Servico;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    public List<Servico> selectAll() { return new ArrayList<>(Banco.servicos); }
    public void insert(Servico servico) { Banco.servicos.add(servico); }
    public boolean update(Servico atualizado) {
        for (int i = 0; i < Banco.servicos.size(); i++) {
            if (Banco.servicos.get(i).getId() == atualizado.getId()) {
                Banco.servicos.set(i, atualizado); return true;
            }
        }
        return false;
    }
    public boolean delete(int id) { return Banco.servicos.removeIf(servico -> servico.getId() == id); }
    public int proximoId() { return Banco.servicos.stream().mapToInt(Servico::getId).max().orElse(0) + 1; }
}
