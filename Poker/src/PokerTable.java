import java.util.*;

public class PokerTable
{
    private enum States {PreFlop, Flop, Turn, River};
    private static Stack<Card> baseDeck = new Stack<>();
    private static List<Card> table = new LinkedList<>();
    private static List<Gambler> gamblers = new LinkedList<>();
    private static Scanner console;

    private enum GameType {POKER, BLACKJACK};

    public static void main (String[] args)
    {
        console = new Scanner(System.in);
        MakeDeck(GameType.POKER);
        BeginGame(1000);
        int annie = 10;
        int handSize = 2;

        while (gamblers.size() >= 2)
        {
            PrintNext("Everybody");
            int pot = 0;

            for (Gambler g : gamblers)
            {
                if (g.NewRound(annie))
                {
                    g.IncreaseDebt(annie);
                    pot += annie;
                }
            }
            if (gamblers.size() < 2) { break; }

            PrintPot(pot);
            Stack<Card> roundDeck = baseDeck;
            Collections.shuffle(roundDeck);

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
                    //System.out.println(size);
                    //System.out.println(i % currentGamblers.size());
                    Gambler g = currentGamblers.get(i % currentGamblers.size());
                    if (!g.IsOut())
                    {
                        PrintNext(currentGamblers.get(i % currentGamblers.size()).name);
                        PrintTable(pot);
                        int gBet = g.Choose(bet > 0, maxDebt - g.GetDebt());
                        g.IncreaseDebt(gBet);
                        if (g.GetDebt() > maxDebt)
                        {
                            bet = gBet;
                            size += i;
                            //System.out.println(size);
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
                    HandComparator handComparator = new HandComparator(table);
                    PrintLines(8);
                    List<Gambler> winners = EvaluateHands(currentGamblers, handComparator);
                    int winnings = pot / winners.size();
                    int leftOver = pot % winners.size();
                    for (Gambler g : winners)
                    {
                        System.out.println(g.name + " won $" + winnings + ", with " + handComparator.GetBestPlayerHand(g.GetHand()).ToString());

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

    private static List<Gambler> EvaluateHands(List<Gambler> currentGamblers, HandComparator handComparator)
    {

        Map<Gambler, HandInfo> bestHands = new HashMap<>();
        for (Gambler g : currentGamblers)
        {
            if (!g.IsOut())
            {
                bestHands.put(g, handComparator.GetBestPlayerHand(g.GetHand()));
                System.out.println(bestHands.get(g).ToString());
            }
        }
        List<Gambler> currentBests = new ArrayList<>();
        Gambler currentBest = bestHands.keySet().iterator().next();
        for (Gambler g : bestHands.keySet())
        {
            /*if (currentBests.isEmpty())
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
            }*/
            if (currentBest == null)
            {
                currentBest = g;
            }
            else if (bestHands.get(g).compareTo(bestHands.get(currentBest)) > 0)
            {
                currentBest = g;
            }
        }
        return new ArrayList<>(List.of(currentBest));
    }

    private static void PrintLines(int amount)
    {
        for (int i = 0; i < amount; ++i)
        {
            System.out.println();
        }
    }

    private static void PrintNext(String whom)
    {
        PrintLines(10);
        System.out.println(whom + ", are you ready?");
        String answer = console.next();
        PrintLines(10);
    }

    private static void PrintTable(int pot)
    {

        System.out.println("\nThe current pot is " + pot);
        if (!table.isEmpty())
        {
            System.out.println("--Table--");
            Hand Table = new Hand(table);
            Table.Visualize();
            System.out.println("---------");
        }
        else
        {
            System.out.println("--Table--");
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
