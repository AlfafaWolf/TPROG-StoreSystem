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
        List<Cliente> clientes = null;
        String paramTypes = "id,nome,email";
        do
        {
            Hud.menuInicial();
            int op = input.nextInt();
            if (op == 1)
            {
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
            }
            else if (op == 2)
            {
                if (leitor == null)
                {
                    Hud.mensagemErro("Carrege o arquivo antes.");
                    continue;
                }
                
                boolean loop;
                boolean isInverted = false;
                do
                {
                    loop = false;
                    Hud.mensagemInput("Ordem decrescente ou crescente? [d/c]");
                    Hud.clearInput(input);
                    String ordem = input.nextLine().trim().toLowerCase();
                    
                    if(ordem.equals("d"))
                        isInverted = true;
                    else if(ordem.equals("c"))
                        isInverted = false;
                    else
                    {
                        Hud.mensagemErro("Ordem invalida.");
                        loop = true;
                    }
                }while(loop);
                String param = "";
                
                do
                {
                    loop = false;
                    Hud.mensagemInput("Ordernar por? [id, nome, email]");
                    //Hud.clearInput(input);
                    param = input.nextLine().trim().toLowerCase();
                    System.out.println(param);
                    if (!paramTypes.contains(param))
                    {
                        loop = true;
                        Hud.mensagemErro("Parametro Invalido.");
                    }
                }while(loop);

                Ordena.insertionSort(clientes, param, isInverted);
                ListaFuncs.printList(clientes);
            }
            else if (op == 3)
            {
                if (leitor == null)
                {
                    Hud.mensagemErro("Carrege o arquivo antes.");
                    continue;
                }
                boolean loop;
                String param = "";
                
                do
                {
                    loop = false;
                    Hud.mensagemInput("Buscar por? [id, nome, email]");
                    Hud.clearInput(input);
                    param = input.nextLine().trim().toLowerCase();
                    if (!paramTypes.contains(param))
                    {
                        loop = true;
                        Hud.mensagemErro("Parametro Invalido.");
                    }
                }while(loop);
                Hud.mensagemInput("O que deseja buscar?");
                String busca = input.nextLine().trim();
                Cliente c = Busca.BuscarCliente(clientes, param, busca);
                
                if (c == null)
                {
                    Hud.mensagemErro("Nao foi possivel encontrar o cliente.");
                }
                else
                {
                    Hud.mensagemSucesso("Cliente encontrado com sucesso!");
                    System.out.println(c);
                }
            }
            else if (op == 4)
            {
                if (leitor == null)
                {
                    Hud.mensagemErro("Carrege o arquivo antes.");
                    continue;
                }
                boolean loop;
                String param = "";
                
                do
                {
                    loop = false;
                    Hud.mensagemInput("Buscar por? [id, nome, email]");
                    Hud.clearInput(input);
                    param = input.nextLine().trim().toLowerCase();
                    if (!paramTypes.contains(param))
                    {
                        loop = true;
                        Hud.mensagemErro("Parametro Invalido.");
                    }
                }while(loop);
                Hud.mensagemInput("O que deseja buscar?");
                String busca = input.nextLine().trim();
                List<Cliente> cs = Busca.BuscaParcialCliente(clientes, param, busca);
                if (cs == null)
                {
                    Hud.mensagemErro("Nao foi possivel encontrar nenhum cliente.");
                }
                else
                {
                    Hud.mensagemSucesso("Clientes encontrados com sucesso!");
                    ListaFuncs.printList(cs);
                }
            }
            else if (op == 0)
            {
                Hud.mensagemSucesso("Saindo do programa ...");
                break;
            }
            else
            {
                Hud.mensagemErro("Opcao invalida. Tente novamente");
            }
        }while(true);
    }
}
