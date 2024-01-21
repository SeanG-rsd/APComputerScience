import java.util.List;

public class ChessBot
{
    private static Piece.PieceColor botColor;
    private static Piece.PieceColor playerColor;
    public ChessBot(Piece.PieceColor color, Piece.PieceColor playerColor)
    {
        botColor = color;
        this.playerColor = playerColor;
    }

    public void GetBestMove(ChessBoard chessBoard, ChessBoard tempBoard)
    {
        System.out.println(Minimax(chessBoard, 1, true, tempBoard));
    }

    private static float Minimax(ChessBoard position, int depth, boolean bot, ChessBoard tempBoard)
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
                position.PrintBoard();
                position.MakeMove(m, false);
                position.PrintBoard();
                float eval = Minimax(position, depth - 1, false, tempBoard);
                position.UndoMove(m);
                position.PrintBoard();
                minEval = Math.min(minEval, eval);
                System.out.println("minEval");
            }
            return minEval;
        }
        else
        {
            float maxEval = Float.NEGATIVE_INFINITY;
            for (Move m : movesForASide)
            {
                position.PrintBoard();
                position.MakeMove(m, true);
                position.PrintBoard();
                float eval = Minimax(position, depth - 1, true, tempBoard);
                position.UndoMove(m);
                position.PrintBoard();
                maxEval = Math.max(maxEval, eval);
                System.out.println("maxEval");
            }
            return maxEval;
        }
    }
}
