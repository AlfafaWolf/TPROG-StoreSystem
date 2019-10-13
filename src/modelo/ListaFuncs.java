package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ListaFuncs 
{
    public static void printList(List lista)
    {
        for(int i=0; i < lista.size(); i++)
        {
            System.out.println(lista.get(i).toString());
        }
    }
    
    public static List<Cliente> generateClientsByString(List<String> linhas)
    {
        List<Cliente> clientes = new ArrayList<>();
        
        for (String linha : linhas) 
        {
            clientes.add(new Cliente(linha));
        }
        
        return clientes;
    }
    
    public static List<Cliente> generateClientsByString(List<String> linhas, String sortedBy)
    {
        List<Cliente> clientes = generateClientsByString(linhas);
        
        Ordena.insertionSort(clientes, sortedBy, false);
        
        return clientes;
    }
    
    public static List<Cliente> generateClientsByMap(List<Map> registros)
    {
        List<Cliente> clientes = new ArrayList<>();
        
        for (Map registro : registros) 
        {
            clientes.add(new Cliente(registro));
        }
        
        return clientes;
    }
    
    public static List<Cliente> generateClientsByMap(List<Map> registros, String sortedBy)
    {
        List<Cliente> clientes = generateClientsByMap(registros);
        
        Ordena.insertionSort(clientes, sortedBy);
        
        return clientes;
    }
}
