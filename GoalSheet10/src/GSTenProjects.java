import java.util.*;
public class GSTenProjects
{
    public static void main(String[] args)
    {
        System.out.println(writeSquares(7));
    }

    public static List<Integer> fibbonacci(int n)
    {
        List<Integer> output = new LinkedList<>();

        if (n == 2)
        {
            output.add(1);
            output.add(1);
            return output;
        }
        else if (n > 2)
        {
            output.addAll(fibbonacci(n - 1));
            output.add(output.get(output.size() - 1) + output.get(output.size() - 2));
            return output;
        }
        else if (n > 0)
        {
            output.add(1);

        }

        return output;
    }

    public static List<Integer> writeSquares(int n)
    {
        List<Integer> output = new LinkedList<>();
        if (n < 2)
        {
            output.add(1);
            return output;
        }
        else if (n % 2 == 1)
        {
            output.add(n * n);
            output.addAll(writeSquares(n - 1));
            return output;
        }
        else
        {
            output.addAll(writeSquares(n - 1));
            output.add(n * n);
            return output;
        }
    }
}
