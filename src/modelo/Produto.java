package modelo;

public class Produto {
    private String codigo;
    private String marca;
    private String modelo;
    private float valor;
     
    public Produto() {
         
    }
     
     
    public Produto(String codigo, String marca, String modelo, float valor) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.valor = valor;
    }
 
    public void setMarca(String marca) {
        this.marca = marca;
    }
 
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
 
    public void setValor(float valor) {
        this.valor = valor;
    }
 
    public String getMarca() {
        return marca;
    }
 
    public String getModelo() {
        return modelo;
    }
 
    public float getValor() {
        return valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;
        
        Produto p = (Produto) obj;
        return codigo.equals(p.getCodigo());
    }
}
