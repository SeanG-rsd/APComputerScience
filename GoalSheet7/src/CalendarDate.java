public class CalendarDate implements Comparable<CalendarDate>
{
    private int year;
    private int month;
    private int day;

    public CalendarDate(int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int compareTo(CalendarDate other)
    {
        if (other.getYear() != year)
        {
            return year - other.getYear();
        }

        if (other.getMonth() != month)
        {
            return month - other.getMonth();
        }

        if (other.getDay() != day)
        {
            return day - other.getDay();
        }

        return 0;
    }

    public int getYear()
    {
        return year;
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public String toString()
    {
        return month + "/" + day + "/" + year;
    }
}
