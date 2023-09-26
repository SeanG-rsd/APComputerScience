public class Cone implements ThreeDimensionalShape
{
    private float height;
    private float radius;

    public Cone(float height, float radius)
    {
        this.height = height;
        this.radius = radius;
    }

    public double getVolume()
    {
        return 3.1415 * Math.pow(radius, 2) * height / 3;
    }

    public double getSurfaceArea()
    {
        return 3.1415 * radius * (radius + Math.sqrt(Math.pow(height, 2) + Math.pow(radius, 2)));
    }
}
