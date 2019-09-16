package interfaces.Leitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LeitorCSV implements ILeitor 
{
    private Scanner scanner;

    public LeitorCSV(){ }
    public LeitorCSV(String filename)
    {
        openSource(filename);
    }
    
    @Override
    public void openSource(String filename){
        try
        {
            File arq = new File(filename);
            this.scanner  = new Scanner(arq);
            System.out.println("Arquivo Aberto com Sucesso");
        }
        catch(FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
    } 
    
    @Override
    public Map readNext()
    {  
        if(!this.scanner.hasNext()) return null;
        String line = this.scanner.nextLine();
        
        String att[] = line.split(",");
        String id = att[0];
        String nome = att[1];
        String email = att[2];
        
        Map registro = new HashMap();
        registro.put(0, id);
        registro.put(1, nome);
        registro.put(2, email);
        
        return registro;
    }
    
    @Override
    public List<Map> readSample(int N)
    {
        Map linha;
        List<Map> list = new ArrayList<>();
        for(linha = this.readNext(); //Inicialização, lê a primeira linha.
            linha != null && N > 0; //Condição, linha lida não é nula, e ainda precisa ler mais linhas.
            linha = this.readNext(), N--){//Antes da proxima iteração, lê a proxima linha, e decrementa a uantidade de linhas a serem lidas.
            
                list.add(linha);
        }
        return list;
    }
    
    @Override
    public List<Map> readAll()
    {
        Map linha;
        List<Map> list = new ArrayList<>();
        while( (linha = this.readNext()) != null ){
            list.add(linha);
        }
        return list;
    }
}
