import java.util.*;
public class LevNode
{
    String key;
    List<LevNode> neighbors;

    protected int depth;

    public LevNode(String k)
    {
        key = k;
        depth = -1;
        neighbors = new ArrayList<>();
    }

    public void AddNeighbor(LevNode node)
    {
        neighbors.add(node);
    }

    public void SetDepth(int d)
    {
        depth = d;
    }

    public int GetDepth()
    {
        return depth;
    }

    public String toString()
    {
        if (neighbors.isEmpty())
        {
            return key + " : []";
        }
        else
        {
            String output = key + " : [" + neighbors.get(0).key;
            for (int i = 1; i < neighbors.size(); ++i)
            {
                output += ", " + neighbors.get(i).key;
            }
            output += "]";
            return output;
        }
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
