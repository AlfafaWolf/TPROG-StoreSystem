package utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory 
{
    static final String DB_URL = "jdbc:mysql://localhost:3307/db-tap";
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "usbw";
    
    public static Connection getConnectionFactory() throws SQLException
    {
        try
        {
            Class.forName(DB_DRV);
            return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
        }
        catch(ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public static void closeConnection(Connection con)
    {
        closeConnection(con, null, null, null);
    }
    
    public static void closeConnection(Connection con, PreparedStatement pstmt)
    {
        closeConnection(con, pstmt, null, null);
    }
    
    public static void closeConnection(Connection con, Statement stmt)
    {
        closeConnection(con, null , stmt, null);
    }
    
    public static void closeConnection(Connection con, PreparedStatement pstmt, ResultSet set)
    {
        closeConnection(con, pstmt , null, set);
    }
    
    public static void closeConnection(Connection con, Statement stmt, ResultSet set)
    {
        closeConnection(con, null , stmt, set);
    }
    
    private static void closeConnection(Connection con, PreparedStatement pstmt, Statement stmt, ResultSet set)
    {
        try
        {
            if(con != null)
                con.close();
            if(stmt != null)
                stmt.close();
            if(pstmt != null)
                pstmt.close();
            if(set != null)
                set.close();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
