import java.util.*;

public class Piece
{
    public enum PieceType {PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING};
    public enum PieceColor {WHITE, BLACK};

    private PieceColor pieceColor;
    private PieceType pieceType;

    public boolean hasMoved;

    private int index;

    public Piece(PieceType type, PieceColor color)
    {
        pieceColor = color;
        pieceType = type;
    }

    public void setIndex(int i)
    {
        index = i;
    }

    public int getIndex()
    {
        return index;
    }

    public void pieceHasMoved()
    {
        hasMoved = true;
    }

    public List<Integer> possibleMoves()
    {
        return new ArrayList<>(List.of(0, 10, 20, 30));
    }

    public PieceType getPieceType()
    {
        return pieceType;
    }

    public PieceColor getPieceColor()
    {
        return pieceColor;
    }
}
