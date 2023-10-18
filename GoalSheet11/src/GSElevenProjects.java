import java.util.*;
import java.io.*;

public class GSElevenProjects
{
    public static void main(String[] args)
    {
        //writeNums(5, 5);
        for (int i = 1; i <= 12; ++i)
        {
            writeSequence(i, i);
            System.out.println();
        }
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

    public static void writeNums(int n, int start)
    {
        if (n > 0)
        {
            if (n == 1)
            {
                System.out.print(n + ", ");
            }
            else
            {
                writeNums(n - 1, start);
                System.out.print(n);
                if (n != start)
                {
                    System.out.print(", ");
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Input is less than 1");
        }
    }
    public static void writeSequence(int n, int max)
    {
        if (n > max)
        {
            System.out.print(n / 2 + ", ");
        }
        else if (n == 2 || n == 1)
        {
            System.out.print("1, ");

            if (max % 2 == 0)
            {
                System.out.print("1, ");
            }

            if (n + 2 <= max)
            {
                writeSequence(n + 3, max);
            }
        }
        else if (n % 2 != max % 2)
        {
            System.out.print(n / 2 + ", ");
            writeSequence(n + 2, max);
        }
        else
        {
            System.out.print((n + 1) / 2 + ", ");
            writeSequence(n - 2, max);
        }
    }

    public static void sumTo(int n)
    {

    }


}
