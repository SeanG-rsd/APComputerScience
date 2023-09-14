import javax.script.ScriptContext;
import  java.util.*;

public class GSTwoPractice
{
    public static void main(String[] args)
    {
        loan();
    }

    public static void loan()
    {
        Scanner console = new Scanner(System.in);

        System.out.println("This program computes monthly " + "mortgage payments.");
        System.out.println("loan amount     : ");
        double loan = console.nextDouble();

        System.out.println("number of years : ");
        int years = console.nextInt();

        System.out.println("interest rate   : ");
        double rate = console.nextDouble();
        System.out.println();

        int n = 12 * years;
        double c = rate / 12.0 / 100.0;

        double payment = loan * c * Math.pow(1 + c, n) / (Math.pow(1 + c, n) - 1);
        System.out.println("payment = $" + (int) payment);
    }

    public static void highestPoint()
    {
        Scanner console = new Scanner(System.in);

        System.out.println("This program computer the points of a projectile.");
        System.out.println("velocity (meters/second)?");
        double velocity = console.nextDouble();
        System.out.println("angle (degrees)?");
        double angle = console.nextDouble();
        System.out.println("number of steps to display?");
        int steps = console.nextInt();

        printTable(velocity, angle, steps);
    }

    public static void printTable(double velocity, double angle, int steps)
    {
        // math and stuff
    }
}
