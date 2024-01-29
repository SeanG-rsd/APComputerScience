import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChessBot
{
    private static Piece.PieceColor botColor;
    private static Piece.PieceColor playerColor;
    public ChessBot(Piece.PieceColor color, Piece.PieceColor player)
    {
        botColor = color;
        playerColor = player;
    }

    public Move GetBestMove(ChessBoard tempBoard)
    {
        Move bestMove = new Move();
        int DEPTH = 6;
        float start = System.nanoTime();

        List<Move> movesForASide = tempBoard.GetAllMovesForAColor(botColor, tempBoard);
        float bestEval = Float.POSITIVE_INFINITY;
        for (Move m : movesForASide)
        {
            tempBoard.MakeMove(m);

            float eval = Minimax(tempBoard, DEPTH - 1, false, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
            if (eval < bestEval)
            {
                bestEval = eval;
                bestMove.Copy(m);
            }

            tempBoard.UndoMove(m);
        }
        //Threads(tempBoard, DEPTH, bestMove);

        System.out.println((System.nanoTime() - start) / 1000000000);
        return bestMove;
    }

    private static void Threads(ChessBoard tempBoard, int DEPTH, Move bestMove)
    {
        List<ChessThread> threads = new ArrayList<>();

        List<Move> movesForASide = tempBoard.GetAllMovesForAColor(botColor, tempBoard);
        float bestEval = Float.POSITIVE_INFINITY;
        for (Move m : movesForASide)
        {
            tempBoard.MakeMove(m);
            ChessBoard boardCopy = new ChessBoard(tempBoard);
            ChessThread newThread = new ChessThread(boardCopy, DEPTH - 1, false, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, botColor, playerColor, m);
            tempBoard.UndoMove(m);
            newThread.start();

            threads.add(newThread);
        }

        for (ChessThread thread : threads)
        {
            try {
                thread.join();
            }
            catch (InterruptedException e){
                throw new RuntimeException(e);
            }

            if (thread.GetBestEval() < bestEval)
            {
                bestMove.Copy(thread.GetMove());
            }
        }
    }

    private static float Minimax(ChessBoard position, int depth, boolean bot, float alpha, float beta)
    {
        List<Move> movesForASide = position.GetAllMovesForAColor(bot ? botColor : playerColor, position);
        if (depth == 0 || movesForASide.isEmpty())
        {
            return position.EvaluateBoard();
        }

        if (bot)
        {
            float minEval = Float.POSITIVE_INFINITY;
            for (Move m : movesForASide)
            {
                position.MakeMove(m);

                float eval = Minimax(position, depth - 1, false, alpha, beta);
                position.UndoMove(m);

                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha)
                {
                    break;
                }
            }
            return minEval;
        }
        else
        {
            float maxEval = Float.NEGATIVE_INFINITY;
            for (Move m : movesForASide)
            {
                position.MakeMove(m);
                float eval = Minimax(position, depth - 1, true, alpha, beta);
                position.UndoMove(m);

                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha)
                {
                    break;
                }
            }
            return maxEval;
        }
    }
}

class ChessThread extends Thread
{
    Piece.PieceColor botColor;
    Piece.PieceColor playerColor;
    ChessBoard startBoard;
    int startDepth;
    boolean isBotStart;
    float startAlpha;
    float startBeta;

    Move move;
    float bestEval;
    public ChessThread(ChessBoard position, int depth, boolean bot, float alpha, float beta, Piece.PieceColor botColor, Piece.PieceColor playerColor, Move firstMove)
    {
        this.startBoard = position;
        this.startDepth = depth;
        this.isBotStart = bot;
        this.startAlpha = alpha;
        this.startBeta = beta;
        this.botColor = botColor;
        this.playerColor = playerColor;
        this.move = firstMove;
    }

    public float miniMax(ChessBoard position, int depth, boolean bot, float alpha, float beta)
    {
        List<Move> movesForASide = position.GetAllMovesForAColor(bot ? botColor : playerColor, position);
        if (depth == 0 || movesForASide.isEmpty())
        {
            return position.EvaluateBoard();
        }

        if (bot)
        {
            float minEval = Float.POSITIVE_INFINITY;
            for (Move m : movesForASide)
            {
                position.MakeMove(m);

                float eval = miniMax(position, depth - 1, false, alpha, beta);
                position.UndoMove(m);

                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha)
                {
                    break;
                }
            }
            return minEval;
        }
        else
        {
            float maxEval = Float.NEGATIVE_INFINITY;
            for (Move m : movesForASide)
            {
                position.MakeMove(m);
                float eval = miniMax(position, depth - 1, true, alpha, beta);
                position.UndoMove(m);

                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha)
                {
                    break;
                }
            }
            return maxEval;
        }

    }

    public float GetBestEval()
    {
        return bestEval;
    }

    public Move GetMove()
    {
        return move;
    }
    @Override
    public void run()
    {
        bestEval = miniMax(startBoard, startDepth, isBotStart, startAlpha, startBeta);
    }
}
