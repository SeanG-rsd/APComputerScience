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

    public int[] NeighborEV(String end)
    {
        int[] ev = new int[neighbors.size()];
        for (int i = 0; i < ev.length; ++i)
        {
            String s = neighbors.get(i);
            if (s.length() < key.length())
            {
                ev[i]++;
            }
            else if (s.length() == key.length())
            {
                char changed = '0';
                for (int k = 0; k < key.length(); ++k)
                {
                    if (key.charAt(k) != s.charAt(k))
                    {
                        changed = key.charAt(k);
                    }
                }

                
            }
        }

        return ev;
    }
}
