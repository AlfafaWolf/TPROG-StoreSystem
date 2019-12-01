package interfaces.Leitor;

import java.util.List;
import java.util.Map;

public interface ILeitor 
{
    public void openSource(String source);
    public Map readNext();
    public List<Map> readSample(int N);
    public List<Map> readAll();
    void reset();
}
