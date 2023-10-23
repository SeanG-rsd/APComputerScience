import java.util.*;
import java.io.*;

public class GSTwelveProjects
{
    public static void main(String[] args) throws FileNotFoundException
    {
        //binarySearch();

        pointComparator();
    }

    public static void binarySearch() throws FileNotFoundException
    {
        Scanner file = new Scanner(new File("D:\\Documents\\GitHub\\APComputerScience\\GoalSheet12\\src\\binaryDictionary"));

        List<String> dictionary = new LinkedList<>();

        while (file.hasNext())
        {
            dictionary.add(file.nextLine());
        }

        Collections.sort(dictionary);
        int index = search(dictionary, "found", 0, dictionary.size() - 1);
        System.out.println(index);
    }

    public static int search(List<String> dictionary, String target, int min, int max)
    {
        int mid = (min + max) / 2;

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
            return search(dictionary, target, min, mid - 1);
        }
        else
        {
            return search(dictionary, target, mid + 1, max);
        }
    }

    public static void pointComparator()
    {
        PointComparator pointComparator = new PointComparator();
        System.out.println(pointComparator.compare(new Point(1, 1), new Point(2, 2)));

        List<Point> points = new LinkedList<>();
        points.add(new Point(3, 3));
        points.add(new Point(2, 2));
    }
}
