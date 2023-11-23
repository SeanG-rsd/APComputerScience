public class HandInfo implements Comparable<HandInfo>
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

    public String ToString()
    {
        return value + " : " + highestFace;
    }

    public int compareTo(HandInfo o)
    {
        if (o.value == value)
        {
            if (o.highestFace == highestFace)
            {
                return 0;
            }
            return o.highestFace - highestFace;
        }
        return o.value - value;
    }
}
