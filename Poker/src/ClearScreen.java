public class ClearScreen
{

    public static void main(String[] args) {
        System.out.println("oh hai");

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            System.out.print("\33c");
            System.out.println("More hi: " + i);
        }
    }


}
