public class MastermindPins
{

    public MastermindPins()
    {

    }
    public int[] calculatePegs(String code, String guess)
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


        return new int[] {whitePegs, blackPegs};
    }
}
