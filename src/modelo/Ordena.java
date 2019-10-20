package modelo;
import java.util.ArrayList;
import java.util.List;

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
            
            while(j >= 0 && compare(chave.compareWith(clientes.get(j), param), inverted))
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
    
    private static boolean compare(int delta, boolean inverted)
    {
        if (inverted)
            return delta < 0;
        else
            return delta > 0;
    }
}
