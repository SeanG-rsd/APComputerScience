import java.util.*;
import java.io.*;

public class GSElevenProjects
{
    public static void main(String[] args)
    {
        //writeNums(5, 5);
        writeSequence(6, 6);
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

    /*public static List<Integer> writeSequence(int n)
    {
        List<Integer> output = new LinkedList<>();

        if (n > 0)
        {
            if (n == 1)
            {
                output.add(1);
            }
            else
            {
                output.addAll(writeSequence(n - 1));

                if (output.size() % 2 == 1)
                {
                    List<Integer> add = new LinkedList<>();

                    for (int i = 0; i < output.size(); ++i)
                    {
                        add.add(output.get(i));

                        if (output.get(i) == 1)
                        {
                            add.add(1);
                        }
                    }

                    output = add;
                }
                else
                {
                    List<Integer> add = new LinkedList<>();

                    add.add(output.get(0) + 1);

                    for (int i = 0; i < output.size(); ++i)
                    {
                       if (output.get(i) == 1 && add.get(add.size() - 1) == 1)
                       {
                           continue;
                       }

                       add.add(output.get(i));
                    }

                    add.add(output.get(0) + 1);

                    output = add;
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Input is less than 1");
        }

        return output;
    }*/

    public static void writeSequence(int n, int max)
    {
        if (max % 2 == 1)
        {
            max--;
        }

        if (n > max)
        {
            System.out.print(n / 2 + ", ");
        }
        else if (n == 2)
        {
            System.out.print("1, ");

            writeSequence(n + 1, max);
        }
        else if (n % 2 == 1)
        {
            System.out.print(n / 2 + ", ");
            writeSequence(n + 2, max);
        }
        else
        {
            System.out.print(n / 2 + ", ");
            writeSequence(n - 2, max);
        }
    }

    public static void sumTo(int n)
    {

    }


}
