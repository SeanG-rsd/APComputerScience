import java.util.List;
public class Pawn extends Piece
{
    public int[] pawnTable = new int[]
    {
            0,   0,   0,   0,   0,   0,   0,   0,
            5,  10,  15,  20,  20,  15,  10,   5,
            4,   8,  12,  16,  16,  12,   8,   4,
            3,   6,   9,  12,  12,   9,   6,   3,
            2,   4,   6,   8,   8,   6,   4,   2,
            1,   2,   3, -10, -10,   3,   2,   1,
            0,   0,   0, -40, -40,   0,   0,   0,
            0,   0,   0,   0,   0,   0,   0,   0
    };
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
    public void GetMoves(ChessBoard chessBoard, List<Move> legalMoves, boolean caresAboutCheck)
    {
        Piece[] board = chessBoard.GetBoard();
        int direction = pieceColor == PieceColor.BLACK ? 1 : -1;

        if (IsWithinBoard(position + (8 * direction)) && board[position + (8 * direction)] == null) // forward 1
        {
            Move newMove = new Move(this, position + (8 * direction), false);
            legalMoves.add(newMove);
        }
        if (IsWithinBoard(position + (16 * direction)) && board[position + (16 * direction)] == null && board[position + (8 * direction)] == null && !hasMoved) // forward 2, only if it's the first move
        {
            Move newMove = new Move(this, position + (16 * direction), false);
            legalMoves.add(newMove);
        }
        if (IsWithinBoard(position + (7 * direction)) && position / 8 + direction == (position + (7 * direction)) / 8 && board[position + (7 * direction)] != null && board[position + (7 * direction)].pieceColor != pieceColor) // take diagonally to left
        {
            Move newMove = new Move(this, position + (7 * direction), true);
            newMove.SetTake(board[position + (7 * direction)]);
            legalMoves.add(newMove);
        }
        if (IsWithinBoard(position + (9 * direction)) && (position / 8) + direction == (position + (9 * direction)) / 8 && board[position + (9 * direction)] != null && board[position + (9 * direction)].pieceColor != pieceColor) // take diagonally to right
        {
            Move newMove = new Move(this, position + (9 * direction), true);
            newMove.SetTake(board[position + (9 * direction)]);
            legalMoves.add(newMove);
        }
        if (IsWithinBoard(position + direction) && position / 8 == (position + direction) / 8 && board[position + direction] != null && board[position + direction].pieceColor != pieceColor)
        {
            if (board[position + direction].pieceType == PieceType.PAWN && ((Pawn)board[position + direction]).justMadeFirstDoubleMove)
            {
                //System.out.println("en passant : " + position);
                Move newMove = new Move(this, position + (9 * direction), true);
                newMove.SetEnPassant(board[position + direction], true);
                legalMoves.add(newMove);
            }
        }
        if (IsWithinBoard(position - direction) && position / 8 == (position - direction) / 8 && board[position - direction] != null && board[position - direction].pieceColor != pieceColor)
        {
            //System.out.println("alkdsjfh");
            if (board[position - direction].pieceType == PieceType.PAWN && ((Pawn)board[position - direction]).justMadeFirstDoubleMove)
            {
                //System.out.println("en passant : " + position);
                Move newMove = new Move(this, position + (7 * direction), true);
                newMove.SetEnPassant(board[position - direction], true);
                legalMoves.add(newMove);
            }
        }
        //System.out.println(legalMoves);
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

    public float GetValue()
    {
        float value = 1;
        if (pieceColor == PieceColor.WHITE)
        {
            value += ((float) pawnTable[position] / 100);
        }
        else
        {
            value += ((float) pawnTable[flip[position]] / 100);
        }

        return value;
    }

    @Override
    public void MakeMove(Move move) {
        timesMoved++;
        if (!hasMoved)
        {
            if (Math.abs(move.startPos - move.getPosition()) == 16) {
                //System.out.println("double");
                justMadeFirstDoubleMove = true;
            }

            hasMoved = true;
        }
        else if (justMadeFirstDoubleMove)
        {
            //System.out.println("not double 1");
            justMadeFirstDoubleMove = false;
        }
    }

    public void UndoMove(Move move)
    {
        timesMoved--;
        if (justMadeFirstDoubleMove && timesMoved == 0)
        {
            //System.out.println("not double");
            justMadeFirstDoubleMove = false;
            hasMoved = false;
        }
        else if (timesMoved == 0)
        {
            hasMoved = false;
        }
    }
}
