public class HandInfo
{
    private final int value;
    private final int highestFace;

    public HandInfo(int v, int h)
    {
        value = v;
        highestFace = h;
    }

    public int GetValue()
    {
        return value;
    }

    public int GetHighestFace()
    {
        return highestFace;
    }

    public boolean IsEqualTo(HandInfo i)
    {
        return value == i.GetValue() && highestFace == i.GetHighestFace();
    }
}
