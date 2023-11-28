public class Card implements Comparable<Card>

{
    private final static String[] Suits  = new String[] {"Hearts", "Diamonds", "Clubs", "Spades"};
    private final static Character[] SuitChars = new Character[] {'♥', '♦', '♣', '♠'};
    private final static String[] Faces = new String[] {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private final static Character[] FaceChars = new Character[] {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
    private int value;
    private int face;
    private int suit;

    public Card(int v, int f, int s)
    {
        value = v;
        face = f;
        suit = s;
    }

    public Card(Card c)
    {
        value = c.GetValue();
        face = c.GetFace();
        suit = c.GetSuit();
    }
    public int GetValue()
    {
        return value;
    }

    public int GetFace()
    {
        return face;
    }

    public int GetSuit()
    {
        return suit;
    }

    public char GetFaceString()
    {
        return FaceChars[face - 1];
    }

    public char GetSuitChar()
    {
        return SuitChars[suit - 1];
    }

    public String ToString()
    {
        return Faces[face - 1] + " of " + Suits[suit - 1];
    }

    public int compareTo(Card o)
    {
        if (o.GetSuit() == suit)
        {
            if (o.GetFace() == face)
            {
                return 0;
            }
            return face - o.GetFace();
        }
        return suit - o.GetSuit();
    }
}
