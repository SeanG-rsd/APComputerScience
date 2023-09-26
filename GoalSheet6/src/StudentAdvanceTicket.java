public class StudentAdvanceTicket extends AdvanceTicket
{
    public StudentAdvanceTicket(int number)
    {
        super(number);
    }

    public float getPrice()
    {
        return super.getPrice() / 2;
    }

    public String toString()
    {
        return "Number: " + super.getNumber() + ", Price: " + getPrice() + " (ID Required)";
    }
}
