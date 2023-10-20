import java.math.*;
public class Point implements Comparable<Point>
{
    private int x;
    private int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point()
    {
        new Point(0, 0);
    }
    public int compareTo(Point o)
    {
        return Double.compare(getDistance(), o.getDistance());
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    public double getDistance()
    {
        return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
    }
}
