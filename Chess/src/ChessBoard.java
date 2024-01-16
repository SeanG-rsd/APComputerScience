import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ChessBoard
{
    public static Piece[] board = new Piece[64];

    private static Move lastMove = null;
    private static Piece lastPieceTaken = null;

    public ChessBoard(String code)
    {
        InitializeBoard(code);
    }

    public boolean IsKingInCheck(Piece.PieceColor color) // checks if a certain king is in check
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
    public static void InitializeBoard(String coded) // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
    {
        // takes the given code and decodes it into an array of chars
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

        // creates the piece objects out of the array of chars

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
    public void PrintBoard() // outputs the board to the console
    {
        System.out.println();
        System.out.println("     0   1   2   3   4   5   6   7  ");
        for (int c = 0; c < 8; ++c)
        {
            System.out.println("   ---------------------------------");
            System.out.print((7 - c) + "  ");
            for (int r = 0; r < 8; ++r)
            {
                if (board[c * 8 + r] != null)
                {
                    System.out.print("| " + (board[c * 8 + r].getChar()) + " ");
                }
                else
                {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");

        }
        System.out.println("   ---------------------------------");
        System.out.println("     0   1   2   3   4   5   6   7  ");
    }

    public List<Move> GetAllMovesForAColor(Piece.PieceColor color, ChessBoard tempBoard) // gets all the moves for a given color
    {
        List<Move> moves = new ArrayList<>();
        for (Piece p : board)
        {
            if (p != null && p.pieceColor == color)
            {
                moves.addAll(GetMoves(p.position, tempBoard, color));
            }
        }

        return moves;
    }

    public List<Move> GetMoves(int pos, ChessBoard tempBoard, Piece.PieceColor turn) // gets the moves for a piece at a certain position
    {
        List<Move> moves = new ArrayList<>();
        board[pos].GetMoves(this, moves, true);

        Iterator<Move> i = moves.iterator();

        while (i.hasNext())
        {
            Move next = i.next();
            if (next.IsCastle())
            {
                tempBoard.MakeMove(next.firstMoveBeforeCastle, true);
                if (tempBoard.IsKingInCheck(turn))
                {
                    i.remove();
                    tempBoard.UndoMove(next.firstMoveBeforeCastle);
                    continue;
                }
                else
                {
                    tempBoard.UndoMove(next.firstMoveBeforeCastle);
                    tempBoard.MakeMove(next, true);
                    if (tempBoard.IsKingInCheck(turn))
                    {
                        i.remove();
                    }
                }
                tempBoard.UndoMove(next);
            }
            else
            {
                tempBoard.MakeMove(next, true);
                if (tempBoard.IsKingInCheck(turn)) {
                    i.remove();
                }
                tempBoard.UndoMove(lastMove);
            }
        }

        return moves;
    }

    public void MakeMove(Move move, boolean temp) // makes the given move on the board
    {
        lastPieceTaken = board[move.getPosition()];
        lastMove = move;
        //System.out.println(move);
        board[move.getPosition()] = board[move.piece.position];
        board[move.startPos] = null;

        if (move.IsCastle() && !temp)
        {
            MakeMove(move.GetCastleMove(), temp);
        }
        else if (move.IsEnPassant())
        {
            board[move.EnPassantMove()] = null;
        }

        if (!temp)
        {
            move.piece.MakeMove(move);
        }

        move.piece.position = move.getPosition();
    }

    public void UndoMove(Move move) // undos the move given
    {
        board[move.startPos] = move.piece;
        board[move.getPosition()] = lastPieceTaken;
        move.piece.position = move.startPos;

        if (move.IsCastle())
        {
            UndoMove(move.GetCastleMove());
        }

        lastPieceTaken = null;
    }

    public void GetOpponentAttackedSpots(Piece.PieceColor yourColor, List<Move> attackedSquares) // gets the sqaures that are attacked by a pieces opponent
    {
        for (Piece piece : board)
        {
            if (piece != null && piece.pieceColor != yourColor && piece.pieceType != Piece.PieceType.KING)
            {
                piece.GetMoves(this, attackedSquares, false);
            }
        }
        //System.out.println(attackedSquares);
    }
}
