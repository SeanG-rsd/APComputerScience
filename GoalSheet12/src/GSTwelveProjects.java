import java.util.*;
import java.io.*;

public class GSTwelveProjects
{
    public static void main(String[] args) throws FileNotFoundException
    {
        binarySearch();
    }

    public static void binarySearch() throws FileNotFoundException
    {
        Scanner file = new Scanner(new File("/Users/gutmannse/Desktop/gutmannsean/APComputerScience/GoalSheet12/src/binaryDictionary"));

        List<String> dictionary = new LinkedList<>();

        while (file.hasNext())
        {
            dictionary.add(file.nextLine());
        }

        Collections.sort(dictionary);
        int index = search(dictionary, "found", 0, 0);
        System.out.println(index);
    }

    public static int search(List<String> dictionary, String target, int min, int max)
    {
        min = 0;
        max = dictionary.size() - 1;

        while (min <= max)
        {
            int mid = (min + max) / 2;
            if (Objects.equals(target, dictionary.get(mid)))
            {
                return mid;
            }
            else if (target.compareTo(dictionary.get(mid)) < 0)
            {
                max = mid - 1;
            }
            else
            {
                min = mid + 1;
            }
        }

        return -1;
    }
}
