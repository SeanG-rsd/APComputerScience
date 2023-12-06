import java.io.FileNotFoundException;
import java.util.*;
import java.util.Queue;

public class LevenshteinSolver
{
    private static MapMaker mapMaker;
    private static int minIndex;

    public static void main(String[] args) throws FileNotFoundException
    {
        mapMaker = new MapMaker("/Users/gutmannse/Desktop/gutmannsean/APComputerScience/Levenshtein/dictionarySorted");
        //mapMaker = new MapMaker("D:\\Documents\\GitHub\\APComputerScience\\Levenshtein\\dictionarySorted");

        String start = "dog"; // puppy
        String end = "smart"; // dog

        minIndex = start.length() + end.length() - 1;
        System.out.println("Finish map");
        BFS(start, end);
        List<List<String>> possiblePaths = new LinkedList<>();

        //Threading(start, end, possiblePaths);
        //possiblePaths = FindShortestPath(start, end); // BFS
        //ThreadingBFS(start, end, possiblePaths);
        //FindShortestPath(new LinkedList<>(List.of(mapMaker.Get(start))), start, end, 0, possiblePaths);

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

    private static void BFS(String start, String end)
    {
        LevNode first = mapMaker.Get(start);
        Queue<LevNode>  queue = new LinkedList<>();
        queue.add(first);
        first.SetDepth(0);
        int length = 9999;
        int count = 0;

        while (!queue.isEmpty())
        {
            LevNode node = queue.element();

            for (LevNode neighbor : node.GetNeighbors())
            {
                if (Objects.equals(neighbor.key, end))
                {
                    if (node.GetDepth() + 1 > length)
                    {
                        continue;
                    }
                    System.out.println("path : " + (node.depth + 1));
                    length = node.depth + 1;
                    count++;
                }
                else if (neighbor.depth == -1 || node.GetDepth() + 1 <= neighbor.GetDepth())
                {
                    queue.add(neighbor);
                    neighbor.SetDepth(node.GetDepth() + 1);
                    //System.out.println("setDepth : " + mapMaker.Get(neighbor.key).GetDepth());
                }

            }

            queue.remove();

        }

        System.out.println("\n# of Paths : " + count);
    }

    /*public static void Threading(String start, String end, List<List<String>> possiblePaths)
    {
        LevNode node = mapMaker.Get(start);
        List<String> firstNeighbors = node.GetNeighbors();

        List<LevThread> threads = new LinkedList<>();

        for (String s : firstNeighbors)
        {
            System.out.println(s);
            LevThread thread = new LevThread(new LinkedList<>(List.of(start, s)), s, end, mapMaker, 1);
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

    public static int GetCurrentShortest(List<List<String>> possiblePaths, int m)
    {
        int min = m;
        for (List<String> s : possiblePaths)
        {
            if (s.size() < min)
            {
                //System.out.println(s.size());
                min = s.size();
            }
        }

        return min;
    }

    public static List<List<String>> FindShortestPath(String start, String end) // BFS
    {
        // queues and should not ask for length

        //System.out.println(breadthPaths.get(0));
        List<List<String>> output = new ArrayList<>();
        Queue<List<String>> breadthPaths = new LinkedList<>();
        breadthPaths.add(new ArrayList<>(List.of(start)));

        while (!breadthPaths.isEmpty())
        {
            //System.out.println(list);
            List<String> list = breadthPaths.element();
            breadthPaths.remove();
            String last = list.get(list.size() - 1);
            LevNode node = mapMaker.Get(last);
            List<String> neighbors = node.GetNeighbors();

            for (String s : neighbors)
            {
                if (Objects.equals(s, end))
                {
                    list.add(s);
                    output.add(list);
                    System.out.println(list);
                }
                else if (!list.contains(s))
                {
                    List<String> newList = new ArrayList<>(list);
                    newList.add(s);
                    breadthPaths.add(newList);
                    //System.out.println(newList);
                }
            }
        }

        return output;
    }

    public static void FindShortestPath(List<LevNode> visited, String start, String end, int index, List<List<String>> possiblePaths) // depth first attempt
    {
        //System.out.print((index + 1) + " : ");
        //for (int i = 0; i < index; ++i) { System.out.print("  "); }
        //System.out.println(start + " : " + index);
        LevNode node = mapMaker.Get(start);
        List<String> neighbors = node.GetNeighbors();
        //System.out.println(start);

        if (neighbors.contains(end))
        {
            List<LevNode> newVisited = new LinkedList<>(visited);
            newVisited.add(new LevNode(mapMaker.Get(end)));

            possiblePaths.add(Path(newVisited));
            System.out.println("end : " + Path(newVisited));
            return;
        }

        if (index > GetCurrentShortest(possiblePaths, minIndex) - 1)
        {
            return;
        }

        for (String s : neighbors)
        {
            LevNode n = mapMaker.Get(s);
            //System.out.println(visited.contains(n) + " : " + s);
            //System.out.println("vis : " + Path(visited));
            if (!doesContain(visited, n) && !n.IsDead())
            {
                List<LevNode> newVisited = new LinkedList<>(visited);
                newVisited.add(new LevNode(n));

                int i = index + 1;
                FindShortestPath(newVisited, s, end, i, possiblePaths);
            }
        }

        if (neighbors.isEmpty() || NeighborsAreDead(node))
        {
            //System.out.println("Set " + start + " to dead");
            //node.changeDead(true);
        }
    }

    private static boolean doesContain(List<LevNode> nodes, LevNode node)
    {
        for (LevNode n : nodes)
        {
            if (Objects.equals(n.GetKey(), node.GetKey()))
            {
                return true;
            }
        }

        return false;
    }

    private static boolean NeighborsAreDead(LevNode node)
    {
        for (String s : node.GetNeighbors())
        {
            if (!mapMaker.Get(s).IsDead())
            {
                return false;
            }
        }

        return true;
    }

    private static List<String> Path(List<LevNode> nodes)
    {
        List<String> output = new LinkedList<>();
        for (LevNode n : nodes)
        {
            output.add(n.GetKey());
        }
        return output;
    }
}



class LevThread extends Thread
{
    List<String> visited = new LinkedList<>();
    String start;
    String end;

    static int minIndex;
    static List<List<String>> possiblePaths = new LinkedList<>();

    static List<String> startList = new ArrayList<>();

    private static int index;

    private static MapMaker mapMaker;

    public LevThread(List<String> visited, String s, String e, MapMaker map, int i)
    {
        index = i;
        mapMaker = map;
        start = s;
        end = e;
        this.visited = visited;
        minIndex = e.length() + s.length() - 1;
    }

    public LevThread(String e, List<String> sList, String s, MapMaker map)
    {
        start = s;
        end = e;
        startList = new ArrayList<>(sList);
        mapMaker = map;
    }

    public static void FindShortestPath(List<String> visited, String start, String end, int index, List<List<String>> possiblePaths)
    {
        //System.out.print((index + 1) + " : ");
        //for (int i = 0; i < index; ++i) { System.out.print("  "); }
        //System.out.println(start + " : " + index);
        LevNode node = mapMaker.Get(start);
        List<String> neighbors = node.GetNeighbors();
        //System.out.println(start);

        if (index > LevenshteinSolver.GetCurrentShortest(possiblePaths, minIndex) - 1)
        {
            return;
        }

        for (String s : neighbors)
        {
            if (!visited.contains(s) && !mapMaker.Get(s).IsDead())
            {
                List<String> newVisited = new LinkedList<>(visited);
                newVisited.add(s);

                if (Objects.equals(s, end))
                {
                    possiblePaths.add(newVisited);
                    System.out.println("path found : " + newVisited.get(1) + ", current shortest : " + LevenshteinSolver.GetCurrentShortest(possiblePaths, minIndex));
                    //System.out.println(newVisited);
                    //System.out.println(index);
                }

                /*for (int i = 0; i < newVisited.size() - 2; ++i)
                {
                    if (mapMaker.Get(newVisited.get(i)).contains(s))
                    {
                        isNeighborOfLast = true;
                        break;
                    }
                }

                if (!isNeighborOfLast)
                {
                    int i = index + 1;
                    FindShortestPath(newVisited, s, end, i, possiblePaths);
                }

                int i = index + 1;
                FindShortestPath(newVisited, s, end, i, possiblePaths);
            }
        }

        mapMaker.Get(start).changeDead(true);
    }*/

    /*public static void FindShortestPathBFS(String start, String end)
    {
        List<List<String>> breadthPaths = new ArrayList<>();
        breadthPaths.add(new ArrayList<>(List.of(start)));
        int min = 999999;
        System.out.println(breadthPaths.get(0));
        List<List<String>> output = new ArrayList<>();
        while (!breadthPaths.isEmpty())
        {
            List<List<String>> newBreadthPaths = new ArrayList<>();

            for (List<String> list : breadthPaths)
            {
                //System.out.println(list);
                String last = list.get(list.size() - 1);
                LevNode node = mapMaker.Get(last);
                List<String> neighbors = node.GetNeighbors();

                for (String s : neighbors)
                {
                    if (Objects.equals(s, end) && list.size() + 1 <= min)
                    {
                        list.add(s);
                        min = list.size();
                        output.add(list);
                        System.out.println(list);
                    }
                    else if (!list.contains(s) && list.size() < min)
                    {
                        List<String> newList = new ArrayList<>(list);
                        newList.add(s);
                        newBreadthPaths.add(newList);
                        System.out.println(newList);
                    }
                }
            }

            breadthPaths.clear();
            breadthPaths.addAll(newBreadthPaths);
        }

        for (List<String> l : output)
        {
            List<String> c = new ArrayList<>(startList);
            c.addAll(l);
            possiblePaths.add(c);
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
        //FindShortestPathBFS(start, end);
    }*/
}
