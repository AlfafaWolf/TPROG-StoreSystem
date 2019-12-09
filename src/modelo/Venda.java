
package modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Venda 
{
    private Integer id;
    private Date data;
    private Cliente cliente;
    private Vendedor vendedor;
 
    public Venda() {
 
    }
     
    public Venda(Integer id, String data, Cliente cliente, Vendedor vendedor) {
        try
        {
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            this.data = format.parse(data);
        }
        catch(ParseException e)
        {
            System.out.println(e.getMessage());
            this.data = new Date(); 
        }
        
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }
    
    public Venda(Integer id, Cliente cliente, Vendedor vendedor) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }
    
    public Venda(Cliente cliente, Vendedor vendedor) {
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    public void setData(Date data) {
        this.data = data;
    }
 
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
 
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
}
