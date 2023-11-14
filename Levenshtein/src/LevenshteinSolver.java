import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;

public class LevenshteinSolver
{
    public static void main(String[] args) throws FileNotFoundException
    {
        MapMaker mapMaker = new MapMaker("D:\\Documents\\GitHub\\APComputerScience\\Levenshtein\\dictionarySorted");

        FindShortestPath(mapMaker, "cat", "dog");
    }

    public static void FindShortestPath(MapMaker mapMaker, String start, String end)
    {
        LinkedList<String> visited = new LinkedList<>();
        visited.add(start);

        LinkedList<String> checking = new LinkedList<>();
        checking.add(start);

        Iterator<String> i = checking.iterator();

        while (i.hasNext())
        {
            String w = i.next();

            checking.addAll(mapMaker.Get(w));


        }

    }
}
