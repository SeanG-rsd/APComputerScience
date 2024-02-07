import java.util.*;
public class GSTenProjects
{
    public static void main(String[] args)
    {
        String[] input = new String[]{"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth"};
        //System.out.println(input.length);
        //mergeSort(input);
        writeSquares(5);
        //System.out.println(Arrays.toString(input));
    }

    public static void writeSquares(int n)
    {
        if (n < 2)
        {
            System.out.print("1 ");
        }
        else if (n % 2 == 0)
        {
            writeSquares(n - 1);
            System.out.print((int)Math.pow(n, 2) + " ");
        }
        else
        {
            System.out.print((int)Math.pow(n, 2) + " ");
            writeSquares(n - 1);
        }
    }

    public static void selectionSort(int[] input)
    {
        for (int i = input.length - 1; i >= 0; --i)
        {
            int biggest = i;
            for (int j = i - 1; j > 0; --j)
            {
                if (input[j] > input[biggest])
                {
                    biggest = j;
                }
            }
            swap(input, i, biggest);
        }
    }

    public static void swap(int[] input, int i, int j)
    {
        int temp = input[i];
        input[j] = input[i];
        input[i] = temp;
    }

    public static void mergeSort(String[] input)
    {
        if (input.length > 1)
        {
            String[] left = Arrays.copyOfRange(input, 0, input.length / 2);
            String[] right = Arrays.copyOfRange(input, input.length / 2, input.length);

            mergeSort(left);
            mergeSort(right);

            merge(input, left, right);
        }
    }

    public static void merge(String[] output, String[] left, String[] right) // lists have to be sorted
    {
        int l1 = 0;
        int l2 = 0;

        for (int i = 0; i < output.length; ++i)
        {
            if (l2 >= right.length || (l1 < left.length && left[l1].compareTo(right[l2]) <= 0))
            {
                output[i] = left[l1];
                l1++;
            }
            else
            {
                output[i] = right[l2];
                l2++;
            }
        }
    }
}
