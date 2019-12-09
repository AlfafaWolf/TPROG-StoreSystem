package control;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import modelo.Cliente;
import utilitarios.ConnectionFactory;
import modelo.Principal;
import modelo.Queries;
import modelo.Sessao;
import modelo.Vendedor;
import javax.swing.JOptionPane;

public class ControlTelaLogin {
    //Path path;
    
    public ControlTelaLogin(Path path){
        //this.path = path;
    }

    public ControlTelaLogin() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean autenticarUsuario(String login, String senha)
    {
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        
        try
        {
            connection = ConnectionFactory.getConnectionFactory();
            stmt = connection.createStatement();

            String query = Queries.loginSenhaVendedor(login, senha);
            resultSet = stmt.executeQuery(query);

            if(resultSet.next())
            {   
                Vendedor vendedor = new Vendedor(
                        resultSet.getInt("id"),
                        resultSet.getString("matricula"),
                        resultSet.getString("nome")
                );
                Sessao sessao = new Sessao(vendedor);
                Principal.setSessao(sessao);
                
                ConnectionFactory.closeConnection(connection, stmt, resultSet);
                return true;
            }
            else
            {
                System.out.println("Vendedor não encontrado.");
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            ConnectionFactory.closeConnection(connection, stmt, resultSet);
        }
        
        return false;
    }
    
    public void erroAutenticacao(JLabel text, boolean autenticado)
    {
        text.setVisible(!autenticado);
    }
}
