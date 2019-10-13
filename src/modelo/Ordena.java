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
    public static void insertionSort(List<Cliente> clientes, String param, boolean inverted)
    {
        int i, j, delta;
        Cliente chave;
        boolean cmp;
        
        for (i = 1; i < clientes.size(); i++) 
        {
            chave = clientes.get(i);
            j = i - 1;
            
            delta = chave.compareWith(clientes.get(j), param);
            if (inverted)
                cmp = delta < 0;
            else
                cmp = delta > 0;
            
            while(j >= 0 && cmp)
            {
                clientes.set(j + 1, clientes.get(j));
                j = j - 1;
            }
            clientes.set(j + 1, chave);
        }
    }
    
    public static void insertionSort(List<Cliente> clientes, String param)
    {
        insertionSort(clientes, param, false);
    }
}
