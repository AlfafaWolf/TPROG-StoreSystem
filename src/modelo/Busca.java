package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Busca 
{
    public static void BuscaParcialCliente(List<Cliente> clientes, String param, String busca)
    {
        int match;
        List<Cliente> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(busca);
        Matcher matcher;
        for(int i = 0; i < clientes.size(); i++)
        {
            match = clientes.get(i).compareWithText(param, busca);
            if( match == 0 || match == 1)
            {
                matches.add(clientes.get(i));
            }
        }
        for(int j = 0; j < matches.size(); j++)
        {
            System.out.println(matches.get(j).toString());
        }  
    }
    
    public static void BuscaCliente(List<Cliente> clientes, String param, String busca)
    {
        for(int i = 0; i < clientes.size(); i++)
        {
            if( clientes.get(i).compareWithText(param, busca) == 0 )
            {
                System.out.println(clientes.get(i).toString());
            }
        }
    }
}