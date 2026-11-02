package Model.DAO;

import Model.Agendamento;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {
    public List<Agendamento> selectAll() { return new ArrayList<>(Banco.agendamentos); }
    public void insert(Agendamento agendamento) { Banco.agendamentos.add(agendamento); }
    public boolean delete(int id) {
        return Banco.agendamentos.removeIf(agendamento -> agendamento.getId() == id);
    }
}
