import java.util.*;

public abstract class Piece
{
    public enum PieceType {PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING};
    public enum PieceColor {WHITE, BLACK};

    protected PieceColor pieceColor;
    protected PieceType pieceType;

    public int[] flip = new int[]
            {
                    56,  57,  58,  59,  60,  61,  62,  63,
                    48,  49,  50,  51,  52,  53,  54,  55,
                    40,  41,  42,  43,  44,  45,  46,  47,
                    32,  33,  34,  35,  36,  37,  38,  39,
                    24,  25,  26,  27,  28,  29,  30,  31,
                    16,  17,  18,  19,  20,  21,  22,  23,
                    8,   9,  10,  11,  12,  13,  14,  15,
                    0,   1,   2,   3,   4,   5,   6,   7
            };
    public boolean hasMoved = false;

    public int position;

    public int timesMoved;

    public Piece(PieceType type, PieceColor color, int position)
    {
        pieceColor = color;
        pieceType = type;
        this.position = position;
    }

    public PieceType getPieceType()
    {
        return pieceType;
    }

    public PieceColor getPieceColor()
    {
        return pieceColor;
    }

    public abstract boolean IsInCheck(Piece[] board, ChessBoard chessBoard);

    public abstract void GetMoves(ChessBoard chessBoard, List<Move> moves, boolean caresAboutCheck);

    public abstract String GetName();

    public abstract Character GetChar();

    public abstract float GetValue();

    public abstract void MakeMove(Move move);

    public abstract void UndoMove(Move move);

    public boolean IsWithinBoard(int pos)
    {
        return pos >= 0 && pos < 64;
    }

    public int getPosition()
    {
        return position;
    }

    public String toString()
    {
        return GetName() + ", " + position;
    }

    public Character getChar()
    {
        return (char) (GetChar() + (pieceColor == PieceColor.BLACK ? 32 : 0));
    }
}
