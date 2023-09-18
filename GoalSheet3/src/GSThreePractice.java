import java.io.*;
import java.util.*;

public class GSThreePractice
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File("/Users/gutmannse/Desktop/hamlet.txt"));

        int count = 0;
        while(input.hasNextLine())
        {
            String word = input.nextLine();

            Scanner scanner = new Scanner(word);

            while (scanner.hasNext())
            {
                String out = scanner.next();
                System.out.print(out + " ");
            }
            System.out.println();
            count++;
        }

        System.out.println("total words = " + count);
    }
}
