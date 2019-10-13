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
import java.util.Scanner;

public class Principal 
{
    public static void main(String[] args) throws IOException 
    {   
        Path path;
        ILeitor leitor = null;
        Scanner input = new Scanner(System.in);
        List<Cliente> clientes;
        
        Hud.menuInicial();
        int op = input.nextInt();
        switch(op)
        {
            case 1:
                Hud.mensagemInput("Digite o nome do arquivo:");
                Hud.clearInput(input);
                String file = input.nextLine();
                try
                {
                    path = Paths.get("src/dataset/" + file).toAbsolutePath();
                    Hud.mensagemSucesso("Path Arquivo: " + path.toString());
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
                            Hud.mensagemErro("Tipo do arquivo não valido.");
                            break;
                    }
                    
                    if(leitor != null)
                    {
                        List<Map> registros = leitor.readAll();
                        clientes = ListaFuncs.generateClientsByMap(registros);
                        Hud.mensagemSucesso("Arquivo lido com sucesso! " + clientes.size() + " registros lidos.");
                    }
                }
                catch(Exception e)
                {
                    Hud.mensagemErro("Arquivo nao encontrado.");
                }
                break;
            case 2:
                if (leitor == null)
                {
                    Hud.mensagemErro("Carrege o arquivo antes.");
                    break;
                }
                break;
            case 3:
                break;
            case 4:
                break;
            case 0:
                Hud.mensagemSucesso("Saindo do programa ...");
                break;
            default:
                Hud.mensagemErro("Opcao invalida. Tente novamente");
                break;
        }   
    }
}
/*
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
        List<Map> list = leitor.readAll();
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
        
        System.out.println("---------------------------------------------");
        System.out.println("Busca Parcial:");
        List<Cliente> cs = Busca.BuscaParcialCliente(clientes, "nome", "John");
        ListaFuncs.printList(cs);
        
        System.out.println("---------------------------------------------");
        System.out.println("Busca:");
        Cliente c = Busca.BuscaCliente(clientes, "email", "Urban.Schinner@hotmail.com");
        System.out.println(c);
*/
