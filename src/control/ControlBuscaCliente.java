package control;

import interfaces.Leitor.ILeitor;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modelo.Cliente;
import utilitarios.ListaFuncs;
import modelo.Sessao;

public class ControlBuscaCliente 
{
    Sessao sessao;
    ILeitor leitor;
    
    public ControlBuscaCliente()
    {
        
    }
    
    public void changeInputBusca(JTextField txInput, String text)
    {
        txInput.setText(text);
    }

    public void setLeitor(ILeitor leitor) {
        this.leitor = leitor;
    }
    
    public void buscaClientes(JTextArea txResult)
    {
        List<Map> registros = leitor.readAll();
        List<Cliente> clientes = ListaFuncs.generateClientsByMap(registros);
        
        String resultado = "";
        for (Cliente cliente : clientes) 
        {
            resultado += cliente.toString() + '\n';
        }
        
        System.out.println(clientes);
        txResult.setText(resultado);
    }
    
    public void buscaClientes(JTable txTable, String item)
    {
        
    }
    
    /*public Cliente[] buscaClientesTabela(JTable tbResult)
    {
        List<Map> lista = this.sessao.getLeitor().readAll();
        Cliente cliente[] = new Cliente[lista.size()];
        
    }*/
}
