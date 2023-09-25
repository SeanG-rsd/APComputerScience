public class Employee
{
    public int getHours()
    {
        return 40;
    }

    public double getSalary()
    {
        return 40000.0;
    }

    public int getVacationDays()
    {
        return 10;
    }

    public String getVacationForm()
    {
        return "yellow";
    }

    public void takeDictation(String text)
    {
        System.out.println("Dictating text: " + text);
    }
}
