import java.util.*;

public class GSTwoProjects
{
    public static void main(String[] args)
    {
        triangle();
    }

    public static void squares()
    {
        for (int i = 1; i <= 10; ++i)
        {
            System.out.print(i * i + " ");
        }
    }

    public static void fibonacci()
    {
        int lastNum = 1;
        int secondNum = 1;

        for (int i = 1; i <= 12; ++i)
        {
            int output = 0;

            if (i <= 2)
            {
                output = 1;
            }
            else
            {
                output = lastNum + secondNum;

            }

            System.out.print(output + " ");
            lastNum = secondNum;
            secondNum = output;
        }
    }

    public static void staircase()
    {
        int NUM_STAIRS = 20;
        for (int i = 0; i < NUM_STAIRS; ++i)
        {
            drawMan(NUM_STAIRS - i, i + 1);
        }

        System.out.print("      ");
        for (int i = 0; i <= NUM_STAIRS; ++i)
        {
            System.out.print("******");
        }
        System.out.println("**");
    }

    public static void drawMan(int position, int wall)
    {
        indent(position);
        System.out.print("  o   *******");
        wall(wall - 1);
        indent(position);
        System.out.print(" /|\\  *");
        wall(wall);
        indent(position);
        System.out.print(" / \\  *");
        wall(wall);


    }

    public static void indent(int count)
    {
        for (int i = 0; i < count; ++i)
        {
            System.out.print("      ");
        }
    }

    public static void wall(int count)
    {
        for (int foo = 0; foo < count; ++foo)
        {
            System.out.print("      ");
        }
        System.out.println("*");
    }

    public static void quadratic()
    {
        Scanner console = new Scanner(System.in);

        System.out.println("This is the quadratic equation.");

        System.out.println("Enter an a value  :");
        int a = console.nextInt();

        System.out.println("Enter a b value   :");
        int b = console.nextInt();

        System.out.println("Enter a c value   :");
        int c = console.nextInt();
        System.out.println();
        System.out.println();

        equation(a, b, c);
    }

    public static void equation(int a, int b, int c)
    {
        if (Math.pow((double) b, 2) < (4 * a * c))
        {
            System.out.println("There is no solution");
            return;
        }

        double root = Math.sqrt((Math.pow((double) b, 2)) - (4 * a * c));
        double answerOne = (-b - root) / (2 * a);
        double answerTwo = (-b + root) / (2 * a);

        System.out.println("The solutions are: " + (int)answerOne + " and " + (int)answerTwo);
    }

    public static void triangle()
    {
        Scanner console = new Scanner(System.in);

        System.out.println("This is the angle calculator.");

        System.out.println("Enter a side length  :");
        int sideA = console.nextInt();

        System.out.println("Enter a side length  :");
        int sideB = console.nextInt();

        System.out.println("Enter a side length  :");
        int sideC = console.nextInt();

        double angleA = Math.acos((Math.pow(sideB, 2) + Math.pow(sideC, 2) - Math.pow(sideA, 2)) / (2 * sideB * sideC)) * 180 / 3.1415;
        double angleB = Math.acos((Math.pow(sideA, 2) + Math.pow(sideC, 2) - Math.pow(sideB, 2)) / (2 * sideA * sideC)) * 180 / 3.1415;
        double angleC = Math.acos((Math.pow(sideA, 2) + Math.pow(sideB, 2) - Math.pow(sideC, 2)) / (2 * sideB * sideA)) * 180 / 3.1415;

        System.out.println("Your 3 angles are : " + Math.round(angleA * 100.0) / 100.0 + ", " + Math.round(100.0 * angleB) / 100.0 + ", " + Math.round(angleC * 100.0) / 100.0);
        System.out.println("These angles add up to " + (int)(angleA + angleB + angleC));
    }
}