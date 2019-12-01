
package modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Venda 
{
    private Date data;
    private Cliente cliente;
    private Vendedor vendedor;
    private Produto produto;
 
    public Venda() {
 
    }
     
    public Venda(String data, Cliente cliente, Vendedor vendedor, Produto produto) {
        try
        {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.data = format.parse(data);
        }
        catch(ParseException e)
        {
            System.out.println(e.getMessage());
            this.data = new Date(); 
        }
        
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.produto = produto;
    }
 
    public Date getData() {
        return data;
    }
 
    public Cliente getCliente() {
        return cliente;
    }
 
    public Vendedor getVendedor() {
        return vendedor;
    }
 
    public Produto getProduto() {
        return produto;
    }
 
    public void setData(Date data) {
        this.data = data;
    }
 
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
 
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
 
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
