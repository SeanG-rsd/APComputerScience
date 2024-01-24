import java.util.List;

public class Rook extends Piece
{
    public int[] rookTable = new int[]
            {
                    0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0,
                    0,0,0,0,0,0,0,0
            };

    public Rook(PieceColor color, int position)
    {
        super(PieceType.ROOK, color, position);
    }

    public void GetMovesInDirection(int currentPos, int direction, List<Move> moves, Piece[] board, int expectedFileChange)
    {
        if (IsWithinBoard(currentPos) && position / 8 + expectedFileChange == currentPos / 8)
        {
            if (board[currentPos] != null && board[currentPos].pieceColor != pieceColor)
            {
                Move newMove = new Move(this, currentPos, true);
                newMove.SetTake(board[currentPos]);
                moves.add(newMove);
            }
            else if (board[currentPos] == null)
            {
                GetMovesInDirection(currentPos + direction, direction, moves, board, expectedFileChange + (Integer.compare(expectedFileChange, 0)));
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
        GetMovesInDirection(position - 1, -1, moves, board, 0);
        GetMovesInDirection(position + 1, 1, moves, board, 0);
        GetMovesInDirection(position - 8, -8, moves, board, -1);
        GetMovesInDirection(position + 8, 8, moves, board, 1);
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
    public String GetName()
    {
        return "Rook";
    }

    public Character GetChar()
    {
        return 'R';
    }

    public float GetValue()
    {
        float value = 5;
        if (pieceColor == PieceColor.WHITE)
        {
            value += ((float) rookTable[position] / 100);
        }
        else
        {
            value += ((float) rookTable[flip[position]] / 100);
        }

        return value;
    }
}
