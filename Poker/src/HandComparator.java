import java.util.Comparator;
import java.util.*;

public class HandComparator implements Comparator<List<Card>>
{
    List<Card> table = new ArrayList<>();
    public HandComparator(List<Card> t)
    {
        table = t;
    }

    public int compare(List<Card> hand1, List<Card> hand2)
    {
        return 0;
    }
}
