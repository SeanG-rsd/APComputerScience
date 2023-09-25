import java.util.*;
import java.io.*;

public class MasterMind
{
    public static void main(String[] args) throws  FileNotFoundException
    {
        testAlgorithm();
        //calculatePegs("1111", "1121", 3, 0);
    }

    public static void testAlgorithm() throws FileNotFoundException
    {
        File answers = new File("/Users/gutmannse/Desktop/input.txt");
        // C:/Users/sean/Desktop/input.txt

        Scanner scanner = new Scanner(answers);

        int iteration = 0;
        while (scanner.hasNextLine())
        {

            String line = scanner.nextLine();

            String code = line.substring(0, 4);
            System.out.print(code + ",");

            String guess = line.substring(5, 9);
            System.out.print(guess + ",");

            int whitePegs = Integer.parseInt(line.substring(10, 11));
            System.out.print(whitePegs + ",");

            int blackPegs = Integer.parseInt(line.substring(12, 13));
            System.out.print(blackPegs);

            calculatePegs(code, guess, whitePegs, blackPegs);

            iteration++;
            System.out.println();

        }

        System.out.println(iteration);
    }


    public static void calculatePegs(String code, String guess, int white, int black)
    {
        char[] codeChars = code.toCharArray();

        boolean[] crossOutGuess = new boolean[code.length()];
        char[] guessChars = guess.toCharArray();

        int whitePegs = 0;
        int blackPegs = 0;

        for (int c = 0; c < codeChars.length; ++c)
        {

            for (int i = 0; i < guessChars.length; ++i)
            {
                if (guessChars[i] == codeChars[c])
                {
                    if (!crossOutGuess[i] && c == i)
                    {
                        crossOutGuess[i] = true;
                        whitePegs++;
                        break;
                    }
                    else if (!crossOutGuess[i])
                    {
                        if (guessChars[i] != codeChars[i] && codeChars[c] != guessChars[c])
                        {
                            crossOutGuess[i] = true;

                            blackPegs++;
                            break;
                        }


                    }
                }
            }
        }

        if (whitePegs == white && black == blackPegs)
        {
            System.out.print(" : Solved!");
        }
        else
        {
            System.out.print(" : w : " + whitePegs + ", b : " + blackPegs);
        }
    }
}