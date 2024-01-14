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
    public void GetMoves(ChessBoard chessBoard, List<Move> moves, boolean isTempBoard)
    {
        Piece[] board = chessBoard.GetBoard();
        Piece[] tempBoard = chessBoard.GetTempBoard();
        if (IsWithinBoard(position + 8) && board[position + 8] == null) // forward 1
        {
            Move newMove = new Move(this, position + 8, false);
            chessBoard.MakeMove(tempBoard, newMove);
            if (isTempBoard || !chessBoard.IsKingInCheck(pieceColor, tempBoard))
            {
                moves.add(newMove);
            }
        }
        if (IsWithinBoard(position + 16) && board[position + 16] == null && board[position + 8] == null && !hasMoved) // forward 2, only if it's the first move
        {
            Move newMove = new Move(this, position + 16, false);
            chessBoard.MakeMove(tempBoard, newMove);
            if (isTempBoard || !chessBoard.IsKingInCheck(pieceColor, tempBoard))
            {
                moves.add(newMove);
            }
        }
        if (IsWithinBoard(position + 7) && position / 8 + 1 == (position + 7) / 8 && board[position + 7] != null && board[position + 7].pieceColor != pieceColor) // take diagonally to left
        {
            Move newMove = new Move(this, position + 7, true);
            chessBoard.MakeMove(tempBoard, newMove);
            if (isTempBoard || !chessBoard.IsKingInCheck(pieceColor, tempBoard))
            {
                moves.add(newMove);
            }
        }
        if (IsWithinBoard(position + 9) && (position / 8) + 1 == (position + 9) / 8 && board[position + 9] != null && board[position + 9].pieceColor != pieceColor) // take diagonally to right
        {
            Move newMove = new Move(this, position + 9, true);
            chessBoard.MakeMove(tempBoard, newMove);
            if (isTempBoard || !chessBoard.IsKingInCheck(pieceColor, tempBoard))
            {
                moves.add(newMove);
            }
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
