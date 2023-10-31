import java.io.*;
import java.util.*;
public class MastermindSolver
{

    private MastermindPins pinCalc;

    public MastermindSolver()
    {
        pinCalc = new MastermindPins();
    }

    public void ResetSolver()
    {

    }

    public String getNextGuess(int white, int black, String guess, List<String> availableCodes)
    {
        //System.out.println(availableCodes.size());

        availableCodes.removeIf(possibleAnswer -> !possibleAnswer(possibleAnswer, guess, white, black));

// 4.4761 max 5
        System.out.println(availableCodes.size());
        Random r = new Random();
        return availableCodes.get(0);
        //return availableCodes.get(r.nextInt())
    }

    private boolean possibleAnswer(String check, String guess, int white, int black)
    {
        int[] pins = pinCalc.calculatePegs(check, guess);
        return pins[0] == white && pins[1] == black;
    }
}
