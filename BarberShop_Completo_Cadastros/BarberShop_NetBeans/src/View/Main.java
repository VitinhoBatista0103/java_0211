package View;

import Model.*;
import javax.swing.SwingUtilities;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        try {
            Cliente cliente = new Cliente(10, "João Silva", 'M', "15/05/2000",
                    "(48) 99999-0000", "joao@email.com", "1234567", "Rua Central", "88140-000");
            Servico servico = new Servico(1, "Corte de cabelo", 35.0f);
            Usuario usuario = new Usuario(10, "Carlos", "1234", "Administrador", 'M',
                    "20/08/1990", "(48) 98888-0000", "carlos@email.com", "7654321");
            Agendamento agendamento = new Agendamento(1, cliente, servico, 35.0f,
                    "02/11/2026", "14:30", "Agendamento de teste");

            System.out.println("=== TESTE DE MODELS, HERANÇA E DATAS ===");
            System.out.println("Cliente: " + cliente.getNome() + " | Nascimento: "
                    + new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));
            System.out.println("Serviço: " + servico.getDescricao() + " | Valor: R$ " + servico.getValor());
            System.out.println("Usuário: " + usuario.getNome() + " | Acesso: " + usuario.getNivelAcesso());
            System.out.println("Agendamento: " + agendamento.getCliente().getNome() + " | Data/hora: "
                    + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(agendamento.getData()));

            // Breakpoint sugerido: inspecione cliente, usuario e agendamento nesta linha.
            System.out.println("Breakpoint sugerido: linha seguinte.");

            SwingUtilities.invokeLater(() -> new Login().setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
