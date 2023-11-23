import java.util.*;

public class Gambler
{
    private int money;
    public final String name;
    private final Scanner console;

    private ArrayList<Card> hand = new ArrayList<>();

    public Gambler(int money)
    {
        console = new Scanner(System.in);
        name = GetName();
        this.money = money;
    }

    public Gambler(Gambler g)
    {
        console = new Scanner(System.in);
        name = g.name;
        this.money = g.money;
    }

    private String GetName()
    {
        System.out.println("What is your name?");
        System.out.print(">> ");
        return console.next();
    }

    public boolean NewRound(int annie)
    {
        hand.clear();
        ResetDebt();
        System.out.println("\n" + name);
        System.out.println("Current Balance : $" + money);
        System.out.println("Would you like to play for : $" + annie);
        char answer = YesOrNo();

        if (answer == 'Y')
        {
            money -= annie;
            return true;
        }
        else
        {
            return false;
        }
    }

    public char YesOrNo()
    {
        System.out.print(">> ");
        String answer = console.next().toUpperCase();

        if (answer.charAt(0) == 'Y')
        {
            return 'Y';
        }
        else if (answer.charAt(1) == 'N')
        {
            return 'N';
        }
        return YesOrNo();
    }

    public void DealCard(Card c)
    {
        hand.add(c);
    }

    private void PrintMoney()
    {
        System.out.println("\n" + name + "'s Balance");
        System.out.println("$" + money);
    }

    public void PrintHand()
    {
        System.out.println("\n" + name + "'s Cards");
        for (Card c : hand)
        {
            System.out.println(c.ToString());
        }
    }

    public List<Card> GetHand()
    {
        return hand;
    }

    private char GetChoice(List<Character> choices)
    {
        System.out.print(">> ");
        char answer = console.next().toUpperCase().charAt(0);
        if (choices.contains(answer))
        {
            return answer;
        }
        return GetChoice(choices);
    }

    public void WinHand(int pot)
    {
        money += pot;
    }

    private int Raise(int bet)
    {
        System.out.print(">> ");
        int r = console.nextInt();
        if (r > bet && r <= money)
        {
            return r;
        }
        System.out.println("You must bet higher than the last bet and cannot bet more than you have");
        return Raise(bet);
    }

    private int Bet()
    {
        System.out.print(">> ");
        int r = console.nextInt();
        if (r <= money)
        {
            return r;
        }
        System.out.println("You cannot bet more than you have");
        return Bet();
    }

    private int increasingDebt = 0;

    public void ResetDebt()
    {
        increasingDebt = 0;
    }

    public void IncreaseDebt(int inc)
    {
        increasingDebt += inc;
        System.out.println("Debt for " + name + " is " + increasingDebt);
    }

    public int GetDebt()
    {
        return increasingDebt;
    }

    private boolean isOutOfRound = false;

    public void SetOut(boolean out)
    {
        isOutOfRound = out;
    }

    public boolean IsOut()
    {
        return isOutOfRound;
    }

    public int Choose(boolean hasToCall, int bet)
    {
        PrintMoney();
        PrintHand();
        System.out.println("\n" + name + "'s Choice");
        if (hasToCall)
        {
            System.out.println("The current bet is " + bet);
            System.out.println("Would you like to Call, Raise, or Fold?");
            char answer = GetChoice(List.of('C', 'R', 'F'));
            if (answer == 'C')
            {
                if (bet > money)
                {
                    int m = money;
                    money -= m;
                    return m;
                }
                money -= bet;
                System.out.println("call");
                return bet;
            }
            else if (answer == 'R' && bet < money)
            {
                System.out.println("What would you like to raise to?");
                int raise = Raise(bet);
                money -= raise;
                return raise;
            }
            else
            {
                System.out.println(name + "Folded.");
                SetOut(true);
                return 0;
            }
        }
        else
        {
            System.out.println("Would you like to Check, Bet, or Fold?");
            char answer = GetChoice(List.of('C', 'B', 'F'));

            if (answer == 'C')
            {
                return 0;
            }
            else if (answer == 'B')
            {
                System.out.println("What would you like to bet?");
                int newBet = Bet();
                System.out.println(newBet);
                money -= newBet;
                return newBet;
            }
            else
            {
                System.out.println(name + "Folded.");
                SetOut(true);
                return 0;
            }
        }
    }
}
