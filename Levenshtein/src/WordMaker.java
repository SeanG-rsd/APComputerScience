import java.util.*;
public class WordMaker
{
    private static char[] chars = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public WordMaker()
    {

    }

    public void MakeWords(String original, Set<String> set)
    {
        for (int i = 0; i <= original.length(); ++i)
        {
            for (char c : chars)
            {
                if (i < original.length())
                {
                    // change a letter already there
                    set.add(ChangeLetter(original, i, c));
                    // remove a letter
                    set.add(RemoveLetter(original, i));
                }
                // add a letter
                //output.add(AddLetter(original, i, c));
            }
        }
    }

    public static String AddLetter(String original, int index, char c)
    {
        String s = original.substring(0, index) + c + original.substring(index);
        return s;
    }

    public static String ChangeLetter(String original, int index, char c)
    {
        String s = original.substring(0, index) + c + original.substring(index + 1);
        return s;
    }

    public static String RemoveLetter(String original, int index)
    {
        String s = original.substring(0, index) + original.substring(index + 1);
        return s;
    }
}
