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
        SaveMap();
    }

    private static void CreateMap()
    {
        WordMaker wordMaker = new WordMaker();
        Collections.sort(dictionary);
        //System.out.println(dictionary);
        int c= 0;
        for (String word : dictionary)
        {
            List<String> possibleWords = new LinkedList<>();

            for (String check : dictionary)
            {
                //System.out.println("\t" + check);
                if (AreNeighbors(word, check) && !Objects.equals(word, check))
                {
                    possibleWords.add(check);
                }
            }

            wordMap.put(word, possibleWords);
            System.out.println(c);
            c++;
        }

        for (String word : wordMap.keySet())
        {
            //System.out.println(word + " : " + wordMap.get(word));
        }
    }

    private static void SaveMap() throws FileNotFoundException
    {
        PrintStream printStream = new PrintStream("/Users/gutmannse/Desktop/gutmannsean/APComputerScience/Levenshtein/WORD_MAP");
        for (String key : wordMap.keySet())
        {
            printStream.println(key + " : " + wordMap.get(key));
        }
        printStream.close();
    }

    private static void ReadMap() throws FileNotFoundException
    {
        
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
                int j = i - (didSkip ? 1 : (i == length - 1 ? 1 : 0));

                if (!didSkip && shorter.charAt(j) != longer.charAt(i))
                {
                    didSkip = true;
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

    public List<String> Get(String word)
    {
        return wordMap.get(word);
    }
}
