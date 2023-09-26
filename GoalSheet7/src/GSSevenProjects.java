import java.io.*;
import java.util.*;
public class GSSevenProjects
{
    public static void main(String[] args)
    {
        System.out.println(scaleByK(new ArrayList<>(Arrays.asList(4, 2, 0, 1 ,3))));
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

    public static void minToFront(ArrayList<Integer> list)
    {
        ArrayList<Integer> output = new ArrayList<>();

        
    }
}
