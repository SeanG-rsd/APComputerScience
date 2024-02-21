import java.util.*;
public class MazeCell
{
    private boolean[] doors = new boolean[4];
    private int position;
    private final int GRID_SIZE;

    private final int[] doorOffsets;
    private final int[] yOffsets = new int[] {0,0,1,-1};

    public MazeCell(int position, int gridSize)
    {
        this.position = position;
        GRID_SIZE = gridSize;
        doorOffsets = new int[] {1,-1, GRID_SIZE, -GRID_SIZE};
    }

    public List<Integer> GetNeighborPositions()
    {
        List<Integer> positions = new ArrayList<>();

        for (int index = 0; index < doorOffsets.length; ++index)
        {
            int newPos = position + doorOffsets[index];
            if (IsWithinGrid(newPos) && newPos / GRID_SIZE == position / GRID_SIZE + yOffsets[index])
            {
                positions.add(newPos);
            }
        }

        return positions;
    }

    public int DoorPositionForNeighbor(int neighbor)
    {
        if (!IsWithinGrid(neighbor)) {return -1;}

        for (int i = 0; i < doorOffsets.length; ++i)
        {
            if (position + doorOffsets[i] == neighbor && position / GRID_SIZE + yOffsets[i] == neighbor / GRID_SIZE)
            {
                return i;
            }
        }

        System.out.println(position);
        throw new IllegalArgumentException(Integer.toString(neighbor));
        //return -1;
    }

    public boolean GetDoor(int position)
    {
        return doors[position];
    }
    public void SetDoor(int neighbor)
    {
        doors[DoorPositionForNeighbor(neighbor)] = true;
    }

    public void SetDoor(int position, boolean set)
    {
        doors[position] = set;
    }

    private boolean IsWithinGrid(int position)
    {
        return position >= 0 && position < (GRID_SIZE * GRID_SIZE);
    }
}
