package View;

import Model.Servico;
import Model.DAO.ServicoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CadastroServico extends JFrame {
    private final ServicoDAO dao=new ServicoDAO();
    private final JTextField descricao=new JTextField(25), valor=new JTextField(10);
    private final DefaultTableModel modelo=new DefaultTableModel(new Object[]{"ID","Descrição","Valor (R$)"},0){@Override public boolean isCellEditable(int r,int c){return false;}};
    private final JTable tabela=new JTable(modelo);
    public CadastroServico(){setTitle("Cadastro de Serviços - BarberShop");setSize(750,500);setLocationRelativeTo(null);setDefaultCloseOperation(DISPOSE_ON_CLOSE);setLayout(new BorderLayout(10,10));
        JPanel form=new JPanel(new GridLayout(2,2,8,8));form.setBorder(BorderFactory.createTitledBorder("Dados do serviço"));form.add(new JLabel("Descrição"));form.add(descricao);form.add(new JLabel("Valor (R$)"));form.add(valor);
        JButton salvar=new JButton("Cadastrar"), editar=new JButton("Editar selecionado"), excluir=new JButton("Excluir selecionado"), limpar=new JButton("Limpar");JPanel botoes=new JPanel();botoes.add(salvar);botoes.add(editar);botoes.add(excluir);botoes.add(limpar);
        JPanel topo=new JPanel(new BorderLayout());topo.add(form,BorderLayout.CENTER);topo.add(botoes,BorderLayout.SOUTH);add(topo,BorderLayout.NORTH);tabela.setRowHeight(24);add(new JScrollPane(tabela),BorderLayout.CENTER);
        salvar.addActionListener(e->gravar(false));editar.addActionListener(e->editar());excluir.addActionListener(e->excluir());limpar.addActionListener(e->limpar());atualizar();}
    private void gravar(boolean alteracao){try{String d=descricao.getText().trim();if(d.isEmpty()){JOptionPane.showMessageDialog(this,"Informe a descrição.");return;}float v=Float.parseFloat(valor.getText().trim().replace(",","."));if(v<0)throw new NumberFormatException();int id=alteracao?(int)modelo.getValueAt(tabela.getSelectedRow(),0):dao.proximoId();Servico s=new Servico(id,d,v);if(alteracao)dao.update(s);else dao.insert(s);atualizar();limpar();JOptionPane.showMessageDialog(this,alteracao?"Serviço atualizado!":"Serviço cadastrado!");}catch(NumberFormatException ex){JOptionPane.showMessageDialog(this,"Informe um valor numérico válido.");}}
    private void editar(){int r=tabela.getSelectedRow();if(r<0){JOptionPane.showMessageDialog(this,"Selecione um serviço.");return;}descricao.setText(String.valueOf(modelo.getValueAt(r,1)));valor.setText(String.valueOf(modelo.getValueAt(r,2)));if(JOptionPane.showConfirmDialog(this,"Altere os campos e confirme para salvar.","Editar serviço",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)gravar(true);}
    private void excluir(){int r=tabela.getSelectedRow();if(r<0){JOptionPane.showMessageDialog(this,"Selecione um serviço.");return;}int id=(int)modelo.getValueAt(r,0);if(JOptionPane.showConfirmDialog(this,"Excluir este serviço?","Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){dao.delete(id);atualizar();}}
    private void atualizar(){modelo.setRowCount(0);for(Servico s:dao.selectAll())modelo.addRow(new Object[]{s.getId(),s.getDescricao(),String.format(java.util.Locale.forLanguageTag("pt-BR"),"%.2f",s.getValor())});}
    private void limpar(){descricao.setText("");valor.setText("");}
}
