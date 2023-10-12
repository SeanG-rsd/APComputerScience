import java.util.*;
public class GSTenProjects
{
    public static void main(String[] args)
    {
        String[] input = new String[]{"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth"};
        System.out.println(input.length);
        mergeSort(input);
        System.out.println(Arrays.toString(input));
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
