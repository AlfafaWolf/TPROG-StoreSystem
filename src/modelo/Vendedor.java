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
    private String matricula;
    private String nome;
 
    public Vendedor() {
         
    }
     
    public Vendedor(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
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
