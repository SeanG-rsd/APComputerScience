import java.util.Comparator;
import java.util.*;
import java.util.jar.JarEntry;

public class HandComparator implements Comparator<List<Card>>
{
    List<Card> table = new ArrayList<>();
    private enum HandTypes {RoyalFlush, StraightFlush, FourOfAKind, FullHouse, Flush, Straight, ThreeOfAKind, TwoPair, Pair, HighCard};
    public HandComparator(List<Card> t)
    {
        table = t;
    }

    private static HandInfo NULL = new HandInfo(0, 0);

    public static void main(String[] args)
    {
        System.out.println(GetHandType(new LinkedList<>(List.of(new Card(2, 2, 2), new Card(1, 1, 1), new Card(2, 2, 1), new Card(1, 1, 1), new Card(3, 3, 1)))));
    }

    public int compare(List<Card> hand1, List<Card> hand2)
    {
        return 0;
    }

    private static HandInfo GetHandType(List<Card> hand)
    {
        if (!IsRoyalFlush(hand).IsEqualTo(NULL))
        {
            return IsRoyalFlush(hand);
        }
        else if (!IsStraight(hand, true).IsEqualTo(NULL))
        {
            return IsStraight(hand, true);
        }
        else if (!IsFourOfAKind(hand).IsEqualTo(NULL))
        {
            return IsFourOfAKind(hand);
        }
        else if (!IsFullHouse(hand).IsEqualTo(NULL))
        {
            return IsFullHouse(hand);
        }
        else if (!IsFlush(hand).IsEqualTo(NULL))
        {
            return IsFlush(hand);
        }
        else if (!IsStraight(hand, false).IsEqualTo(NULL))
        {
            return IsStraight(hand, false);
        }
        else if (!IsThreeOfAKind(hand).IsEqualTo(NULL))
        {
            return IsThreeOfAKind(hand);
        }
        else if (!IsTwoPair(hand).IsEqualTo(NULL))
        {
            return IsTwoPair(hand);
        }
        else if (!IsPair(hand).IsEqualTo(NULL))
        {
            return IsPair(hand);
        }
        return new HandInfo(1, GetMax(hand));
    }

    private static int GetMin(List<Card> hand)
    {
        int min = 20;
        for (Card c : hand)
        {
            if (c.GetFace() < min)
            {
                min = c.GetFace();
            }
        }

        return min;
    }

    private static int GetMax(List<Card> hand)
    {
        int max = 0;
        for (Card c : hand)
        {
            if (c.GetFace() > max)
            {
                max = c.GetFace();
            }
        }

        return max;
    }

    private static HandInfo IsRoyalFlush(List<Card> hand)
    {
        int[] royalFlush = new int[] {10, 11, 12, 13, 1};

        if (!IsFlush(hand).IsEqualTo(NULL)) { return NULL; }

        boolean[] correct = new boolean[hand.size()];
        for (int i = 0; i < correct.length; ++i)
        {
            Card c = hand.get(i);
            for (int check = 0; check < royalFlush.length; ++check)
            {
                if (c.GetFace() == royalFlush[check] && !correct[check])
                {
                    correct[check] = true;
                }
            }
        }

        for (boolean b : correct)
        {
            if (!b)
            {
                return NULL;
            }
        }

        return new HandInfo(10, 14);
    }

    private static HandInfo IsStraight(List<Card> hand, boolean hasToBeFlush)
    {
        int min = GetMin(hand);
        int[] straightFlush = new int[] {min, min + 1, min + 2, min + 3, min + 4};
        boolean[] correct = new boolean[5];
        if (!IsFlush(hand).IsEqualTo(NULL) && hasToBeFlush) { return NULL; }

        for (Card c : hand)
        {
            for (int i = 0; i < correct.length; ++i)
            {
                if (c.GetFace() == straightFlush[i] && !correct[i])
                {
                    correct[i] = true;
                }
            }
        }

        for (boolean b : correct)
        {
            if (!b)
            {
                return NULL;
            }
        }

        return new HandInfo(hasToBeFlush ? 9 : 5, GetMax(hand));
    }

    private static HandInfo IsFourOfAKind(List<Card> hand)
    {
        int val1 = 0;
        int val2 = 0;

        int valOneCount = 0;
        int valTwoCount = 0;

        for (Card c : hand)
        {
            if (val1 == 0 && c.GetFace() != val2)
            {
                val1 = c.GetFace();
                valOneCount++;
            }
            else if (val2 == 0 && c.GetFace() != val1)
            {
                val2 = c.GetFace();
                valTwoCount++;
            }
            else if (c.GetFace() == val1)
            {
                valOneCount++;
            }
            else if (c.GetFace() == val2)
            {
                valTwoCount++;
            }
        }

        if (valOneCount == 4)
        {
            return new HandInfo(8, val1);
        }
        else if (valTwoCount == 4)
        {
            return new HandInfo(8, val2);
        }
        else
        {
            return NULL;
        }
    }

    private static HandInfo IsFullHouse(List<Card> hand)
    {
        int val1 = 0;
        int val2 = 0;

        int valOneCount = 0;
        int valTwoCount = 0;

        for (Card c : hand)
        {
            if (val1 == 0 && c.GetFace() != val2)
            {
                val1 = c.GetFace();
                valOneCount++;
            }
            else if (val2 == 0 && c.GetFace() != val1)
            {
                val2 = c.GetFace();
                valTwoCount++;
            }
            else if (c.GetFace() == val1)
            {
                valOneCount++;
            }
            else if (c.GetFace() == val2)
            {
                valTwoCount++;
            }
        }

        if (valOneCount == 2 && valTwoCount == 3)
        {
            return new HandInfo(7, val2);
        }
        else if (valOneCount == 3 && valTwoCount == 2)
        {
            return new HandInfo(7, val1);
        }
        return NULL;
    }

    private static HandInfo IsFlush(List<Card> hand)
    {
        int suit = hand.get(0).GetSuit();
        for (Card c : hand)
        {
            if (c.GetSuit() != suit)
            {
                return NULL;
            }
        }

        return new HandInfo(6, GetMax(hand));
    }

    private static HandInfo IsThreeOfAKind(List<Card> hand)
    {
        int val1 = 0;
        int val2 = 0;

        int valOneCount = 0;
        int valTwoCount = 0;

        for (Card c : hand)
        {
            if (val1 == 0 && c.GetFace() != val2)
            {
                val1 = c.GetFace();
                valOneCount++;
            }
            else if (val2 == 0 && c.GetFace() != val1)
            {
                val2 = c.GetFace();
                valTwoCount++;
            }
            else if (c.GetFace() == val1)
            {
                valOneCount++;
            }
            else if (c.GetFace() == val2)
            {
                valTwoCount++;
            }
        }

        if (valOneCount == 3)
        {
            return new HandInfo(4, val1);
        }
        else if (valTwoCount == 3)
        {
            return new HandInfo(4, val2);
        }
        return NULL;
    }

    private static HandInfo IsTwoPair(List<Card> hand)
    {
        int val1 = 0;
        int val2 = 0;
        int val3 = 0;

        int valOneCount = 0;
        int valTwoCount = 0;
        int valThreeCount = 0;

        for (Card c : hand)
        {
            if (val1 == 0 && c.GetFace() != val2 && c.GetFace() != val3)
            {
                val1 = c.GetFace();
                valOneCount++;
            }
            else if (val2 == 0 && c.GetFace() != val1 && c.GetFace() != val3)
            {
                val2 = c.GetFace();
                valTwoCount++;
            }
            else if (val3 == 0 && c.GetFace() != val2 && c.GetFace() != val1)
            {
                val3 = c.GetFace();
                valThreeCount++;
            }
            else if (c.GetFace() == val1)
            {
                valOneCount++;
            }
            else if (c.GetFace() == val2)
            {
                valTwoCount++;
            }
            else if (c.GetFace() == val3)
            {
                valThreeCount++;
            }
        }

        if (valOneCount == 2 && valTwoCount == 2)
        {
            return new HandInfo(3, Math.max(val1, val2));
        }
        else if (valThreeCount == 2 && valTwoCount == 2)
        {
            return new HandInfo(3, Math.max(val3, val2));
        }
        else if (valOneCount == 2 && valThreeCount == 2)
        {
            return new HandInfo(3, Math.max(val1, val3));
        }
        return NULL;
    }

    private static HandInfo IsPair(List<Card> hand)
    {
        int[] values = new int[5];

        for (Card c : hand)
        {
            for (int b = 0; b < values.length; ++b)
            {
                if (values[b] == 0)
                {
                    values[b] = c.GetFace();
                }
                if (values[b] == c.GetFace())
                {
                    return new HandInfo(2, c.GetFace());
                }
            }
        }

        return NULL;
    }
}
