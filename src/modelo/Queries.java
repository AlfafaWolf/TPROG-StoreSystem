package modelo;

public class Queries 
{
    public static String loginSenhaVendedor(String matricula, String senha)
    {
        return String.format("select * from vendedor where matricula = '%s' and senha = '%s'", matricula, senha);
    }
    
    public static String clienteDoCpf(String cpf)
    {
        return String.format("select * from cliente where cpf = '%s'", cpf);
    }
    
    public static String produtoDoId(String id)
    {
        return String.format("select * from produto where id = '%s'", id);
    }
}
