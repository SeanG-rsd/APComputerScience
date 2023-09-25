public class Secretary extends Employee
{
    public int getVacationDays()
    {
        return super.getVacationDays() + 5;
    }

    public String getVacationForm()
    {
        return "pink";
    }
}
