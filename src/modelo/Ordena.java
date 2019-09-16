package modelo;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabri
 */
public class Ordena 
{
    public static void insertionSort(List<Cliente> clientes)
    {
        int i, j;
        Cliente chave;
        
        for (i = 1; i < clientes.size(); i++) 
        {
            chave = clientes.get(i);
            j = i - 1;
            //clientes.get(j).getNome().compareTo(chave.getNome())
            while(j >= 0 && chave.compareWith(clientes.get(j)) > 0)
            {
                clientes.set(j + 1, clientes.get(j));
                j = j - 1;
            }
            clientes.set(j + 1, chave);
        }
    }
}
