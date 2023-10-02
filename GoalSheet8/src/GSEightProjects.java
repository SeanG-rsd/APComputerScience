import java.util.*;
import java.io.*;

public class GSEightProjects {
    public static void main(String[] args) {
        LinkedList<Integer> input = new LinkedList<Integer>(Arrays.asList(0, 0, 2, 0, 4, 0, 6, 0, 8, 0, 10, 0, 12, 0, 14, 0, 16));
        System.out.println(input);

        removeInRange(input, 0, 5, 13);
        System.out.println(input);

        System.out.println(sortAndRemoveDoubles(new LinkedList<Integer>(Arrays.asList(7,4,-9,4,15,8,27,7,11,-5,32,-9,-9))));

        Set<String> out = new HashSet<String>(List.of("adf", "even", "odd", "keep"));
        removeEvenLength(out);

        System.out.println(out);
    }

    public static void removeInRange(LinkedList<Integer> numbers, int removeValue, int min, int max) {
        Iterator<Integer> i = numbers.iterator();
        int index = 0;
        while (i.hasNext()) {
            int val = i.next();

            if (index >= min && index < max) {
                if (val == removeValue) {
                    i.remove();
                }
            }

            index++;
        }
    }

    public static List<Integer> sortAndRemoveDoubles(List<Integer> input)
    {
        return List.copyOf(new TreeSet<>(input));
    }

    public static void removeEvenLength(Set<String> input)
    {
        Iterator<String> i = input.iterator();

        while (i.hasNext())
        {
            String line = i.next();

            if (line.length() % 2 == 0)
            {
                i.remove();
            }
        }
    }

    public static boolean isUnique(Map<String, String> input)
    {
        Set<String> keys = new HashSet<>(input.values());

        return input.size() == keys.size();
    }
}
