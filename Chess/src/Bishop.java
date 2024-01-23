import java.util.Dictionary;
import java.util.List;

public class Bishop extends Piece
{

    public Bishop(PieceColor color, int position) {
        super(PieceType.BISHOP, color, position);
    }

    public static int[] DIRECTION = new int[]
            {
                    -7,7,9,-9
            };

    public static int[] FILE_CHANGE = new int[]
            {
                    -1,1,1,-1
            };

    public void GetMovesInDirection(int currentPos, int direction, List<Move> moves, Piece[] board, int expectedFileChange)
    {
        if (IsWithinBoard(currentPos) && (position / 8 + expectedFileChange == currentPos / 8))
        {
            if (board[currentPos] != null && board[currentPos].pieceColor != pieceColor)
            {
                Move newMove = new Move(this, currentPos, true);
                newMove.SetTake(board[currentPos]);
                moves.add(newMove);
            }
            else if (board[currentPos] == null)
            {
                GetMovesInDirection(currentPos + direction, direction, moves, board, expectedFileChange + (expectedFileChange > 0 ? 1 : -1));
                moves.add(new Move(this, currentPos, false));
            }
        }
    }

    @Override
    public boolean IsInCheck(Piece[] board, ChessBoard chessBoard) {
        return false;
    }

    @Override
    public void GetMoves(ChessBoard chessBoard, List<Move> moves, boolean caresAboutCheck)
    {
        Piece[] board = chessBoard.GetBoard();
        for (int i = 0; i < DIRECTION.length; ++i)
        {
            GetMovesInDirection(position + DIRECTION[i], DIRECTION[i], moves, board, FILE_CHANGE[i]);
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
        return "Bishop";
    }

    public Character GetChar()
    {
        return 'B';
    }

    public float GetValue()
    {
        return 3;
    }
}
