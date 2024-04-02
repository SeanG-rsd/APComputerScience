public class Sign
{
    String str;
    int width;

    public Sign(String s, int w)
    {
        str = s;
        width = w;
    }

    public int numberOfLines()
    {
        int output = str.length() / width;
        if (str.length() % width != 0) output++;
        return output;
    }

    public String getLines()
    {
        String output = "";
        int w = 0;
        for (int index = 0; index < str.length(); index++)
        {

            if (w == width)
            {
                w = 0;
                output += ";";
            }
            output += str.substring(index, index + 1);
            w++;
        }

        return output;
    }
}
