// Sean Gutmann

import java.io.FileNotFoundException;
import java.util.*;
import java.util.Queue;

public class LevenshteinSolver
{
    private static MapMaker mapMaker;

    public static void main(String[] args) throws FileNotFoundException
    {
        //mapMaker = new MapMaker("/Users/gutmannse/Desktop/gutmannsean/APComputerScience/Levenshtein/dictionarySorted");
        mapMaker = new MapMaker("D:\\Documents\\GitHub\\APComputerScience\\Levenshtein\\dictionarySorted");

        String start = "dog"; // puppy
        String end = "quack"; // dog

        System.out.println("Finish map\n");
        List<List<String>> possiblePaths = new LinkedList<>();
        BFS(start, end, possiblePaths);

        for (List<String> s : possiblePaths) // print out all the paths
        {
            System.out.println(s);
        }

        System.out.println("\n# of Paths : " + possiblePaths.size());
    }

    private static void BFS(String start, String end, List<List<String>> output) // BFS depth
    {
        LevNode first = mapMaker.Get(start); // initialize queue
        Queue<LevNode>  queue = new LinkedList<>();
        Queue<List<String>> lists = new LinkedList<>();
        lists.add(new ArrayList<>(List.of(start)));
        queue.add(first);
        first.SetDepth(0);
        int length = 9999;

        while (!queue.isEmpty())
        {
            LevNode node = queue.element(); // first element
            List<String> list = lists.element();

            for (LevNode neighbor : node.GetNeighbors()) // get neighbors
            {
                List<String> l = new ArrayList<>(list);
                l.add(neighbor.key);
                if (Objects.equals(neighbor.key, end)) // found the end
                {
                    if (node.GetDepth() + 1 > length)
                    {
                        continue;
                    }

                    output.add(l);
                    length = node.depth + 1;
                }
                else if (neighbor.depth == -1 || node.GetDepth() + 1 <= neighbor.GetDepth()) // set depth of node to first element + 1
                {
                    lists.add(l);
                    queue.add(neighbor);
                    neighbor.SetDepth(node.GetDepth() + 1);
                }
            }

            lists.remove(); // remove the current one we're checking
            queue.remove();
        }
    }
}
