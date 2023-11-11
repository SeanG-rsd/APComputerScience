import java.io.*;
import java.util.*;

public class MapMaker
{
    private static List<String> dictionary = new LinkedList<>();
    private static Map<String, List<String>> wordMap = new HashMap<>();

    public MapMaker(String filename) throws FileNotFoundException
    {
        SetDictionary(filename);
    }

    private static void SetDictionary(String filename) throws FileNotFoundException
    {
        Scanner file = new Scanner(new File(filename));

        while (file.hasNextLine())
        {
            dictionary.add(file.nextLine());
        }

        CreateMap();
    }

    private static void CreateMap()
    {
        WordMaker wordMaker = new WordMaker();
        Collections.sort(dictionary);
        //List<String> possibleWords = new LinkedList<>(wordMaker.MakeWords("dog"));
        System.out.println(dictionary);
        System.out.println(wordExists("dil", 0, dictionary.size()));
        //System.out.println(possibleWords);
    }

    public static int wordExists(String target, int min, int max)
    {
        int mid = (max - min) / 2;

        if (min < max)
        {
            return -1;
        }
        else if (Objects.equals(target, dictionary.get(mid)))
        {
            return mid;
        }
        else if (target.compareTo(dictionary.get(mid)) < 0)
        {
            return wordExists(target, min, mid - 1);
        }
        else
        {
            return wordExists(target, mid + 1, max);
        }
    }

}
