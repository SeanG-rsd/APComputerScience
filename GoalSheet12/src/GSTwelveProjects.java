import java.util.*;
import java.io.*;

public class GSTwelveProjects
{
    public static void main(String[] args) throws FileNotFoundException
    {
        //binarySearch();

        //pointComparator();
        int[] array = new int[]{6,3,0,1,4,2,5,7,10,3,45,7};
        selectionSort(array);
        System.out.println(Arrays.toString(array));
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

        points.sort(new PointComparator());
    }

    public static void selectionSort(int[] input)
    {
        for (int i = input.length - 1; i >= 0; --i)
        {
            int biggest = i;
            int smallest = input.length - 1 - i;

            for (int j = i - 1; j >= 0; --j)
            {
                if (input[j] > input[biggest])
                {
                    biggest = j;
                }
                if (input[input.length - 1 - j] < input[smallest])
                {
                    smallest = i - j;
                }
            }

            swap(input, i, biggest);
            swap(input, input.length - 1 - i, smallest);
            System.out.println(i + "  " + (input.length - 1 - i));
            System.out.println(biggest + "  " + smallest);
            System.out.println(Arrays.toString(input));
        }
    }

    public static void swap(int[] input, int i, int j)
    {
        int temp = input[j];
        input[j] = input[i];
        input[i] = temp;

    }

    public static void dataProcessing()
    {

    }
}
