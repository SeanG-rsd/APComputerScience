import java.util.Comparator;

public class PointComparator implements Comparator<Point>
{
    public int compare(Point one, Point two)
    {
        return one.compareTo(two);
    }

}
