public class Sphere implements ThreeDimensionalShape
{
    private float radius;

    public Sphere(float radius)
    {
        this.radius = radius;
    }

    public double getVolume()
    {
        return 4 * 3.1415 * Math.pow(radius, 3) / 3;
    }

    public double getSurfaceArea()
    {
        return 4 * 3.1415 * Math.pow(radius, 2);
    }

    public double getRadius()
    {
        return radius;
    }
}
