import java.util.Arrays;

public class HandInfo implements Comparable<HandInfo>
{
    private final int value;
    private final int highestFace;

    private final int[] values;

    public HandInfo(int v, int h, int[] val)
    {
        value = v;
        highestFace = h;
        values = val;
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
        return value != i.GetValue() || highestFace != i.GetHighestFace();
    }

    public String ToString()
    {
        System.out.println(Arrays.toString(values));
        return value + " : " + highestFace;
    }

    public int compareTo(HandInfo o)
    {
        if (o.value == value)
        {
            if (o.highestFace == highestFace)
            {
                for (int i = 0; i < values.length; ++i)
                {
                    if (o.values[i] > values[i])
                    {
                        return -1;
                    }
                    else if (values[i] > o.values[i])
                    {
                        return 1;
                    }
                }
                return 0;
            }
            return highestFace - o.highestFace;
        }
        return value - o.value;
    }
}
