import java.util.ArrayList;
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
    public boolean IsInCheck(Piece[] board, ChessBoard chessBoard) {
        return false;
    }

    @Override
    public void GetMoves(ChessBoard chessBoard, List<Move> legalMoves, boolean isTempBoard)
    {
        Piece[] board = chessBoard.GetBoard();
        Piece[] tempBoard = chessBoard.GetTempBoard();
        for (int i = 0; i < KNIGHT_MOVES.length; ++i)
        {
            int move = KNIGHT_MOVES[i];
            if (IsWithinBoard(position + move) && (position + move) / 8 == position / 8 + KNIGHT_FILE_CHANGE[i])
            {

                if (board[position + move] != null && board[position + move].pieceColor != pieceColor)
                {
                    Move newMove = new Move(this, position + move, true);
                    chessBoard.MakeMove(tempBoard, newMove);
                    if (isTempBoard || !chessBoard.IsKingInCheck(pieceColor, tempBoard))
                    {
                        legalMoves.add(newMove);
                    }
                }
                else if (board[position + move] == null)
                {
                    Move newMove = new Move(this, position + move, false);
                    chessBoard.MakeMove(tempBoard, newMove);
                    if (isTempBoard || !chessBoard.IsKingInCheck(pieceColor, tempBoard))
                    {
                        legalMoves.add(newMove);
                    }
                }
            }
            if (IsWithinBoard(position - move) && (position - move) / 8 == position / 8  - KNIGHT_FILE_CHANGE[i])
            {
                if (board[position - move] != null && board[position - move].pieceColor != pieceColor)
                {
                    Move newMove = new Move(this, position - move, true);
                    chessBoard.MakeMove(tempBoard, newMove);
                    if (!chessBoard.IsKingInCheck(pieceColor, tempBoard))
                    {
                        legalMoves.add(newMove);
                    }
                }
                else if (board[position - move] == null)
                {
                    Move newMove = new Move(this, position - move, false);
                    chessBoard.MakeMove(tempBoard, newMove);
                    if (!chessBoard.IsKingInCheck(pieceColor, tempBoard))
                    {
                        legalMoves.add(newMove);
                    }
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
}
