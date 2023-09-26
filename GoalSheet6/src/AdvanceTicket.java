public class AdvanceTicket extends Ticket
{
    public AdvanceTicket(int number)
    {
        super(number);
    }

    public boolean canBuy()
    {
        return true;
    }

    public float getPrice()
    {
        if (getNumber() >= 10)
        {
            return 30.0f;
        }
        else
        {
            return 40.0f;
        }
    }
}
