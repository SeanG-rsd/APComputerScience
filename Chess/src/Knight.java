import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public int[] knightTable = new int[]
            {
                    -10, -10, -10, -10, -10, -10, -10, -10,
                    -10,   0,   0,   0,   0,   0,   0, -10,
                    -10,   0,   5,   5,   5,   5,   0, -10,
                    -10,   0,   5,  10,  10,   5,   0, -10,
                    -10,   0,   5,  10,  10,   5,   0, -10,
                    -10,   0,   5,   5,   5,   5,   0, -10,
                    -10,   0,   0,   0,   0,   0,   0, -10,
                    -10, -30, -10, -10, -10, -10, -30, -10
            };

    public Knight(PieceColor color, int position)
    {
        super(PieceType.KNIGHT, color, position);
    }

    public Knight(Piece copy)
    {
        super(PieceType.BISHOP, copy.pieceColor, copy.position);
        hasMoved = copy.hasMoved;
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
    public boolean IsInCheck(Piece[] board, ChessBoard chessBoard) {
        return false;
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
    public void GetMoves(ChessBoard chessBoard, List<Move> legalMoves, boolean caresAboutCheck)
    {
        Piece[] board = chessBoard.GetBoard();
        for (int i = 0; i < KNIGHT_MOVES.length; ++i)
        {
            int move = KNIGHT_MOVES[i];
            if (IsWithinBoard(position + move) && (position + move) / 8 == position / 8 + KNIGHT_FILE_CHANGE[i])
            {
                if (board[position + move] != null && board[position + move].pieceColor != pieceColor)
                {
                    Move newMove = new Move(this, position + move, true);
                    newMove.SetTake(board[position + move]);
                    legalMoves.add(newMove);
                }
                else if (board[position + move] == null)
                {
                    Move newMove = new Move(this, position + move, false);
                    legalMoves.add(newMove);
                }
            }
            if (IsWithinBoard(position - move) && (position - move) / 8 == position / 8  - KNIGHT_FILE_CHANGE[i])
            {
                if (board[position - move] != null && board[position - move].pieceColor != pieceColor)
                {
                    Move newMove = new Move(this, position - move, true);
                    newMove.SetTake(board[position - move]);
                    legalMoves.add(newMove);
                }
                else if (board[position - move] == null)
                {
                    Move newMove = new Move(this, position - move, false);
                    legalMoves.add(newMove);
                }
            }
        }
    }

    public String GetName()
    {
        return "Knight";
    }

    public Character GetChar()
    {
        return 'N';
    }

    public float GetValue()
    {
        float value = 3;
        if (pieceColor == PieceColor.WHITE)
        {
            value += ((float) knightTable[position] / 100);
        }
        else
        {
            value += ((float) knightTable[flip[position]] / 100);
        }

        return value;
    }
}
