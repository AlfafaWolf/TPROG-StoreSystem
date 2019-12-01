package modelo;

import view.TelaBuscaCliente;
import view.TelaLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Principal 
{
    private static Sessao sessao = null;
    
    public static void main(String[] args)
    {   
        init(); 
    }
    
    public static void setSessao(Sessao _sessao)
    {
        sessao = _sessao;
    }
    
    private static void init()
    {
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
        
        while(sessao == null)
        {
            System.out.println("Nao Autenticado");
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Autenticado");
        System.out.println(sessao.vendedor);
        
        tela.dispose();
        
        TelaBuscaCliente telaBusca = new TelaBuscaCliente();
        telaBusca.setVisible(true);
    }
}
