public class GSTenPractice
{
    public static void main(String[] args)
    {
        System.out.println(pow(2, 16));
    }

    public static int pow(int x, int y)
    {
        if (y == 0)
        {
            return 1;
        }
        else
        {
            return x * pow(x, y - 1);
        }
    }
}
