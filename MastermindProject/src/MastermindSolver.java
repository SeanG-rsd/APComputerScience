// Seam Gutmann

import java.util.*;
public class MastermindSolver
{
    private final MastermindPins pinCalc;

    public MastermindSolver()
    {
        pinCalc = new MastermindPins();
    }
    public String getNextGuess(int white, int black, String guess, List<String> availableCodes, List<String> allCodes, int guessNum)
    {
        availableCodes.removeIf(possibleAnswer -> !possibleAnswer(possibleAnswer, guess, white, black));

        Map<String, Integer> smallest = new TreeMap<>();

        for (String code1 : allCodes) // knuths algorithm
        {
            int[] scores = new int[41];
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

        List<String> possibleGuesses = new LinkedList<>();
        int min = 1296;
        for (String key : smallest.keySet()) // get the best guesses
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
        for (String s : possibleGuesses) // pick the first one if it's available
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
