import java.io.*;
import java.util.*;
public class MastermindSolver
{

    private final MastermindPins pinCalc;

    private final int[][] possiblePins = new int[][] { new int[] {4, 0}, new int[] {3, 0}, new int[] {2, 0}, new int[] {1, 0}, new int[] {0, 0}, new int[] {3, 1}, new int[] {2, 1}, new int[] {1, 1}, new int[] {0, 1}, new int[] {2, 2}, new int[] {1, 2}, new int[] {0, 2}, new int[] {0, 3}, new int[] {0, 4}};
    public MastermindSolver()
    {
        pinCalc = new MastermindPins();
    }

    public void ResetSolver()
    {

    }

    public boolean IsUnique(List<int[]> list)
    {
        for (int i = 0; i < list.size(); ++i)
        {
            for (int j = 0; j < list.size(); ++j)
            {
                if (list.get(i)[0] == list.get(j)[0] && list.get(i)[1] == list.get(j)[1] && i != j)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public String getNextGuess(int white, int black, String guess, List<String> availableCodes, List<String> allCodes, int guessNum)
    {
        availableCodes.removeIf(possibleAnswer -> !possibleAnswer(possibleAnswer, guess, white, black));

        Map<String, Integer> smallest = new TreeMap<>();
        List<String> copy = new LinkedList<>();

        /*for (String code : allCodes)
        {
            for (int[] pins : possiblePins)
            {
                copy = new LinkedList<>(availableCodes);

                copy.removeIf(possibleAnswer -> !possibleAnswer(possibleAnswer, code, pins[0], pins[1]));

                if (smallest.containsKey(code))
                {
                    if (copy.size() > smallest.get(code))
                    {
                        smallest.put(code, copy.size());
                    }
                }
                else
                {
                    smallest.put(code, copy.size());
                }
            }
        }*/

        for (String code1 : allCodes)
        {
            int scores[] = new int[41];
            for (String code2 : availableCodes)
            {
                int[] pegs = pinCalc.calculatePegs(code1, code2);
                int s = pegs[1] * 10 + pegs[0];
                scores[s]++;
            }

            int max = 0;
            for (int score : scores)
            {
                if (score > max)
                {
                    smallest.put(code1, score);
                    max = score;
                }
            }
        }

        String output = "";
        List<String> possibleGuesses = new LinkedList<>();
        int min = 1296;
        for (String key : smallest.keySet())
        {
            if (smallest.get(key) < min)
            {
                min = smallest.get(key);
                possibleGuesses = new LinkedList<>();
                possibleGuesses.add(key);
            }
            else if (smallest.get(key) == min)
            {
                possibleGuesses.add(key);
            }
        }
        //System.out.println(possibleGuesses);
        for (String s : possibleGuesses)
        {
            if (availableCodes.contains(s))
            {
                return s;
            }
        }

        return possibleGuesses.get(0); // 4.4761 max 5
    }

    private boolean possibleAnswer(String check, String guess, int white, int black)
    {
        int[] pins = pinCalc.calculatePegs(guess, check);
        return pins[0] == white && pins[1] == black;
    }
}
