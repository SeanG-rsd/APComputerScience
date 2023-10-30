import java.io.*;
import java.util.*;
public class MastermindSolver
{
    private static List<String> allCodes;
    private static List<String> availableCodes;

    public MastermindSolver()
    {
        allCodes = new LinkedList<>();
        GetAllCodes();
        availableCodes = new LinkedList<>(allCodes);
        System.out.println(availableCodes.size());
    }

    private void GetAllCodes()
    {
        allCodes.clear();

        for (int first = 1; first <= 6; ++first)
        {
            for (int second = 1; second <= 6; ++second)
            {
                for (int third = 1; third <= 6; ++third)
                {
                    for (int fourth = 1; fourth <= 6; ++fourth)
                    {
                        String add = String.valueOf(first);
                        add += second;
                        add += third;
                        add += fourth;

                        allCodes.add(add);
                    }
                }
            }
        }
    }

    public void ResetSolver()
    {

    }

    public String getNextGuess(int white, int black, String guess)
    {

        availableCodes.removeIf(s -> !possibleAnswer(s.toCharArray(), guess.toCharArray(), white, black));
        System.out.println(availableCodes.size());
        Random r = new Random();
        return availableCodes.get(r.nextInt(availableCodes.size()));
    }

    private boolean possibleAnswer(char[] check, char[] guess, int white, int black)
    {
        int w = 0;
        int b = 0;
        boolean[] checked = new boolean[4];

        for (int word = 0; word < check.length; ++word)
        {
            for (int g = 0; g < guess.length; ++g)
            {
                if (check[word] == guess[g] && g == word && !checked[word])
                {
                    w++;
                    checked[word] = true;
                }
                else if (check[word] == guess[g] && check[word] != guess[word] && !checked[word])
                {
                    b++;
                    checked[word] = true;
                }
            }
        }

        return w == white && b == black;
    }
}
