import java.util.List;

public class Knight extends Piece
{

    public Knight(PieceColor color, int position)
    {
        super(PieceType.KNIGHT, color, position);
    }

    private final int[] KNIGHT_MOVES = new int[]
            {
                    15,17,10,6
            };

    private final int[] KNIGHT_FILE_CHANGE = new int[]
            {
                    2,2,1,1
            };

    @Override
    public void GetMoves(Piece[] board, List<Move> legalMoves)
    {
        for (int i = 0; i < KNIGHT_MOVES.length; ++i)
        {
            int move = KNIGHT_MOVES[i];
            if (IsWithinBoard(position + move) && (position + move) / 8 == position / 8 + KNIGHT_FILE_CHANGE[i])
            {
                legalMoves.add(new Move(this, position + move, board[position + move] != null));
            }
            if (IsWithinBoard(position - move) && (position - move) / 8 == position / 8  - KNIGHT_FILE_CHANGE[i])
            {
                legalMoves.add(new Move(this, position - move, board[position - move] != null));
            }
        }
    }

    public String GetName()
    {
        return "Knight";
    }
}
