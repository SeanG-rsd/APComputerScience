import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        projectFour();
    }

    public static void exerciseThree()
    {
        for (int i = 1; i <= 10; ++i)
        {
            System.out.print(i * i + " ");
        }
    }

    public static void exerciseFour()
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

    public static void projectFour()
    {
        int NUM_STAIRS = 10;
        for (int i = 0; i < NUM_STAIRS; ++i)
        {
            drawMan(NUM_STAIRS - i, i + 1);
        }

        System.out.print("      ");
        for (int i = 0; i < NUM_STAIRS - 1; ++i)
        {
            System.out.print("********");
        }
        if (NUM_STAIRS == 1)
        {
            System.out.print("*********");
        }
        System.out.println("******");
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
}