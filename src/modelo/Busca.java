package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Busca 
{
    public static List<Cliente> BuscaParcialCliente(List<Cliente> clientes, String param, String busca)
    {
        int match;
        List<Cliente> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(busca);
        Matcher matcher;
        
        for(int i = 0; i < clientes.size(); i++)
        {
            match = clientes.get(i).compareWithText(param, busca);
            if(match == 0 || match == 1)
            {
                matches.add(clientes.get(i));
            }
        }
        
        if (matches.isEmpty())
            return null;
        
        return matches;
    }
    
    public static Cliente BuscarCliente(List<Cliente> clientes, String param, String busca)
    {
        for(int i = 0; i < clientes.size(); i++)
        {
            if(clientes.get(i).compareWithText(param, busca) == 0 )
            {
                return clientes.get(i);
            }
        }
        return null;
    }
}