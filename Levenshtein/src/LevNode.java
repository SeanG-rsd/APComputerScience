import java.util.*;
public class LevNode
{
    String key;
    List<LevNode> neighbors;

    public LevNode(String k, List<String> n)
    {
        key = k;

    }

    public LevNode(LevNode copy)
    {
        key = copy.GetKey();
        neighbors = copy.GetNeighbors();
    }

    public List<LevNode> GetNeighbors()
    {
        return neighbors;
    }

    public String GetKey()
    {
        return key;
    }
}
