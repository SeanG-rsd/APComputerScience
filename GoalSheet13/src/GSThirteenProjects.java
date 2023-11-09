import java.util.*;
public class GSThirteenProjects
{
    public static void main(String[] args)
    {
        //System.out.println(maxOccurrences(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5, 5, 5, 6, 7, 8))));
        System.out.println(repeat("hello", 3));
        System.out.println(writeBinary(4));
    }

    public static int maxOccurrences(List<Integer> numbers)
    {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : numbers)
        {
            if (map.containsKey(i))
            {
                map.put(i, map.get(i) + 1);
            }
            else
            {
                map.put(i, 1);
            }
        }

        int max = 0;

        if (!map.isEmpty())
        {
            List<Integer> list = new ArrayList<>(map.values());
            Collections.sort(list);
            max = list.get(list.size() - 1);
        }

        return max;
    }

    public static String repeat(String s, int n)
    {
        if (n == 0)
        {
            return "";
        }
        else
        {
            return s + repeat(s, n - 1);
        }
    }

    public static String writeBinary(int n)
    {
        if (n == 0)
        {
            return "0";
        }
        else
        {
            return writeBinary(n - 1);
        }
    }
}
