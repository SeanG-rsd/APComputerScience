import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ChessBot
{
    private static Piece.PieceColor botColor;
    private static Piece.PieceColor playerColor;
    public ChessBot(Piece.PieceColor color, Piece.PieceColor playerColor)
    {
        botColor = color;
        this.playerColor = playerColor;
    }

    public Move GetBestMove(ChessBoard chessBoard, ChessBoard tempBoard)
    {
        Move bestMove = new Move();
        System.out.println(Minimax(chessBoard, 2, true, tempBoard, bestMove));
        //System.out.println(chessBoard.EvaluateBoard());
        return bestMove;
    }

    private static float Minimax(ChessBoard position, int depth, boolean bot, ChessBoard tempBoard, Move bestMove)
    {
        List<Move> movesForASide = position.GetAllMovesForAColor(bot ? botColor : playerColor, tempBoard);
        if (depth == 0 || movesForASide.isEmpty())
        {
            return position.EvaluateBoard();
        }

        if (bot)
        {
            float minEval = Float.POSITIVE_INFINITY;
            for (Move m : movesForASide)
            {
                tempBoard.MakeMove(m, true);
                position.MakeMove(m, true);
                float eval = Minimax(position, depth - 1, false, tempBoard, bestMove);
                position.UndoMove(m);
                tempBoard.UndoMove(m);

                minEval = Math.min(minEval, eval);
                if (eval <= minEval)
                {
                    bestMove.Copy(m);
                }
            }
            return minEval;
        }
        else
        {
            movesForASide = position.GetAllMovesForAColor(playerColor, tempBoard);
            System.out.println(movesForASide);
            float maxEval = Float.NEGATIVE_INFINITY;
            for (Move m : movesForASide)
            {
                tempBoard.MakeMove(m, true);
                position.MakeMove(m, true);
                float eval = Minimax(position, depth - 1, true, tempBoard, bestMove);
                position.UndoMove(m);
                tempBoard.UndoMove(m);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        }
    }
}
