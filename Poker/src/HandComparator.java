import java.util.Comparator;
import java.util.*;
import java.util.jar.JarEntry;

public class HandComparator //implements Comparator<List<Card>>
{
    static List<Card> table = new ArrayList<>();
    private enum HandTypes {RoyalFlush, StraightFlush, FourOfAKind, FullHouse, Flush, Straight, ThreeOfAKind, TwoPair, Pair, HighCard};
    public HandComparator(List<Card> t)
    {
        table = t;
    }

    private static HandInfo NULL = new HandInfo(0, 0);

    public static void main(String[] args)
    {
        table = new LinkedList<>(List.of(new Card(2, 2, 2), new Card(1, 1, 1), new Card(2, 2, 1), new Card(1, 1, 1), new Card(3, 3, 1)));
        List<Card> hand = new LinkedList<>(List.of(new Card(3 , 3, 2), new Card(5, 5, 4)));
        //System.out.println(compare(hand));
        //System.out.println(GetHandType(table));
    }

    public Hand GetBestPlayerHand(List<Card> hand)
    {
        List<Hand> player1Hands = new LinkedList<>();

        for (int i = 0; i <= hand.size(); ++i)
        {
            if (i == 0)
            {
                Hand fiveCards = new Hand(table);
                player1Hands.add(fiveCards);
            }
            else if (i == 1)
            {
                for (Card f : hand)
                {
                    for (Card c : table)
                    {
                        Hand newHand = new Hand();
                        for (Card t : table)
                        {
                            if (t.compareTo(c) != 0)
                            {
                                newHand.AddCardToHand(t);
                            }
                        }
                        newHand.AddCardToHand(f);
                        newHand.Value();
                        player1Hands.add(newHand);
                    }
                }
            }
            else if (i == 2)
            {
                for (int j = 0; j < table.size(); ++j)
                {
                    for (int h = j + 1; h < table.size(); ++h)
                    {
                        Hand fiveCards = new Hand();
                        fiveCards.AddCardToHand(hand.get(0));
                        fiveCards.AddCardToHand(hand.get(1));

                        for (Card c : table)
                        {
                            if (c.compareTo(table.get(j)) != 0 && c.compareTo(table.get(h)) != 0)
                            {
                                fiveCards.AddCardToHand(c);
                            }
                        }
                        fiveCards.Value();
                        player1Hands.add(fiveCards);

                    }
                }
            }
        }

        Collections.sort(player1Hands);
    }

    public static int compare(List<Hand> player1Hands)
    {
        return 0;
    }
}
