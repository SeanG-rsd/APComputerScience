import java.util.*;

public class GSThreePractice
{
    public static void main(String[] args)
    {
        System.out.println(repl("hello", 3));
    }

    public static String repl(String string, int count)
    {
        String output = "";

        for (int i = 0; i < count; ++i)
        {
            output += string;
        }

        return output;
    }
}