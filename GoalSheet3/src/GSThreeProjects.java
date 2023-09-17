import java.lang.reflect.Array;
import java.util.*;
public class GSThreeProjects
{
    public static void main(String[] args)
    {
        randomLines();
    }

    public static String repl(String string, int count) // BJP Ch 4 Ex 2
    {
        String output = "";

        for (int i = 0; i < count; ++i)
        {
            output += string;
        }

        return output;
    }

    public static void printRange(int first, int second) // BJP Ch 4 Ex 5
    {
        System.out.print("[");

        if (first == second)
        {
            System.out.println(first + "]");
            return;
        }

        int direction = 0;
        if (first > second) direction = 1;
        else direction = -1;

        for (int i = 0; i <= (first - second) * direction; ++i)
        {
            System.out.print(first - (i * direction));
            if (i != (first - second) * direction)
            {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }

    public static void romanNumerals() // BJP Ch 4 Proj 1
    {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a number:   ");
        int num = console.nextInt();

        System.out.println();
        System.out.println();

        ArrayList<String> characters = new ArrayList<>();
        characters.add("I");
        characters.add("V");
        characters.add("X");
        characters.add("L");
        characters.add("C");
        characters.add("D");
        characters.add("M");
        characters.add("v");
        characters.add("m");

        String output = "";
        int usableNum = num;

        for (int i = 0; i < 4; i++)
        {
            int count = 1000;
            for (int foo = 0; foo < i; ++foo)
            {
                count /= 10;
            }

            int digit = usableNum / count;
            usableNum = usableNum % count;

            output += digit(i, digit, characters);
        }

        System.out.println(output);
    }

    public static String digit(int placement, int digit, ArrayList<String> characters)
    {
        String output = "";

        String bottom = characters.get(characters.size() - 3 - (placement * 2));
        String main = characters.get(characters.size() - 2 - (placement * 2));
        String top = characters.get(characters.size() - 1 - (placement * 2));

        if (digit == 1) { output += bottom; }
        else if (digit == 2) { output += (bottom + bottom); }
        else if (digit == 3) { output += (bottom + bottom + bottom); }
        else if (digit == 4) { output += (bottom + main); }
        else if (digit == 5) { output += (main); }
        else if (digit == 6) { output += (main + bottom); }
        else if (digit == 7) { output += (main + bottom + bottom); }
        else if (digit == 8) { output += (main + bottom + bottom + bottom); }
        else if (digit == 9) { output += (bottom + top); }
        else if (digit == 10) { output += top; }
        return output;


    }

    public static void randomLines() // BJP Ch 5 Ex 5
    {
        Random r = new Random();
        int numLines = 5 + r.nextInt(5);

        for (int i = 0; i < numLines; ++i)
        {
            int numChars = r.nextInt(80);

            for (int foo = 0; foo < numChars; ++foo)
            {
                System.out.print((char)(r.nextInt(26) + 'a'));
            }

            System.out.println();
        }
    }

    public static void charCounter() // BJP Ch 6 Proj 1
    {

    }
}