import java.util.*;
public class GSTenProjects
{
    public static void main(String[] args)
    {

    }

    public static List<Integer> fibbonacci(int n, sum)
    {
        List<Integer> output = new LinkedList<>();

        output.addAll(fibbonacci(n - 1, 0));

        if (n == 0 || n == 1)
        {
            sum += 1;
        }
        else
        {
            sum += output.get(output.size() - 1) + output.get(output.size() - 2);
        }

        return output;
    }
}
