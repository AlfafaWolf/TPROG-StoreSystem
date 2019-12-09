package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import modelo.Venda;
import utilitarios.ConnectionFactory;

public class VendaDAO 
{
    public Integer save(Venda venda)
    {
        String query = "INSERT INTO venda (id, data_venda, id_vendedor, id_cliente) VALUES (?,?,?,?);";
        Connection connection = null;
        PreparedStatement stmt = null;
        Integer id = null;
        
        try
        {
            connection = ConnectionFactory.getConnectionFactory();
            stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date myDate = new java.util.Date();
            String date = formatter.format(myDate);

            System.out.println(venda.getCliente());
            if(venda.getId() == null)
                stmt.setNull(1, Types.INTEGER);
            else
                stmt.setInt(1, venda.getId());
            stmt.setString(2, date);
            stmt.setInt(3, venda.getVendedor().getId());
            stmt.setInt(4, venda.getCliente().getId());
            
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            ConnectionFactory.closeConnection(connection, stmt);
        }
        
        System.out.println("id_venda: " + id);
        return id;
    }
}
