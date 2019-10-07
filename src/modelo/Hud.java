package modelo;

public abstract class Hud 
{
    public static void menuInicial()
    {
        System.out.println("[ 1 ] - Listar Clintes");
        System.out.println("[ 2 ] - Busca Especifica");
        System.out.println("[ 3 ] - Busca Parcial");
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
            System.out.println("ERRO: Não foi possivel identificar OS.");
        }
    }
}
