package modelo;

public class Queries 
{
    public static String loginSenhaVendedor(String matricula, String senha)
    {
        return String.format("select * from vendedor where matricula = '%s' and senha = '%s'", matricula, senha);
    }
}
