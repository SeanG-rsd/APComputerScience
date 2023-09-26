import java.awt.*;

public class GSSixProjects
{
    public static void main(String[] args)
    {
        StudentAdvanceTicket ticket = new StudentAdvanceTicket(29);
        if (ticket.canBuy())
        {
            System.out.println(ticket.toString());
        }

        Hexagon hex = new Hexagon(5);

        System.out.println(hex.getArea());
    }


}
