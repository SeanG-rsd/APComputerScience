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
        if (o.getY() != getY())
        {
            return getY() - o.getY();
        }
        return getX() - o.getX();
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
}
