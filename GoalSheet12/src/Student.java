public class Student implements Comparable<Student> {
    public String lastname;
    public String firstname;
    public int ID;
    public float percentage;
    public char grade;

    public int compareTo(Student other) {
        return this.lastname.compareTo(other.lastname);
    }

    public String toString()
    {
        return lastname + "\t" + firstname + "\t" + ID + "\t" + Math.round(percentage * 100) / 100 + "\t" + grade;
    }
}
