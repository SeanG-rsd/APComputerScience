public class Ticket
{
    private int number;
    private float cost = 50.0f;
    public Ticket(int number)
    {
        this.number = number;
    }

    public float getPrice()
    {
        return cost;
    }

    public int getNumber()
    {
        return number;
    }

    public boolean canBuy()
    {
        return getNumber() <= 1;
    }

    public String toString()
    {
        return "Number: " + getNumber() + ", Price: " + getPrice();
    }
}
