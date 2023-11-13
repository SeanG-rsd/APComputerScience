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
        for (String word : dictionary)
        {
            Set<String> possibleWords = new HashSet<>();

            for (String check : dictionary)
            {
                //System.out.println("\t" + check);
                if (AreNeighbors(word, check))
                {
                    possibleWords.add(check);
                }
            }

            wordMap.put(word, possibleWords);
            //System.out.println(word);
        }

        //System.out.println(AreNeighbors("cat", "at"));

        for (String s : wordMap.keySet())
        {
            System.out.println(s + " : " + wordMap.get(s));
        }
    }

    public static boolean AreNeighbors(String word1, String word2)
    {
        boolean foundOne = false;

        if (word1.length() == word2.length())
        {
            for (int i = 0; i < word1.length(); ++i)
            {
                if (!foundOne && word1.charAt(i) != word2.charAt(i))
                {
                    foundOne = true;
                }
                else if (foundOne && word1.charAt(i) != word2.charAt(i))
                {
                    return false;
                }
            }

            return true;
        }
        else if (Math.abs(word1.length() - word2.length()) == 1)
        {
            boolean didSkip = false;
            int length = Math.max(word1.length(), word2.length());
            String longer = (word1.length() - length == 0) ? word1 : word2;
            String shorter = (word1.length() - length != 0) ? word1 : word2;

            for (int i = 0; i < length; i++)
            {
                int j = i - (didSkip ? 1 : 0);

                if (!didSkip && shorter.charAt(j) != longer.charAt(i))
                {
                    didSkip = true;
                    System.out.println("skipped");
                }
                else if (didSkip && shorter.charAt(j) != longer.charAt(i))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
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
