import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    GSTwelveProjects.SortType sortType;
    public StudentComparator(GSTwelveProjects.SortType sortType)
    {
        this.sortType = sortType;
    }

    public int compare(Student one, Student two)
    {
        return one.getInfo(sortType).compareTo(two.getInfo(sortType));

    }
}
