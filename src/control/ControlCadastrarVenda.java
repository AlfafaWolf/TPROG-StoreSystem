package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Item;
import modelo.Principal;
import modelo.Produto;
import modelo.Queries;
import modelo.Venda;
import modelo.Vendedor;
import modelo.dao.ItemDAO;
import modelo.dao.VendaDAO;
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
                    resultSet.getInt("id"),
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
        System.out.println(cliente);
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
                    resultSet.getInt("id"),
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
    
    public void atualizarViewCarrinho(JTable tabela, JTextField txTotal)
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
    
    public void adicionarProduto(JTable tabela, JTextField txTotal, JLabel txErroProduto)
    {
        if (produtoSelecionado == null) 
        {
           txErroProduto.setVisible(true); 
           return;
        } 
        else 
            txErroProduto.setVisible(false);
        
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
        atualizarViewCarrinho(tabela, txTotal);
    }
    
    public void removeProduto(JTable tabela, JTextField txTotal)
    {
        if(produtos.containsKey(produtoSelecionado))
        {
            produtos.remove(produtoSelecionado);
        }
        atualizarViewCarrinho(tabela, txTotal);
    }
    
    public void finalizarCompra(JTextField txNome, JTextField txEmail, JTextField txCpf, 
            JTextField txCodigo, JTextField txModelo, JTextField txMarca, 
            JTextField txValor, JTextField txSubTotal, JTextField txQtd,
            JTable tabela, JTextField txTotal, JLabel txErroCliente, JLabel txErroCarrinho, JLabel txErroProduto)
    {
        if (!autenticarVenda(txErroCliente, txErroCarrinho)) return;
        
        Venda venda = new Venda(clienteSelecionado, vendedorSelecionado);
        System.out.println(venda);
        VendaDAO vendaDao = new VendaDAO();
        Integer idInserted = vendaDao.save(venda);
        if(idInserted != null)
        {
            venda.setId(idInserted);
            
            ArrayList<Item> itens = new ArrayList<>();
            for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) 
            {
                itens.add(new Item(
                        venda, 
                        entry.getKey(), 
                        entry.getKey().getValor(), 
                        entry.getValue()
                ));
            }
            
            ItemDAO itemDAO = new ItemDAO();
            
            for(Item item : itens)
            {
                itemDAO.save(item);
            }
            
            produtos.clear();
            atualizarViewCarrinho(tabela, txTotal);
            limparTela(txNome, txEmail, txCpf, txCodigo, txModelo, txMarca, txValor, txSubTotal, txQtd, txErroCliente, txErroCarrinho, txErroProduto);
            JOptionPane.showMessageDialog(null, "Venda Realizada com Sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
        }  
        else
        {
            System.out.println("ERRO: Não foi possivel inserir venda");
            JOptionPane.showMessageDialog(null, "Não foi possivel inserir a venda!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean autenticarVenda(JLabel txErroCliente, JLabel txErroCarrinho)
    {
        boolean temCliente = true;
        boolean temProduto = true;
                
        if(clienteSelecionado == null)
        {
            temCliente = false;
        }
        if(produtos.isEmpty())
        {
            temProduto = false;
        }
        
        txErroCarrinho.setVisible(!temProduto);
        txErroCliente.setVisible(!temCliente);
        
        return temCliente && temProduto;
    }
    
    public void limparTela(JTextField txNome, JTextField txEmail, JTextField txCpf, 
            JTextField txCodigo, JTextField txModelo, JTextField txMarca, 
            JTextField txValor, JTextField txSubTotal, JTextField txQtd,
            JLabel txErroCliente, JLabel txErroCarrinho, JLabel txErroProduto)
    {
        setCliente(null, txNome, txEmail);
        setProduto(null, txMarca, txModelo, txValor, txSubTotal, txQtd);
        txCodigo.setText("");
        txErroCliente.setVisible(false);
        txErroCarrinho.setVisible(false);
        txErroProduto.setVisible(false);
    }
}
