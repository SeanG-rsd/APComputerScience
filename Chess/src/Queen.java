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
                Move newMove = new Move(this, currentPos, true);
                newMove.SetTake(board[currentPos]);
                moves.add(newMove);
            }
            else if (board[currentPos] == null)
            {
                moves.add(new Move(this, currentPos, false));
                GetMovesInDirection(currentPos + direction, direction, moves, board, expectedFileChange + (Integer.compare(expectedFileChange, 0)));
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
        return "Queen";
    }

    public Character GetChar()
    {
        return 'Q';
    }

    public float GetValue()
    {
        return 9;
    }
}
