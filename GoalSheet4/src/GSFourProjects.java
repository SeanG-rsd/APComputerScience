import java.util.*;

public class GSFourProjects
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);

        processName(console);
    }

    public static String padString(String input, int padding) // BJP Ch 3 Ex 11
    {
        String output = input;

        if (padding > input.length())
        {
            for (int i = 0; i < (padding - input.length()); ++i)
            {
                output += "";
            }
        }

        return output;
    }

    public static String reverseString(String input) // BJP Ch 3 Ex 13
    {
        String output = "";

        for (int i = input.length() - 1; i >= 0; i--)
        {
            output += input.charAt(i);
        }

        return output;
    }

    public static void processName(Scanner console) // BJP Ch 3 Ex 15
    {
        System.out.print("Enter your full name : ");
        String fullName = console.nextLine();

        System.out.println("Your name is : " + reverseString(fullName));
    }
}
