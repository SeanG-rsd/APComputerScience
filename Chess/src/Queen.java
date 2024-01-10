import java.util.List;

public class Queen extends Piece
{

    public Queen(PieceColor color, int position) {
        super(PieceType.QUEEN, color, position);
    }

    public void GetMovesInDirection(int currentPos, int direction, List<Move> moves, Piece[] board, int expectedFileChange)
    {
        if (IsWithinBoard(currentPos) && position / 8 + expectedFileChange == currentPos / 8)
        {
            if (board[currentPos] != null && board[currentPos].pieceColor != pieceColor)
            {
                moves.add(new Move(this, currentPos, true));
            }
            else
            {
                GetMovesInDirection(currentPos + direction, direction, moves, board, expectedFileChange + (Integer.compare(expectedFileChange, 0)));
                moves.add(new Move(this, currentPos, false));
            }
        }
    }

    @Override
    public void GetMoves(Piece[] board, List<Move> moves)
    {
        GetMovesInDirection(position - 7, -7, moves, board, -1);
        GetMovesInDirection(position + 7, 7, moves, board, 1);
        GetMovesInDirection(position - 9, -9, moves, board, -1);
        GetMovesInDirection(position + 9, 9, moves, board, 1);
        GetMovesInDirection(position - 1, -1, moves, board, 0);
        GetMovesInDirection(position + 1, 1, moves, board, 0);
        GetMovesInDirection(position - 8, -8, moves, board, -1);
        GetMovesInDirection(position + 8, 8, moves, board, 1);
    }

    @Override
    public String GetName() {
        return "Queen";
    }
}