public class Hexagon implements Shape
{
    private double side;
    public Hexagon(double side)
    {
        this.side = side;
    }
    public double getPerimeter()
    {
        return side * 6;
    }

    public double getArea()
    {
        return 3 * Math.sqrt(3) / 2 * Math.pow(side, 2);
    }
}
