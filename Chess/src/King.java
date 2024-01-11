import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{

    public King(PieceColor color, int position)
    {
        super(PieceType.KING, color, position);
    }

    private int[] MOVES = new int[]
            {
                    1,8,9,7,-1,-7,-8,-9
            };

    private int[] FILE_CHANGE = new int[]
            {
              0,1,1,1,0,-1,-1,-1
            };

    private List<Integer> GetBlackAttackedSpots(Piece[] board)
    {
        List<Move> attackedSquares = new ArrayList<>();
        for (Piece piece : board)
        {
            if (piece != null && piece.pieceColor != pieceColor && piece.pieceType != PieceType.KING)
            {
                piece.GetMoves(board, attackedSquares);
            }
        }

        List<Integer> attacked = new ArrayList<>();
        for (Move m : attackedSquares)
        {
            attacked.add(m.getPosition());
        }
    }

    @Override
    public void GetMoves(Piece[] board, List<Move> moves)
    {
        int[] possibleMoves = new int[MOVES.length];
        for (int i = 0; i < MOVES.length; ++i)
        {
            possibleMoves[i] = position + MOVES[i];
        }

        List<Integer> notAllowed = GetBlackAttackedSpots(board);

        for (int i = 0; i < possibleMoves.length; ++i)
        {
            if (possibleMoves[i] / 8 == position / 8 + FILE_CHANGE[i] && !notAllowed.contains(possibleMoves[i]))
            {
                if (board[possibleMoves[i]] == null)
                {
                    moves.add(new Move(this, possibleMoves[i], false));
                }
                else if (board[possibleMoves[i]] != null && board[possibleMoves[i]].pieceColor != pieceColor)
                {
                    moves.add(new Move(this, possibleMoves[i], true));
                }
            }
        }
    }

    @Override
    public String GetName() {
        return "King";
    }
}