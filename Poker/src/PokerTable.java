import java.util.*;

public class PokerTable
{
    private enum States {PreFlop, Flop, Turn, River};
    private static Stack<Card> baseDeck = new Stack<>();
    private static List<Card> table = new LinkedList<>();
    private static List<Gambler> gamblers = new LinkedList<>();
    private static List<Gambler> losers = new LinkedList<>();
    private static Scanner console;

    private enum GameType {POKER, BLACKJACK};

    public static void main (String[] args)
    {
        console = new Scanner(System.in);
        MakeDeck(GameType.POKER);

        Collections.shuffle(baseDeck);
        BeginGame(1000);
        int annie = 10;
        int handSize = 2;

        while (gamblers.size() >= 2)
        {
            int pot = 0;

            Iterator<Gambler> checker = gamblers.iterator();
            while (checker.hasNext())
            {
                Gambler g = checker.next();
                if (!g.NewRound(annie))
                {
                    checker.remove();
                    losers.add(g);
                }
                else
                {
                    g.IncreaseDebt(annie);
                    pot += annie;
                }
            }
            if (gamblers.size() < 2) { break; }

            PrintPot(pot);
            Stack<Card> roundDeck = baseDeck;

            for (int i = 0; i < gamblers.size() * handSize; ++i) // deals cards
            {
                gamblers.get(i % gamblers.size()).DealCard(roundDeck.pop());
            }

            States state = States.PreFlop;
            List<Gambler> currentGamblers = new LinkedList<>(gamblers);
            table.clear();

            while (pot != 0)
            {
                int bet = 0;
                int maxDebt = 0;

                int size = currentGamblers.size();
                for (int i = 0; i < size; ++i)
                {
                    PrintTable(pot);
                    System.out.println(size);
                    System.out.println(i % currentGamblers.size());
                    Gambler g = currentGamblers.get(i % currentGamblers.size());
                    if (!g.IsOut())
                    {
                        int gBet = g.Choose(bet > 0, maxDebt - g.GetDebt());
                        g.IncreaseDebt(gBet);
                        if (g.GetDebt() > maxDebt)
                        {
                            bet = gBet;
                            size += i;
                            System.out.println(size);
                            maxDebt = g.GetDebt();
                        }
                        pot += gBet;
                    }
                }

                if (state == States.PreFlop)
                {
                    state = States.Flop;

                    table.add(roundDeck.pop());
                    table.add(roundDeck.pop());
                    table.add(roundDeck.pop());
                }
                else if (state == States.Flop)
                {
                    table.add(roundDeck.pop());
                    state = States.Turn;
                }
                else if (state == States.Turn)
                {
                    table.add(roundDeck.pop());
                    state = States.River;
                }
                else
                {
                    List<Gambler> winners = EvaluateHands(currentGamblers);
                    int winnings = pot / winners.size();
                    int leftOver = pot % winners.size();
                    for (Gambler g : winners)
                    {
                        g.WinHand(winnings);
                    }
                    Random r = new Random();
                    int i = r.nextInt(winners.size());
                    winners.get(i).WinHand(leftOver);
                    pot = 0;
                }
            }

        }
    }

    private static List<Gambler> EvaluateHands(List<Gambler> currentGamblers)
    {
        HandComparator handComparator = new HandComparator(table);
        Map<Gambler, HandInfo> bestHands = new HashMap<>();
        for (Gambler g : currentGamblers)
        {
            bestHands.put(g, handComparator.GetBestPlayerHand(g.GetHand()));
        }
        List<Gambler> currentBests = new ArrayList<>();
        for (Gambler g : bestHands.keySet())
        {
            if (currentBests.isEmpty())
            {
                currentBests.add(g);
            }
            if (bestHands.get(g).compareTo(bestHands.get(currentBests.get(0))) > 0)
            {
                currentBests.clear();
                currentBests.add(g);
            }
            else if (bestHands.get(g).compareTo(bestHands.get(currentBests.get(0))) == 0)
            {
                currentBests.add(g);
            }
        }
        return currentBests;
    }

    private static void PrintTable(int pot)
    {
        System.out.println("\nThe current pot is " + pot);
        if (!table.isEmpty())
        {
            System.out.println("--Table--");
            for (Card c : table) {
                System.out.println(c.ToString());
            }
            System.out.println("---------");
        }
    }

    private static void PrintPot(int pot)
    {
        System.out.println("\n" + gamblers.size() + " Players are in!");
        System.out.println("Pot Size : " + pot);
    }

    public static void BeginGame(int startingMoney)
    {
        System.out.println("How many people are joining?");
        System.out.print(">> ");
        int answer = console.nextInt();
        for (int i = 0; i < answer; ++i)
        {
            System.out.println("Player " + (i + 1));
            Gambler g = new Gambler(startingMoney);
            gamblers.add(g);
        }
        System.out.println("\nGood luck to All!");
    }

    public static void MakeDeck(GameType type)
    {
        for (int s = 0; s < 4; ++s)
        {
            for (int v = 0; v < 13; ++v)
            {
                int value = v;
                if (v > 10 && type == GameType.BLACKJACK)
                {
                    value = 10;
                }
                baseDeck.add(new Card(value, v, s));
            }
        }
    }
}
