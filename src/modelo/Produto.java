package modelo;

public class Produto {
    private Integer codigo;
    private String marca;
    private String modelo;
    private float valor;
     
    public Produto() {
         
    }
     
    public Produto(Integer codigo, String marca, String modelo, float valor) {
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

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;
        
        Produto p = (Produto) obj;
        System.out.println(marca.equals(p.getMarca()) && modelo.equals(p.getModelo()));
        return marca.equals(p.getMarca()) && modelo.equals(p.getModelo());
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
        
        return result;
  }
}
