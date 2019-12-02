package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Principal;
import modelo.Produto;
import modelo.Queries;
import modelo.Vendedor;
import utilitarios.ConnectionFactory;

public class ControlCadastrarVenda 
{
    private Produto produtoSelecionado = null;
    private Vendedor vendedorSelecionado = null;
    private Cliente clienteSelecionado = null;
    private Integer quantidadeSelecionada = null;
    private Map<Produto, Integer> produtos = new HashMap<>();
    
    public ControlCadastrarVenda() { }
    
    public void setVendedor(JTextField txNomeVendedor)
    {
        vendedorSelecionado = Principal.getSessao().getVendedor();
        txNomeVendedor.setText(vendedorSelecionado.getNome());
    }
    
    public Cliente encontrarCliente(JTextField txCpf)
    {
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        Cliente cliente = null;
        
        try
        {
            connection = ConnectionFactory.getConnectionFactory();
            stmt = connection.createStatement(); 
            
            String query = Queries.clienteDoCpf(txCpf.getText());
            resultSet = stmt.executeQuery(query);
            
            if(resultSet.next())
            {
                cliente = new Cliente(
                    resultSet.getString("nome"),
                    resultSet.getString("email"),
                    resultSet.getString("cpf")
                );
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            ConnectionFactory.closeConnection(connection, stmt, resultSet);
        }
        
        clienteSelecionado = cliente;
        return cliente;
    }
    
    public void setCliente(Cliente cliente, JTextField txNome, JTextField txEmail)
    {
        if(cliente != null)
        {
            txNome.setText(cliente.getNome());
            txEmail.setText(cliente.getEmail());
        }
        else
        {
            txNome.setText("Nome");
            txEmail.setText("Email");
        }
    }
    
    public Produto encontrarProduto(JTextField txCodigo)
    {
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        Produto produto = null;
        
        try
        {
            connection = ConnectionFactory.getConnectionFactory();
            stmt = connection.createStatement(); 
            
            String query = Queries.produtoDoId(txCodigo.getText());
            resultSet = stmt.executeQuery(query);
            
            if(resultSet.next())
            {
                produto = new Produto(
                    Integer.toString(resultSet.getInt("id")),
                    resultSet.getString("marca"),
                    resultSet.getString("modelo"),
                    resultSet.getFloat("valor")
                );
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            ConnectionFactory.closeConnection(connection, stmt, resultSet);
        }
        
        produtoSelecionado = produto;
        return produto;
    }
    
    public void setProduto(Produto produto, JTextField txMarca, JTextField txModelo, JTextField txValor, JTextField txSubTotal, JTextField txQtd)
    {
        if(produto != null)
        {
            txMarca.setText(produto.getMarca());
            txModelo.setText(produto.getModelo());
            txValor.setText(String.format("%.2f", produto.getValor()));
        }
        else
        {
            txMarca.setText("Marca");
            txModelo.setText("Modelo");
            txValor.setText("0,00");
            txSubTotal.setText("0,00");
        }
        txQtd.setText("1");
        atualizarSubTotal(txQtd, txValor, txSubTotal);
    }
    
    public void atualizarSubTotal(JTextField txQtd, JTextField txValor, JTextField txSubTotal)
    {
        String subTotalText = "0,00";
        Integer qtd = 1;
        try
        {
            qtd = Integer.parseInt(txQtd.getText());
            if(qtd <= 0)
                qtd = 1;
            Float valor = Float.parseFloat(txValor.getText().replace(',', '.'));
            System.out.println(qtd + " " + valor);
            subTotalText = String.format("%.2f", qtd * valor);
        }
        catch(NumberFormatException e)
        { }
        finally
        {
            txQtd.setText(Integer.toString(qtd));
            txSubTotal.setText(subTotalText);
            quantidadeSelecionada = qtd;
        }       
    }
    
    public void atualizarCarrinho(JTable tabela, JTextField txTotal)
    {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0); // Reset Rows
        double total = 0;
        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) 
        {
            Produto p = entry.getKey();
            Integer qtd = entry.getValue();
            model.addRow(new Object[]
            {
                p.getCodigo(),
                p.getMarca(),
                p.getModelo(),
                qtd,
                p.getValor(),
                String.format("%.2f",p.getValor() * qtd)
            });
            total += qtd * p.getValor();
        }
        txTotal.setText(String.format("%.2f", total));
    }
    
    public void adicionarProduto()
    {
        Produto p = produtoSelecionado;
        if (produtos.containsKey(p))
        {
            for (Produto key : produtos.keySet()) 
            {
                if (p.equals(key)) 
                {
                    produtos.put(key, quantidadeSelecionada);
                }
            }
        }
        else
        {
            produtos.put(p, quantidadeSelecionada);
        }
    }
    
    public void removeProduto()
    {
      
    }
}
