import java.io.FileNotFoundException;
import java.util.*;

public class LevenshteinSolver
{
    private static MapMaker mapMaker;
    public static void main(String[] args) throws FileNotFoundException
    {
        mapMaker = new MapMaker("D:\\Documents\\GitHub\\APComputerScience\\Levenshtein\\dictionaryCatDog");

        FindShortestPath(new LinkedList<>(List.of("cat")), "cat", "dog", 1);
    }

    public static void FindShortestPath(List<String> visited, String start, String end, int index)
    {
        List<String> neighbors = mapMaker.Get(start);
        //System.out.println(start);
        for (String s : neighbors)
        {
            if (!visited.contains(s))
            {
                List<String> newVisited = new LinkedList<>(visited);
                newVisited.add(s);

                if (Objects.equals(s, end))
                {
                    System.out.println("end : " + newVisited);
                    //return;
                }

                int i = index + 1;
                FindShortestPath(newVisited, s, end, i);
            }
        }
    }
}
