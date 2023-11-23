import java.util.Comparator;
import java.util.*;
import java.util.jar.JarEntry;

public class HandComparator
{
    static List<Card> table = new ArrayList<>();
    private enum HandTypes {RoyalFlush, StraightFlush, FourOfAKind, FullHouse, Flush, Straight, ThreeOfAKind, TwoPair, Pair, HighCard};
    public HandComparator(List<Card> t)
    {
        table = t;
    }
    private static final HandInfo NULL = new HandInfo(0, 0);

    public HandInfo GetBestPlayerHand(List<Card> hand)
    {
        List<HandInfo> info = new ArrayList<>();

        Hand hand1 = new Hand();
        for (Card c : table)
        {
            hand1.AddCardToHand(c);
        }
        hand1.Value();
        info.add(hand1.GetHandInfo());

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
                info.add(newHand.GetHandInfo());
            }
        }

        for (int j = 0; j < table.size(); ++j)
        {
            for (int h = j + 1; h < table.size(); ++h)
            {
                Hand fiveCards = new Hand();
                fiveCards.AddCardToHand(new Card(hand.get(0)));
                fiveCards.AddCardToHand(new Card(hand.get(1)));

                for (Card c : table)
                {
                    if (c.compareTo(table.get(j)) != 0 && c.compareTo(table.get(h)) != 0)
                    {
                        fiveCards.AddCardToHand(new Card(c));
                    }
                }
                fiveCards.Value();
                info.add(fiveCards.GetHandInfo());
            }
        }

        Collections.sort(info);

        return info.get(0);
    }
}
