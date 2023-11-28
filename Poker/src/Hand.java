import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Hand implements Comparable<Hand>
{
    private static HandInfo handInfo;
    private static List<Card> hand;
    private static final HandInfo NULL = new HandInfo(0, 0, new int[] {0,0,0,0,0});

    private static final String top = "---------";
    private static final String middle = "|       |";

    public static void main(String[] args)
    {
        char suit = 'H';
        char face = '9';
        System.out.println("---------");
        System.out.println("| " + suit + "     |");
        System.out.println("|       |");
        System.out.println("|   " + face + "   |");
        System.out.println("|       |");
        System.out.println("|     " + suit + " |");
        System.out.println("---------");

    }
    public Hand(List<Card> h)
    {
        hand = new LinkedList<>(h);
        handInfo = NULL;
    }

    public Hand()
    {
        hand = new LinkedList<>();
        handInfo = NULL;
    }

    public Hand(Hand h)
    {
        List<Card> temp = new LinkedList<>(h.GetHand());
        hand = new LinkedList<>();
        for (Card c : temp)
        {
            hand.add(new Card(c));
        }
        handInfo = NULL;
    }

    public void Visualize()
    {
        System.out.println(Top(hand.size()));
        for (Card c : hand)
        {
            System.out.print("| " + c.GetSuitChar() + "     |" + "  ");
        }
        System.out.println();
        System.out.println(Middle(hand.size()));
        for (Card c : hand)
        {
            System.out.print("|   " + c.GetFaceString() + "   |" + "  ");
        }
        System.out.println();
        System.out.println(Middle(hand.size()));
        for (Card c : hand)
        {
            System.out.print("|     " + c.GetSuitChar() + " |" + "  ");
        }
        System.out.println();
        System.out.println(Top(hand.size()));
    }

    public static String Top(int amount)
    {
        String output = "";
        for (int i = 0; i < amount; ++i)
        {
            output += top + "  ";
        }
        return output;
    }

    public static String Middle(int amount)
    {
        String output = "";
        for (int i = 0; i < amount; ++i)
        {
            output += middle + "  ";
        }
        return output;
    }

    public void Value()
    {
        handInfo = GetHandType(hand);
    }

    public void AddCardToHand(Card c)
    {
        hand.add(c);
    }

    public List<Card> GetHand()
    {
        return hand;
    }

    public HandInfo GetHandInfo()
    {
        handInfo = GetHandType(hand);
        return handInfo;
    }

    private static HandInfo GetHandType(List<Card> hand)
    {
        if (IsRoyalFlush(hand).IsEqualTo(NULL)) // royal flush
        {
            return IsRoyalFlush(hand);
        }
        else if (IsStraight(hand, true).IsEqualTo(NULL)) // straight flush
        {
            return IsStraight(hand, true);
        }
        else if (CheckPairs(hand).IsEqualTo(NULL))
        {
            return CheckPairs(hand);
        }

        List<Integer> ints = new ArrayList<>();
        for (Card c : hand)
        {
            ints.add(c.GetFace());
        }
        Collections.sort(ints);
        Collections.reverse(ints);

        return new HandInfo(1, GetMax(hand), ints.stream().mapToInt(Integer::intValue).toArray()); // high card
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

        if (IsFlush(hand).IsEqualTo(NULL)) { return NULL; }

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

        return new HandInfo(10, 14, new int[] {14,13,12,11,10});
    }
    private static HandInfo IsStraight(List<Card> hand, boolean hasToBeFlush)
    {
        int min = GetMin(hand);
        int[] straightFlush = new int[] {min + 4, min + 3, min + 2, min + 1, min};
        boolean[] correct = new boolean[5];
        if (!IsFlush(hand).IsEqualTo(NULL) && hasToBeFlush)
        {
            return NULL;
        }

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

        return new HandInfo(hasToBeFlush ? 9 : 5, GetMax(hand), straightFlush.clone());
    }

    private static HandInfo CheckPairs(List<Card> hand)
    {
        int[] values = new int[5];
        int[] valCount = new int[5];

        for (Card c : hand)
        {
            for (int i = 0; i < values.length; ++i)
            {
                if (values[i] == 0)
                {
                    values[i] = c.GetFace();
                    valCount[i]++;
                    break;
                }
                else if (values[i] == c.GetFace())
                {
                    valCount[i]++;
                    break;
                }
            }
        }

        int fourOfAKind = -1;
        int extra = -1;
        for (int i = 0; i < valCount.length; ++i) // four of a kind
        {
            if (valCount[i] == 4)
            {
                fourOfAKind = i;
            }
            else if (valCount[i] != 0)
            {
                extra = i;
            }
        }

        if (fourOfAKind != -1)
        {
            int val = values[fourOfAKind];
            return new HandInfo(8, val, new int[] {val,val,val,val,values[extra]});
        }

        int threeOfAKind = -1;
        int pair = -1;
        extra = -1;
        int extraTwo = -1;
        for (int i = 0; i < valCount.length; ++i) // full house
        {
            if (valCount[i] == 3)
            {
                threeOfAKind = i;
            }
            else if (valCount[i] == 2)
            {
                pair = i;
            }
            else if (valCount[i] != 0 && extra == -1)
            {
                extra = i;
            }
            else if (valCount[i] != 0 && extra != -1)
            {
                extraTwo = i;
            }
        }

        if (threeOfAKind != -1 && pair != -1) // full house
        {
            return new HandInfo(7, values[threeOfAKind], new int[]{values[threeOfAKind], values[threeOfAKind], values[threeOfAKind], values[pair], values[pair]});
        }
        else if (IsFlush(hand).IsEqualTo(NULL)) // flush
        {
            return IsFlush(hand);
        }
        else if (IsStraight(hand, false).IsEqualTo(NULL)) // straight
        {
            return IsStraight(hand, false);
        }
        else if (threeOfAKind != -1) // three of a kind
        {
            return new HandInfo(4, values[threeOfAKind], new int[] {values[threeOfAKind], values[threeOfAKind], values[threeOfAKind], Math.max(values[extraTwo], values[extra]), Math.min(values[extraTwo], values[extra])});
        }

        int twoPair = -1;
        List<Integer> cards = new ArrayList<>();

        for (int i = 0; i < valCount.length; ++i) // is two pair
        {
            if (valCount[i] == 2 && i != pair && pair != -1)
            {
                twoPair = i;
            }
            else if (valCount[i] != 0 && valCount[i] != 2)
            {
                cards.add(values[i]);
            }
        }

        if (twoPair != -1)
        {
            if (values[pair] == 1)
            {
                values[pair] = 14;
            }
            else if (values[twoPair] == 1)
            {
                values[twoPair] = 14;
            }
            int max = Math.max(values[twoPair], values[pair]);
            int min = Math.min(values[twoPair], values[pair]);
            return new HandInfo(3, max, new int[] {max, max, min, min, cards.get(0)});
        }
        else if (pair != -1)
        {
            if (values[pair] == 1)
            {
                values[pair] = 14;
            }
            Collections.sort(cards);
            Collections.reverse(cards);
            int[] array = cards.stream().mapToInt(Integer::intValue).toArray();
            return new HandInfo(2, values[pair], new int[] {values[pair], values[pair], array[0], array[1], array[2]});
        }

        return NULL;
    }

    private static HandInfo IsFlush(List<Card> hand)
    {
        int suit = hand.get(0).GetSuit();
        List<Integer> ints = new ArrayList<>();
        for (Card c : hand)
        {
            if (c.GetSuit() != suit)
            {
                return NULL;
            }
            ints.add(c.GetFace());
        }
        Collections.sort(ints);
        Collections.reverse(ints);

        return new HandInfo(6, GetMax(hand), ints.stream().mapToInt(Integer::intValue).toArray());
    }

    public int compareTo(Hand o)
    {
        return handInfo.compareTo(o.GetHandInfo());
    }
}
