import java.io.*;
import java.util.*;
public class GSSevenProjects
{
    public static void main(String[] args) throws FileNotFoundException
    {
        //System.out.println(scaleByK(new ArrayList<>(Arrays.asList(4, 2, 0, 1 ,3))));

        //System.out.println(minToFront(new ArrayList<>(Arrays.asList(5, 8, 92, 4, 2, 17, 1))));

        //modifyClasses();

        System.out.println(reverseLines());
    }

    public static ArrayList<Integer> scaleByK(ArrayList<Integer> list)
    {
         ArrayList<Integer> output = new ArrayList<>();

         for (int i = 0; i < list.size(); ++i)
         {
             for (int ii = 0; ii < list.get(i); ++ii)
             {
                 output.add(list.get(i));
             }
         }

         return output;
    }

    public static ArrayList<Integer> minToFront(ArrayList<Integer> list)
    {
        ArrayList<Integer> output = new ArrayList<>();

        int min = list.get(0);
        int index = 0;

        for (int i = 0; i < list.size(); ++i)
        {
            if (list.get(i) < min)
            {
                min = list.get(i);
                index = i;
            }
        }

        output.add(min);

        for (int i = 0; i < list.size(); ++i)
        {
            if (i != index)
            {
                output.add(list.get(i));
            }
        }

        return output;
    }

    public static void modifyClasses()
    {
        ArrayList<CalendarDate> dates = new ArrayList<>();

        dates.add(new CalendarDate(2006, 6, 4));
        dates.add(new CalendarDate(2023, 10, 31));
        dates.add(new CalendarDate(2021, 5, 6));
        dates.add(new CalendarDate(2006, 6, 5));

        System.out.println(dates);
        Collections.sort(dates); // very important
        System.out.println(dates);
    }

    public static ArrayList<String> reverseLines() throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File("D:\\Documents\\GitHub\\APComputerScience\\GoalSheet7\\reverseLines"));

        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            lines.add(scanner.nextLine());
        }

        for (int i = lines.size() - 1; i >= 0; --i)
        {
            String line = lines.get(i);
            String add = "";

            for (int ii = line.length() - 1; ii >= 0; --ii)
            {
                add += line.charAt(ii);
            }

            output.add(add);
        }

        return output;
    }
}
