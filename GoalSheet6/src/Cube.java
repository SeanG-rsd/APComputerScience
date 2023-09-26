public class Cube implements ThreeDimensionalShape
{
    private double sideLength;
    public Cube(int sideLength)
    {
        this.sideLength = sideLength;
    }

    public double getVolume()
    {
        return Math.pow(sideLength, 3);
    }

    public double getSurfaceArea()
    {
        return Math.pow(sideLength, 2) * 6;
    }
}
