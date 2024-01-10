import java.util.List;
public class Pawn extends Piece
{
    public boolean justMadeFirstDoubleMove;
    public Pawn(PieceColor color, int position)
    {
        super(PieceType.PAWN, color, position);
    }
    @Override
    public void GetMoves(Piece[] board, List<Move> moves)
    {
        if (IsWithinBoard(position + 8) && board[position + 8] == null) // forward 1
        {
            moves.add(new Move(this, position + 8, false));
        }
        if (IsWithinBoard(position + 16) && board[position + 16] == null && board[position + 8] == null && !hasMoved) // forward 2, only if it's the first move
        {
            moves.add(new Move(this, position + 16, false));
        }
        if (IsWithinBoard(position + 7) && position / 8 + 1 == (position + 7) / 8 && board[position + 7] != null && board[position + 7].pieceColor != pieceColor) // take diagonally to left
        {
            moves.add(new Move(this, position + 7, true));
        }
        if (IsWithinBoard(position + 9) && (position / 8) + 1 == (position + 9) / 8 && board[position + 9] != null && board[position + 9].pieceColor != pieceColor) // take diagonally to right
        {
            moves.add(new Move(this, position + 9, true));
        }
        //System.out.println(board[position + 9].pieceType != pieceType);
    }

    public String GetName()
    {
        return "Pawn";
    }
}
