import java.util.*;

public abstract class Piece
{
    public enum PieceType {PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING};
    public enum PieceColor {WHITE, BLACK};

    protected PieceColor pieceColor;
    protected PieceType pieceType;

    public boolean hasMoved;

    public int position;

    public Piece(PieceType type, PieceColor color, int position)
    {
        pieceColor = color;
        pieceType = type;
        this.position = position;
    }

    public void pieceHasMoved()
    {
        hasMoved = true;
    }

    public PieceType getPieceType()
    {
        return pieceType;
    }

    public PieceColor getPieceColor()
    {
        return pieceColor;
    }

    public abstract void GetMoves(Piece[] board, List<Move> moves);

    public abstract String GetName();

    public boolean IsWithinBoard(int pos)
    {
        return pos >= 0 && pos < 64;
    }

    public int getPosition()
    {
        return position;
    }

    private void GetRookMoves(Piece[] board)
    {
        List<Integer> legalMoves = new ArrayList<>();
    }
}
