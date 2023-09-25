public class Rectangle implements Shape
{
    private double width;
    private double height;

    public Rectangle(double width, double height)
    {
        this.height = height;
        this.width = width;
    }
    public double getArea()
    {
        return width * height;
    }

    public double getPerimeter()
    {
        return 2.0 * (width + height);
    }
}
