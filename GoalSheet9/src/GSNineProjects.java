import java.util.*;
public class GSNineProjects
{
    public static void main(String[] args)
    {
        System.out.println(partition(new LinkedList<>(Arrays.asList(15, 1, 6, 12, -3,4,8,21,2,30,-1,9)), 10));
    }

    public static List<Integer> partition(List<Integer> input, int E)
    {
        List<Integer> output = new LinkedList<>();

        for (int i = 0; i < input.size(); ++i)
        {
            if (input.get(i) < E)
            {
                output.add(input.get(i));
            }
        }

        for (int i = 0; i < input.size(); ++i)
        {
            if (input.get(i) > E)
            {
                output.add(input.get(i));
            }
        }


        return output;
    }

    public static Integer countUnique(List<Integer> input)
    {
        return new HashSet<>(input).size();
    }

    public static Integer maxLength(Set<String> input)
    {
        Iterator<String> i = input.iterator();

        int output = 0;

        while (i.hasNext())
        {
            String s = i.next();

            if (!s.isEmpty())
            {
                output = s.length();
            }
        }

        return output;
    }

    public static Integer rarest(Map<Integer, Integer> input)
    {
        List<Integer> vals = List.copyOf(input.values());
        Map<Integer, Integer> rarity = new HashMap<>();

        for (int i = 0; i < vals.size(); ++i)
        {
            if (!rarity.containsKey(vals.get(i)))
            {
                rarity.put(vals.get(i), 1);
            }
            else
            {
                rarity.put(vals.get(i), rarity.get(vals.get(i)));
            }
        }
        int rare = 0;
        int amount = 0;
        List<Integer> keys = List.copyOf(rarity.keySet());

        for (int i = 0; i < rarity.size(); ++i)
        {
            if (rarity.get(keys.get(i)) > amount)
            {
                amount = rarity.get(keys.get(i));
                rare = keys.get(i);
            }
            else if (rarity.get(keys.get(i)) == amount && keys.get(i) < rare)
            {
                amount = rarity.get(keys.get(i));
                rare = keys.get(i);
            }
        }

        return rare;
    }
}
