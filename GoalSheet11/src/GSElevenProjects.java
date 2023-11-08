import java.util.*;
import java.io.*;

public class GSElevenProjects
{
    public static void main(String[] args)
    {
        //writeNums(5);
        //System.out.println();
        for (int i = 1; i <= 12; ++i)
        {
            //writeSequence(i);
            //System.out.println();
        }

        //System.out.println(sumTo(4));
        List<Integer> sequence = new LinkedList<>();
        fibonnacci(10, sequence);
        System.out.println(sequence);
    }

    public static void starString(int n)
    {
        if (n == 0)
        {
            System.out.println("*");
        }
        else if (n == 1)
        {
            System.out.println("**");
        }
        else
        {
            for (int i = 0; i < Math.pow(2, n - 1); ++i)
            {
                System.out.print("*");
            }
            starString(n - 1);
        }
    }

    public static void writeNums(int n)
    {
        if (n > 0)
        {
            if (n == 1)
            {
                System.out.print(n + "  ");
            }
            else
            {
                writeNums(n - 1);
                System.out.print(n + "  ");
            }
        }
        else
        {
            throw new IllegalArgumentException("Input is less than 1");
        }
    }
    public static void writeSequence(int n)
    {
        if (n < 0)
        {
            throw new IllegalArgumentException("n is less than 1");
        }
        else if (n == 1)
        {
            System.out.print("1, ");
        }
        else if (n == 2)
        {
            System.out.print("1, 1, ");
        }
        else
        {
            System.out.print((n + 1) / 2 + ", ");
            writeSequence(n - 2);
            System.out.print((n + 1) / 2 + ", ");
        }
    }

    public static double sumTo(int n)
    {
        if (n == 0)
        {
            return 0.0;
        }
        else
        {
            return ((double) 1 / n) + sumTo(n - 1);
        }
    }

    public static void fibonnacci(int n, List<Integer> sequence)
    {
        if (n < 1)
        {
            throw new IllegalArgumentException("n is less than 1");
        }
        else if (n == 1)
        {
            sequence.add(1);
        }
        else if (n == 2)
        {
            sequence.add(1);
            sequence.add(1);
        }
        else
        {
            fibonnacci(n - 1, sequence);
            sequence.add(sequence.get(sequence.size() - 1) + sequence.get(sequence.size() - 2));
        }
    }


}
