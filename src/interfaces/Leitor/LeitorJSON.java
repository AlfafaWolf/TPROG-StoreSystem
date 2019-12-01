package interfaces.Leitor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.*;

public class LeitorJSON implements ILeitor
{
    private String content;
    private JSONArray jsonContent;
    private int index;
    
    public LeitorJSON()
    { 
        this.index = 0;
    }
    public LeitorJSON(String path)
    {
        this.index = 0;
        openSource(path);
    }
    
    @Override
    public void openSource(String source) 
    {
        Path path = Paths.get(source).toAbsolutePath();
        try
        {
            content = new String(Files.readAllBytes(path));
            jsonContent = new JSONArray(content);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map readNext() 
    {
        JSONObject item = (JSONObject) jsonContent.get(index);

        String id = item.get("ID").toString();
        String nome = item.get("NOME").toString();
        String email = item.get("EMAIL").toString();
         
        Map registro = new HashMap();
        registro.put(0,id);
        registro.put(1,nome);
        registro.put(2,email);
        
        this.index++;
        return registro;
    }

    @Override
    public List<Map> readSample(int N) 
    {
        List<Map> list = new ArrayList<>();
        while(index < jsonContent.length() && index < N)
        {
            list.add(readNext());
        }
        return list;
    }

    @Override
    public List<Map> readAll() 
    {
        reset();
        List<Map> list = new ArrayList<>();
        while(index < jsonContent.length())
        {
            list.add(readNext());
        }
        return list;
    }
    
    @Override
    public void reset()
    {
        index = 0;
    }
}
