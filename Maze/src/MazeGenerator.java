import java.util.*;


public class MazeGenerator
{
    private final static int GRID_SIZE = 10;
    private static MazeCell[] grid;
    private static int[] wilsonGrid;

    private final static String topWall = "--------";
    private final static String topDoor = "---   --";

    private final static String sideWall = "|       ";
    private final static String sideDoor = "        ";


    private static int[] wilsonMaze;

    public static void main(String[] args)
    {
        InitializeGrid();

        CreateWilsonMaze();

        PrintMaze();

        SolveMaze();
    }

    private static void InitializeGrid()
    {
        int totalCount = GRID_SIZE * GRID_SIZE;
        grid = new MazeCell[totalCount];
        wilsonGrid = new int[totalCount];

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

    private static int[] neighborChange;
    private static int[] neighborRowChange;

    private static void CreateWilsonMaze()
    {
        neighborChange = new int[] {GRID_SIZE, -GRID_SIZE, 1, -1};
        neighborRowChange = new int[] {1, -1, 0, 0};

        wilsonMaze = new int[GRID_SIZE * GRID_SIZE];

        int startCell = (int)(Math.random() * GRID_SIZE * GRID_SIZE);
        int targetCell;

        do
        {
            targetCell = (int)(Math.random() * GRID_SIZE * GRID_SIZE);
        }
        while (targetCell == startCell);

        System.out.println(startCell + " : " + targetCell);

        wilsonMaze[targetCell] = 2;

        WilsonAlgorithm(startCell, new ArrayList<>());
    }

    private static void SolveMaze()
    {
        int startCell = 0;
        List<Integer> currentPath = new ArrayList<>(List.of(startCell));
        int endCell = 99;

        DFSSolveMaze(new ArrayList<>(List.of(startCell)), startCell, currentPath, endCell);

        System.out.print(currentPath);
    }

    private static List<Integer> GetNeighbors(int cell)
    {
        List<Integer> output = new ArrayList<>();

        for (int i = 0; i < neighborChange.length; i++)
        {
            int newCell = cell + neighborChange[i];
            if (newCell / GRID_SIZE == cell / GRID_SIZE + neighborRowChange[i] && newCell >= 0 && newCell < wilsonMaze.length)
            {
                output.add(newCell);
            }
        }

        return output;
    }

    private static boolean WilsonAlgorithm(int currentCell, List<Integer> currentPath)
    {
        if (wilsonMaze[currentCell] == 2) // found maze piece
        {
            currentPath.add(currentCell);
            for (int i : currentPath)
            {
                wilsonMaze[i] = 2;
            }
            System.out.println(currentPath);
            currentPath.clear();

            return true;
        }
        else if (wilsonMaze[currentCell] == 1) // created
        {
            return false;
        }
        else
        {
            List<Integer> neighbors = GetNeighbors(currentCell);
            Collections.shuffle(neighbors);
            wilsonMaze[currentCell] = 1;
            currentPath.add(currentCell);

            for (int neighbor : neighbors)
            {
                if (WilsonAlgorithm(neighbor, currentPath))
                {
                    List<Integer> possibleNewCells = new ArrayList<>();
                    for (int i = 0; i < wilsonMaze.length; i++)
                    {
                        if (wilsonMaze[i] == 0)
                        {
                            possibleNewCells.add(i);
                        }
                    }

                    if (!possibleNewCells.isEmpty())
                    {
                        int newCell = possibleNewCells.get((int) (Math.random() * possibleNewCells.size()));

                        WilsonAlgorithm(newCell, currentPath);
                    }
                    else
                    {
                        return false;
                    }
                }
            }

            if (!currentPath.isEmpty())
            {
                wilsonMaze[currentCell] = 0;
                currentPath.removeLast();
            }
            return false;
        }
    }

    private static void DFSSolveMaze(List<Integer> visited, int currentPos, List<Integer> currentPath, int endCell)
    {
        List<Integer> neighbors = grid[currentPos].GetNeighborPositions();

        if (currentPos == endCell)
        {
            return;
        }

        if (!neighbors.isEmpty())
        {
            for (int n : neighbors)
            {
                if (!visited.contains(n) && grid[currentPos].GetDoor(grid[currentPos].DoorPositionForNeighbor(n)) && !visited.contains(endCell))
                {
                    currentPath.add(n);
                    visited.add(n);

                    DFSSolveMaze(visited, n, currentPath, endCell);
                }
            }
        }
        else
        {
            currentPath.removeLast();
        }
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
