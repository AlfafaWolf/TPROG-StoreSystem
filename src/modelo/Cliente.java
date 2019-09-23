package modelo;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.*;

public class Cliente 
{
    private int id;
    private String nome;
    private String email;
    
    public Cliente(){ }

    public Cliente(int id, String nome, String email) 
    {
        this.id = id;
        this.nome = nome;
        this.email = email;
        
        validateEmail(this.email);
    }
    
    public Cliente(String json_str) 
    {
        JSONObject object = new JSONObject(json_str);
        this.id = object.getInt("ID");
        this.nome = object.getString("NOME");
        this.email = object.getString("EMAIL");
        
        validateEmail(this.email);
    }
    
    public Cliente(Map atributos)
    {
        this.id = Integer.parseInt(atributos.get(0).toString());
        this.nome = atributos.get(1).toString();
        this.email = atributos.get(2).toString();
        
        validateEmail(this.email);
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    private String[] parseLinha(String linha){
        String[] atributos = linha.split(",");
        return atributos;
    }
    
    // Compara cliente de acordo com o parâmetro
    public int compareWith(Cliente C, String param) {
        int delta;
        String search = param.toLowerCase().trim();
        
        if (search.equals("id"))
        {
           // compara por int
            if (C.getId() == this.id)
                delta = 0;
            else
                delta = C.getId() - this.id;
        }
        else if (search.equals("nome"))
        {
            // compara por string
            delta = C.getNome().compareTo(this.nome);
        }
        else if(search.equals("email"))
        {
            // compara por string
            delta = C.getEmail().compareTo(this.email);
        }
        else
        {
            // erro
            System.out.println("ERRO. Parametro do inválido na função compareWith de Cliente");
            delta = 0;
        }
        return delta;
    }
    
    // Verifica se o email é valido
    private boolean isEmailValid(String email)
    {
        Pattern p = Pattern.compile("^(.+)@(.+)$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
    
    // Gera um novo email
    private String generateEmail()
    {
        return "u" + this.id + "@email.com";
    }
    
    // Cria um email para o usuário caso o dele estiver inválido
    private void validateEmail(String email)
    {
        if(!isEmailValid(this.email))
            this.email = generateEmail();
    }
    
    @Override
    public String toString() {
        return String.format(
            String.format("Id......: %s\n", id)
          + String.format("Nome....: %s\n", nome)
          + String.format("Email...: %s\n", email)
        );
    }
}
