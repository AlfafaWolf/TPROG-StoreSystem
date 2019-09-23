package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class ListaFuncs 
{
    public static void printList(List lista)
    {
        for(int i=0; i < lista.size(); i++)
        {
            System.out.println(lista.get(i).toString());
        }
    }
    
    public static List<Cliente> generateClients(List<String> linhas, Boolean sorted)
    {
        List<Cliente> clientes = new ArrayList<>();
        
        for (String linha : linhas) 
        {
            clientes.add(new Cliente(linha));
        }
        
        //if(sorted)
            //Ordena.insertionSort(clientes);
        
        return clientes;
    }
}
