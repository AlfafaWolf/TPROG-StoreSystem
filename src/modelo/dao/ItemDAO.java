package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import modelo.Item;
import utilitarios.ConnectionFactory;

public class ItemDAO 
{
    public Integer save(Item item)
    {
        String query = "INSERT INTO item (id, quantidade, valor_unitario, id_produto, id_venda) VALUES (?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement stmt = null;
        Integer id = null;
        
        try
        {
            connection = ConnectionFactory.getConnectionFactory();
            stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 
            
            System.out.println(item.getProduto().getCodigo());
            
            if(item.getId() == null)
                stmt.setNull(1, Types.INTEGER);
            else
                stmt.setInt(1, item.getId());
            stmt.setInt(2, item.getQuantidade());
            stmt.setFloat(3, item.getValorUnitario());
            stmt.setInt(4, item.getProduto().getCodigo());
            stmt.setInt(5, item.getVenda().getId());
            
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
        
        return id;
    }
}
