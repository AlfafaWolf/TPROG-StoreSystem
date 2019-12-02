/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import interfaces.Leitor.*;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * controlar o estado
 */
public class Sessao 
{
    Vendedor vendedor;
    Calendar horario;
    ILeitor leitor;

    public Sessao(Vendedor vendedor) {
        this.vendedor = vendedor;
        this.horario = Calendar.getInstance();
    }
    
    public String printHorario()
    {
        Calendar data = this.horario;
        int hora = data.get(Calendar.HOUR_OF_DAY);
        int min = data.get(Calendar.MINUTE);
        int seg =data.get(Calendar.SECOND);
        
        String strData = hora+":"+min+":"+seg;
        return strData;
    }
    
    public void setLeitor(Path path)
    {
        String[] splitFile = path.toString().split("\\.");

        // Obter tipo do arquivo
        String tipoSource = splitFile[splitFile.length - 1];

        // Descobrir o tipo do arquivo para a instância do Leitor
        switch (tipoSource) 
        {
            case "csv": leitor = new LeitorCSV(path.toString());
                break;
            case "json": leitor = new LeitorJSON(path.toString());
                break;
            default: leitor = null;
                Hud.mensagemErro("Tipo do arquivo não valido.");
                break;
        }
    }

    public ILeitor getLeitor() {
        return leitor;
    }
    
    public Vendedor getVendedor()
    {
        return vendedor;
    }
}
