import java.util.*;
public class LevNode
{
    String key;
    List<String> neighbors;
    private boolean isDead;

    public LevNode(String k, List<String> n, boolean d)
    {
        key = k;
        neighbors = n;
        isDead = d;
    }

    public LevNode(LevNode copy)
    {
        key = copy.GetKey();
        neighbors = copy.GetNeighbors();
        isDead = copy.IsDead();
    }

    public List<String> GetNeighbors()
    {
        return neighbors;
    }

    public String GetKey()
    {
        return key;
    }

    public boolean IsDead()
    {
        return isDead;
    }

    public void changeDead(boolean d)
    {
        isDead = d;
    }
}
