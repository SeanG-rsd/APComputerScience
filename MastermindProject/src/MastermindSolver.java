import java.io.*;
import java.util.*;
public class MastermindSolver
{

    private MastermindPins pinCalc;

    private int[][] possiblePins = new int[][] { new int[] {4, 0}, new int[] {3, 0}, new int[] {2, 0}, new int[] {1, 0}, new int[] {0, 0}, new int[] {3, 1}, new int[] {2, 1}, new int[] {1, 1}, new int[] {0, 1}, new int[] {2, 2}, new int[] {1, 2}, new int[] {0, 2}, new int[] {1, 3}, new int[] {0, 3}, new int[] {0, 4}};

    public MastermindSolver()
    {
        pinCalc = new MastermindPins();
    }

    public void ResetSolver()
    {

    }

    public String getNextGuess(int white, int black, String guess, List<String> availableCodes)
    {
        availableCodes.removeIf(possibleAnswer -> !possibleAnswer(possibleAnswer, guess, white, black));
        Map<String, Integer> smallest = new HashMap<>();

        for (String code : availableCodes)
        {
            List<String> copy = new LinkedList<>();

            for (int[] pins : possiblePins)
            {
                copy = new LinkedList<>(availableCodes);

                copy.removeIf(possibleAnswer -> !possibleAnswer(code, possibleAnswer, pins[0], pins[1]));

                if (smallest.containsKey(code))
                {
                    smallest.put(code, copy.size() + smallest.get(code));
                }
                else
                {
                    smallest.put(code, copy.size());
                }
            }
        }

        String output = "";
        for (String key : smallest.keySet())
        {
            System.out.println(smallest.get(key));
            if (!smallest.containsKey(output))
            {
                output = key;
            }
            else if (smallest.get(key) < smallest.get(output))
            {
                output = key;
            }
        }
        System.out.println(availableCodes.size());

        return output;


// 4.4761 max 5

        //Random r = new Random();
        //return availableCodes.get(0);
        //return availableCodes.get(r.nextInt())
    }

    private boolean possibleAnswer(String check, String guess, int white, int black)
    {
        int[] pins = pinCalc.calculatePegs(guess, check);
        return pins[0] == white && pins[1] == black;
    }
}
