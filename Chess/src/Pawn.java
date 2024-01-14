import java.util.List;
public class Pawn extends Piece
{
    public boolean justMadeFirstDoubleMove;
    public Pawn(PieceColor color, int position)
    {
        super(PieceType.PAWN, color, position);
    }

    @Override
    public boolean IsInCheck(Piece[] board, ChessBoard chessBoard) {
        return false;
    }

    @Override
    public void GetMoves(ChessBoard chessBoard, List<Move> legalMoves, boolean caresAboutCheck)
    {
        Piece[] board = chessBoard.GetBoard();
        int direction = pieceColor == PieceColor.BLACK ? 1 : -1;

        if (IsWithinBoard(position + 8) && board[position + (8 * direction)] == null) // forward 1
        {
            Move newMove = new Move(this, position + (8 * direction), false);
            legalMoves.add(newMove);
        }
        if (IsWithinBoard(position + (16 * direction)) && board[position + (16 * direction)] == null && board[position + (16 * direction)] == null && !hasMoved) // forward 2, only if it's the first move
        {
            Move newMove = new Move(this, position + (16 * direction), false);
            legalMoves.add(newMove);
        }
        if (IsWithinBoard(position + (7 * direction)) && position / 8 + direction == (position + (7 * direction)) / 8 && board[position + (7 * direction)] != null && board[position + (7 * direction)].pieceColor != pieceColor) // take diagonally to left
        {
            Move newMove = new Move(this, position + 7, true);
            legalMoves.add(newMove);
        }
        if (IsWithinBoard(position + (9 * direction)) && (position / 8) + direction == (position + (9 * direction)) / 8 && board[position + (9 * direction)] != null && board[position + (9 * direction)].pieceColor != pieceColor) // take diagonally to right
        {
            Move newMove = new Move(this, position + (9 * direction), true);
            legalMoves.add(newMove);
        }
        //System.out.println(board[position + 9].pieceType != pieceType);
    }

    public String GetName()
    {
        return "Pawn";
    }

    public Character GetChar()
    {
        return 'P';
    }
}
