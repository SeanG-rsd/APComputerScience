import java.util.*;


public class MazeGenerator
{
    private final static int GRID_SIZE = 10;
    private static MazeCell[] grid;

    public static void main(String[] args)
    {
        InitializeGrid();

        CreateMaze();
    }

    private static void InitializeGrid()
    {
        int totalCount = GRID_SIZE * GRID_SIZE;
        grid = new MazeCell[totalCount];

        for (int position = 0; position < totalCount; position++)
        {
            grid[position] = new MazeCell(position, GRID_SIZE);
        }
    }

    private static void CreateMaze()
    {
        int startCell = (int)(Math.random() * GRID_SIZE * GRID_SIZE);
        startCell = 0;

        DFSMaze(new ArrayList<>(List.of(startCell)), startCell);

    }

    private static void DFSMaze(List<Integer> visited, int currentPos)
    {
        List<Integer> neighbors = grid[currentPos].GetNeighborPositions();
        Collections.shuffle(neighbors);

        if (!neighbors.isEmpty())
        {
            for (int neighbor : neighbors)
            {
                if (!visited.contains(neighbor))
                {
                    visited.add(neighbor);
                    grid[currentPos].SetDoor(neighbor);
                    grid[neighbor].SetDoor(currentPos);
                    System.out.println("set door : " + currentPos + "->" + neighbor);
                    DFSMaze(visited, neighbor);
                }
            }
        }
    }

    /*

    ---------
    |       |
    |       |
    |       |
    ---   ---

     */
}
