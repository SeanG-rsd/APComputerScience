import java.util.*;


public class MazeGenerator
{
    private final static int GRID_SIZE = 10;
    private static MazeCell[] grid;

    private final static String topWall = "--------";
    private final static String topDoor = "---   --";

    private final static String sideWall = "|       ";
    private final static String sideDoor = "        ";

    public static void main(String[] args)
    {
        InitializeGrid();

        CreateMaze();

        PrintMaze();
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

    private static void PrintMaze()
    {
        for (int row = 0; row < GRID_SIZE; row++)
        {
            PrintLine(row, topWall, topDoor, true, 3, "-");
            PrintLine(row, sideWall, sideDoor, false, -1, "|");
            PrintLine(row, sideWall, sideDoor, true, 1, "|");
            PrintLine(row, sideWall, sideDoor, false, -1, "|");
        }
        PrintLine(0, topWall, topDoor, false, -1, "-");
    }

    private static void PrintLine(int row, String wallLine, String doorLine, boolean possibleDoor, int doorPosition, String lineEnder)
    {
        for (int column = 0; column < GRID_SIZE; column++)
        {
            if (!possibleDoor)
            {
                System.out.print(wallLine);
            }
            else if (grid[row * GRID_SIZE + column].GetDoor(doorPosition))
            {
                System.out.print(doorLine);
            }
            else
            {
                System.out.print(wallLine);
            }
        }
        System.out.println(lineEnder);
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
                    //System.out.println("set door : " + currentPos + "->" + neighbor);
                    DFSMaze(visited, neighbor);
                }
            }
        }
    }

    /*

    ---   ---
    |       |

    |       |
    ---   ---

     */
}
