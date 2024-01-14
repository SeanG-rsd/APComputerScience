import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{

    public King(PieceColor color, int position)
    {
        super(PieceType.KING, color, position);
    }

    public boolean IsInCheck(Piece[] board, ChessBoard chessBoard)
    {
        return GetOpponentAttackedSpots(board, chessBoard).contains(position);
    }

    private int[] MOVES = new int[]
            {
                    1,8,9,7,-1,-7,-8,-9
            };

    private int[] FILE_CHANGE = new int[]
            {
              0,1,1,1,0,-1,-1,-1
            };

    private List<Integer> GetOpponentAttackedSpots(Piece[] board, ChessBoard chessBoard)
    {
        List<Move> attackedSquares = new ArrayList<>();
        for (Piece piece : board)
        {
            if (piece != null && piece.pieceColor != pieceColor && piece.pieceType != PieceType.KING)
            {
                //piece.GetMoves(chessBoard, attackedSquares, true);
            }
        }

        List<Integer> attacked = new ArrayList<>();
        for (Move m : attackedSquares)
        {
            attacked.add(m.getPosition());
        }

        return attacked;
    }

    private boolean CheckForCastling(Piece[] board, int direction, int currentPos)
    {
        if (currentPos / 8 == position / 8)
        {
            if (board[currentPos] == null)
            {
                return CheckForCastling(board, direction, currentPos + direction);
            }
            else if (board[currentPos].pieceType == PieceType.ROOK && board[currentPos].pieceColor == pieceColor && !board[currentPos].hasMoved)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public void GetMoves(ChessBoard chessBoard, List<Move> moves, boolean isTempBoard)
    {
        Piece[] board = chessBoard.GetBoard();
        int[] possibleMoves = new int[MOVES.length];
        for (int i = 0; i < MOVES.length; ++i)
        {
            possibleMoves[i] = position + MOVES[i];
        }

        List<Integer> notAllowed = GetOpponentAttackedSpots(board, chessBoard);

        for (int i = 0; i < possibleMoves.length; ++i) // normal 8 moves in a square
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

        if (!hasMoved)
        {
            if (CheckForCastling(board, -1, position - 1))
            {
                moves.add(new Move(this, position - 2, false));
            }
            if (CheckForCastling(board, 1, position + 1))
            {
                moves.add(new Move(this, position + 2, false));
            }
        }
    }

    @Override
    public String GetName() {
        return "King";
    }

    public Character GetChar()
    {
        return 'K';
    }
}