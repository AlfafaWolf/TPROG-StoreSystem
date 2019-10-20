package modelo;

import java.util.Scanner;

public abstract class Hud 
{
    public static void menuInicial()
    {
        System.out.println("[ 1 ] - Carregar Arquivo");
        System.out.println("[ 2 ] - Listar Clintes");
        System.out.println("[ 3 ] - Busca Especifica");
        System.out.println("[ 4 ] - Busca Parcial");
        System.out.println("[ 0 ] - Sair do Programa");
        System.out.println("> Digite sua opcao: ");
    }
    
    public static void clear()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (Exception e)
        {
            mensagemErro("Nao foi possivel identificar OS.");
        }
    }
    
    public static void mensagemErro(String msg)
    {
        System.out.println("[ERRO] " + msg);
    }
    public static void mensagemInput(String msg)
    {
        System.out.println("> " + msg);
    }
    public static void mensagemSucesso(String msg)
    {
        System.out.println("[SISTEMA] " + msg);
    }
    public static void clearInput(Scanner input)
    {
        input.nextLine();
    }
}
