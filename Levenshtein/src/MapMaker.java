import java.io.*;
import java.util.*;

public class MapMaker
{
    private static List<String> dictionary = new LinkedList<>();
    private static Map<String, Set<String>> wordMap = new HashMap<>();

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
        //System.out.println(dictionary);
        int c = 0;
        for (String word : dictionary)
        {
            System.out.println(c);
            c++;
            Set<String> possibleWords = new HashSet<>();
            wordMaker.MakeWords(word, possibleWords);
            possibleWords.removeIf(check -> wordExists(check, 0, dictionary.size() - 1) == -1 || Objects.equals(check, word));

            wordMap.put(word, possibleWords);
        }

        for (String s : wordMap.keySet())
        {
            //System.out.println(s + " : " + wordMap.get(s));
        }
    }

    public static int wordExists(String target, int min, int max)
    {
        int mid = (max + min) / 2;

        if (min > max)
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
