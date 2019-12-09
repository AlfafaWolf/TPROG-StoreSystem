/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author gabri
 */
public class Vendedor 
{
    private Integer id;
    private String matricula;
    private String nome;
 
    public Vendedor() {
         
    }
     
    public Vendedor(Integer id, String matricula, String nome) {
        
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getMatricula() {
        return matricula;
    }
 
    public String getNome() {
        return nome;
    }
 
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s\nMatricula: %s", nome, matricula);
    }
}
