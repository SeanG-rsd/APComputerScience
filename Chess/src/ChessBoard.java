import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard
{
    public static Piece[] board = new Piece[64];
    public static Piece[] tempBoard = new Piece[64];

    public ChessBoard(String code)
    {
        InitializeBoard(code);
    }

    public boolean IsKingInCheck(Piece.PieceColor color, Piece[] board)
    {
        for (Piece p : board)
        {
            if (p != null && p.pieceType == Piece.PieceType.KING && p.pieceColor == color)
            {

                return p.IsInCheck(board, this);
            }
        }
        return false;
    }
    public Piece[] GetBoard()
    {
        return board;
    }

    public Piece[] GetTempBoard()
    {
        for (int i = 0; i < board.length; ++i)
        {
            tempBoard[i] = board[i];
        }

        return tempBoard;
    }
    public static void InitializeBoard(String coded) // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
    {
        char[] codedBoard = new char[64];
        char[] codeReader = coded.toCharArray();

        int row = 0;
        int column = 0;
        for (char c : codeReader)
        {
            if (c != '/')
            {
                if (Character.isDigit(c))
                {
                    int count = c - '0';
                    for (int i = 0; i < count; ++i)
                    {
                        if (column < 8)
                        {
                            column++;
                        }
                    }
                }
                else
                {
                    codedBoard[row * 8 + column] = c;
                    column++;
                }
            }
            else
            {
                row++;
                column = 0;
            }
        }

        System.out.println(Arrays.toString(codedBoard));

        for (int i = 0; i < codedBoard.length; ++i)
        {
            char c = codedBoard[i];
            Piece.PieceColor color = Character.isUpperCase(c) ? Piece.PieceColor.WHITE : Piece.PieceColor.BLACK;
            if (c == 'p' || c == 'P')
            {
                board[i] = new Pawn(color, i);
            }
            else if (c == 'r' || c == 'R')
            {
                board[i] = new Rook(color, i);
            }
            else if (c == 'n' || c == 'N')
            {
                board[i] = new Knight(color, i);
            }
            else if (c == 'b' || c == 'B')
            {
                board[i] = new Bishop(color, i);
            }
            else if (c == 'q' || c == 'Q')
            {
                board[i] = new Queen(color, i);
            }
            else if (c == 'k' || c == 'K')
            {
                board[i] = new King(color, i);
            }
        }
    }

    public void PrintBoard()
    {
        System.out.println();
        for (int c = 0; c < 8; ++c)
        {
            System.out.println("---------------------------------");
            for (int r = 0; r < 8; ++r)
            {
                if (board[c * 8 + r] != null)
                {
                    System.out.print("| " + (board[c * 8 + r].GetChar()) + " ");
                }
                else
                {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");

        }
        System.out.println("---------------------------------");
    }

    public void GetMoves(int pos)
    {
        List<Move> moves = new ArrayList<>();
        board[pos].GetMoves(this, moves, false);
        System.out.println(moves);
    }

    public void MakeMove(Piece[] board, Move move)
    {
        board[move.getPosition()] = move.piece;
        board[move.piece.position] = null;
        move.piece.position = move.getPosition();
    }

    public int GetOpponentAttackedSpots(Piece.PieceColor yourColor, List<Move> attackedSquares)
    {
        int yourKingPosition = 0;

        for (Piece piece : board)
        {
            if (piece != null && piece.pieceColor != yourColor)
            {
                piece.GetMoves(this, attackedSquares, true);
            }

            if (piece != null && piece.pieceType == Piece.PieceType.KING && piece.pieceColor == yourColor)
            {
                yourKingPosition = piece.position;
            }
        }

        return yourKingPosition;
    }
}
