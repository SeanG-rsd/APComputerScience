import java.util.*;
import java.io.*;

public class GSElevenProjects
{
    public static void main(String[] args)
    {
        fibonnacci(10);

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

    public static int fibonnacci(int n)
    {
        if (n < 1)
        {
            throw new IllegalArgumentException("n is less than 1");
        }
        else if (n == 1)
        {
            //System.out.print("1 ");
            return 1;
        }
        else if (n == 2)
        {
            //System.out.print("1 ");
            return 1;
        }
        else
        {
            int next = fibonnacci(n - 1);
            System.out.print(next + " ");
            return next;
        }
    }


}
