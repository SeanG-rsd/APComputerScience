import java.util.*;

public class Student implements Comparable<Student> {
    private String lastname;
    private String firstname;
    private String ID;
    private String percentage;
    private String grade;


    public Student(String lastname, String firstname, String ID, String percentage, String grade)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.ID = ID;
        this.percentage = percentage;
        this.grade = grade;
    }
    public int compareTo(Student other)
    {
        return this.lastname.compareTo(other.lastname);
    }

    public String toString()
    {
        return lastname + "\t" + firstname + "\t" + ID + "\t" + Math.round(Double.parseDouble(percentage) * 100) / 100 + "\t" + grade;
    }

    public String getInfo(GSTwelveProjects.SortType sortType)
    {
        if (sortType == GSTwelveProjects.SortType.LastName)
        {
            return lastname;
        }
        else if (sortType == GSTwelveProjects.SortType.FirstName)
        {
            return firstname;
        }
        else if (sortType == GSTwelveProjects.SortType.ID)
        {
            return ID;
        }
        else if (sortType == GSTwelveProjects.SortType.Percentage)
        {
            return percentage;
        }
        else
        {
            return grade;
        }
    }
}
