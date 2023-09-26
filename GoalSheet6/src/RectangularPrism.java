public class RectangularPrism implements ThreeDimensionalShape
{
    private float width;
    private float length;
    private float height;

    public RectangularPrism(float width, float length, float height)
    {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public double getVolume()
    {
        return width * height * length;
    }

    public double getSurfaceArea()
    {
        return (width * length * 2) + (width * height * 2) + (length * height * 2);
    }
}
