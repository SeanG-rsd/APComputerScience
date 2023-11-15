import java.io.FileNotFoundException;
import java.util.*;

public class LevenshteinSolver
{
    private static MapMaker mapMaker;
    public static void main(String[] args) throws FileNotFoundException
    {
        mapMaker = new MapMaker("D:\\Documents\\GitHub\\APComputerScience\\Levenshtein\\dictionarySorted");

        String start = "dog";
        String end = "smart";

        List<List<String>> possiblePaths = new LinkedList<>();
        Threading(start, end, possiblePaths);
        //FindShortestPath(new LinkedList<>(List.of("dog")), "dog", "smart", 0, possiblePaths);

        int min = 999999;
        List<List<String>> shortestPaths = new LinkedList<>();
        for (List<String> s : possiblePaths)
        {
            if (s.size() < min)
            {
                shortestPaths.clear();
                min = s.size();
                shortestPaths.add(s);
            }
            else if (s.size() == min)
            {
                shortestPaths.add(s);
            }
        }

        for (List<String> s : shortestPaths)
        {
            System.out.println(s);
        }

        System.out.println("\n# of Paths : " + shortestPaths.size());

    }

    public static void Threading(String start, String end, List<List<String>> possiblePaths)
    {
        List<String> firstNeighbors = mapMaker.Get(start);

        List<LevThread> threads = new LinkedList<>();

        for (String s : firstNeighbors)
        {
            System.out.println(s);
            LevThread thread = new LevThread(new LinkedList<>(List.of(start, s)), s, end, mapMaker, 0);
            thread.start();

            threads.add(thread);
        }

        for (LevThread thread : threads)
        {
            try
            {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            possiblePaths.addAll(thread.GetPaths());
        }
    }

    public static void FindShortestPath(List<String> visited, String start, String end, int index, List<List<String>> possiblePaths)
    {
        //System.out.print((index + 1) + " : ");
        //for (int i = 0; i < index; ++i) { System.out.print("  "); }
        //System.out.println(start + " : " + index);
        List<String> neighbors = mapMaker.Get(start);
        //System.out.println(start);
        if (index > 5)
        {
            return;
        }
        for (String s : neighbors)
        {
            if (!visited.contains(s))
            {
                List<String> newVisited = new LinkedList<>(visited);
                newVisited.add(s);

                if (Objects.equals(s, end))
                {
                    possiblePaths.add(newVisited);
                    //System.out.println("end : " + newVisited);
                    //return;
                }

                int i = index + 1;
                FindShortestPath(newVisited, s, end, i, possiblePaths);
            }
        }
    }
}

class LevThread extends Thread
{
    List<String> visited = new LinkedList<>();
    String start;
    String end;
    List<List<String>> possiblePaths = new LinkedList<>();

    private static int index;

    private static MapMaker mapMaker;

    public LevThread(List<String> visited, String s, String e, MapMaker map, int i)
    {
        index = i;
        mapMaker = map;
        start = s;
        end = e;
        this.visited = visited;
    }

    public static void FindShortestPath(List<String> visited, String start, String end, int index, List<List<String>> possiblePaths)
    {
        //System.out.print((index + 1) + " : ");
        //for (int i = 0; i < index; ++i) { System.out.print("  "); }
        //System.out.println(start + " : " + index);
        List<String> neighbors = mapMaker.Get(start);
        //System.out.println(start);

        if (index > 5)
        {
            return;
        }

        for (String s : neighbors)
        {
            if (!visited.contains(s))
            {
                List<String> newVisited = new LinkedList<>(visited);
                newVisited.add(s);

                if (Objects.equals(s, end))
                {
                    possiblePaths.add(newVisited);
                    System.out.println("path found");
                }

                int i = index + 1;
                FindShortestPath(newVisited, s, end, i, possiblePaths);
            }
        }
    }

    public List<List<String>> GetPaths()
    {
        return possiblePaths;
    }

    @Override
    public void run()
    {
        FindShortestPath(visited, start, end, index, possiblePaths);
    }
}
