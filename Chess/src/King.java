import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{


    public int[] kingTable = new int[]
            {
                    -40, -40, -40, -40, -40, -40, -40, -40,
                    -40, -40, -40, -40, -40, -40, -40, -40,
                    -40, -40, -40, -40, -40, -40, -40, -40,
                    -40, -40, -40, -40, -40, -40, -40, -40,
                    -40, -40, -40, -40, -40, -40, -40, -40,
                    -40, -40, -40, -40, -40, -40, -40, -40,
                    -20, -20, -20, -20, -20, -20, -20, -20,
                    0,  20,  40, -20,   0, -20,  40,  20
            };
    public King(PieceColor color, int position)
    {
        super(PieceType.KING, color, position);
    }

    public boolean IsInCheck(Piece[] board, ChessBoard chessBoard)
    {
        List<Move> attackedSqaures = new ArrayList<>();
        chessBoard.GetOpponentAttackedSpots(pieceColor, attackedSqaures);
        for (Move m : attackedSqaures)
        {
            if (m.getPosition() == position)
            {
                return true;
            }
        }
        return false;
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
                piece.GetMoves(chessBoard, attackedSquares, false);
            }
        }

        List<Integer> attacked = new ArrayList<>();
        for (Move m : attackedSquares)
        {
            attacked.add(m.getPosition());
        }

        return attacked;
    }

    private Piece CheckForCastling(Piece[] board, int direction, int currentPos)
    {
        if (currentPos / 8 == position / 8)
        {
            if (IsWithinBoard(currentPos))
            {
                if (board[currentPos] == null)
                {
                    return CheckForCastling(board, direction, currentPos + direction);
                }
                else if (board[currentPos].pieceType == PieceType.ROOK && board[currentPos].pieceColor == pieceColor && !board[currentPos].hasMoved)
                {
                    return board[currentPos];
                }
            }
        }

        return null;
    }

    @Override
    public void GetMoves(ChessBoard chessBoard, List<Move> moves, boolean caresAboutCheck)
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
            if (IsWithinBoard(possibleMoves[i]))
            {
                if (possibleMoves[i] / 8 == position / 8 + FILE_CHANGE[i] && !notAllowed.contains(possibleMoves[i]))
                {
                    if (board[possibleMoves[i]] == null)
                    {
                        moves.add(new Move(this, possibleMoves[i], false));
                    }
                    else if (board[possibleMoves[i]] != null && board[possibleMoves[i]].pieceColor != pieceColor)
                    {
                        Move newMove = new Move(this, possibleMoves[i], true);
                        newMove.SetTake(board[possibleMoves[i]]);
                        moves.add(newMove);
                    }
                }
            }
        }

        if (!hasMoved && !IsInCheck(board, chessBoard))
        {
            if (CheckForCastling(board, -1, position - 1) != null)
            {
                Move castle = new Move(this, position - 2, false);
                castle.SetCastle(CheckForCastling(board, -1, position - 1), true, position - 1, this, position - 1);
                moves.add(castle);

            }
            if (CheckForCastling(board, 1, position + 1) != null)
            {
                Move castle = new Move(this, position + 2, false);
                castle.SetCastle(CheckForCastling(board, 1, position + 1), true, position + 1, this, position + 1);
                moves.add(castle);
            }
        }
    }

    @Override
    public void MakeMove(Move move) {
        hasMoved = true;
        timesMoved++;
    }

    @Override
    public void UndoMove(Move move) {
        timesMoved--;
        if (timesMoved == 0)
        {
            hasMoved = false;
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

    public float GetValue()
    {
        float value = 0;
        if (pieceColor == PieceColor.WHITE)
        {
            value += ((float) kingTable[position] / 100);
        }
        else
        {
            value += ((float) kingTable[flip[position]] / 100);
        }

        return value;
    }
}