import java.util.*;
public class TreeClient
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        IntSearchTree numbers = new IntSearchTree();
        System.out.print("Next int (0 to quit)? : ");
        int number = console.nextInt();
        while (number != 0)
        {
            numbers.add(number);
            System.out.print("Next int (0 to quit)? : ");
            number = console.nextInt();
        }
        System.out.println();

        System.out.println("Tree Structure : ");
        //numbers.printSideways();
        System.out.println("Sorted List : ");
        //numbers.print();
    }
}
