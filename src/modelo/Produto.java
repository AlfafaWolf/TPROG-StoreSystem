package modelo;

public class Produto {
    private String marca;
    private String modelo;
    private float valor;
     
    public Produto() {
         
    }
     
     
    public Produto(String marca, String modelo, float valor) {
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
}
