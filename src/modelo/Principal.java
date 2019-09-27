package modelo;

import interfaces.Leitor.LeitorJSON;
import interfaces.Leitor.LeitorCSV;
import interfaces.Leitor.ILeitor;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Principal 
{
    public static void main(String[] args) throws IOException 
    {   
        String file = "JSONClientes.json";
        Path path = Paths.get("src/dataset/" + file).toAbsolutePath();
        
        ILeitor leitor;
        
        // Quebra a String, usando como parâmetro "."
        // Se usa "\\" para transformar em "\.", para o split poder lêr
        String[] splitFile = file.split("\\.");
        
        // Obter tipo do arquivo
        String type = splitFile[splitFile.length - 1];

        // Descobrir o tipo do arquivo para a instância do Leitor
        switch (type) 
        {
            case "csv":
                leitor = new LeitorCSV(path.toString());
                break;
            case "json":
                leitor = new LeitorJSON(path.toString());
                break;
            default:
                leitor = null;
                System.out.println("ERROR: Tipo do arquivo não identificado.");
                return;
        }
        
        // Ler dados da fonte
        List<Map> list = leitor.readSample(5);
        ListaFuncs.printList(list);
        
        // Gerar Lista de Clientes
        List<Cliente> clientes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) 
        {
            clientes.add(new Cliente(list.get(i)));
            System.out.println(clientes.get(i).toString());
        }
        System.out.println("---------------------------------------------");
        System.out.println("Ordenado:");
        Ordena.insertionSort(clientes, "email");
        
        ListaFuncs.printList(clientes);
    }
}
