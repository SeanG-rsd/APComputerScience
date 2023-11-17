import java.io.*;
import java.util.*;

public class MapMaker
{
    private static List<String> dictionary = new LinkedList<>();
    public static Map<String, List<String>> wordMap = new TreeMap<>();
    public static Map<String, LevNode> nodeMap = new TreeMap<>();

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

        //CreateMap();
        //SaveMap();
        ReadMap();
        //PrintMap();
    }

    private static void PrintMap()
    {
        for (String word : wordMap.keySet())
        {
            System.out.println(word + " : " + wordMap.get(word));
        }
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
        //PrintWriter printStream = new PrintWriter("D:\\Documents\\GitHub\\APComputerScience\\Levenshtein\\WORD_MAP");
        PrintWriter printStream = new PrintWriter("/Users/gutmannse/Desktop/gutmannsean/APComputerScience/Levenshtein/WORD_MAP");
        for (String key : wordMap.keySet())
        {
            printStream.println(key + " : " + wordMap.get(key));
        }
        printStream.close();
    }

    private static void ReadMap() throws FileNotFoundException
    {
        Scanner file = new Scanner(new File("/Users/gutmannse/Desktop/gutmannsean/APComputerScience/Levenshtein/WORD_MAP_MINI"));
        //Scanner file = new Scanner(new File("D:\\Documents\\GitHub\\APComputerScience\\Levenshtein\\WORD_MAP"));

        while (file.hasNextLine())
        {
            String line = file.nextLine();

            String key = "";
            List<String> keyNeighbors = new LinkedList<>();

            int first = 0;

            for (int i = 0; i < line.length(); ++i)
            {
                if (line.charAt(i) == ':')
                {
                    key = line.substring(0, i - 1);
                }
                if (line.charAt(i) == '[')
                {
                    first = i + 1;
                }
                if (line.charAt(i) == ',')
                {
                    keyNeighbors.add(line.substring(first, i));
                    first = i + 2;
                }
                if (line.charAt(i) == ']')
                {
                    keyNeighbors.add(line.substring(first, i));
                    first = i + 2;
                }
            }

            wordMap.put(key, keyNeighbors);
            nodeMap.put(key, new LevNode(key, keyNeighbors, false));
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

    public LevNode Get(String word)
    {
        return nodeMap.get(word);
    }
}
