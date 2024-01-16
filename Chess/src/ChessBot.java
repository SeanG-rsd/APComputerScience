import java.util.List;

public class ChessBot
{
    private static Piece.PieceColor botColor;
    public ChessBot(Piece.PieceColor color)
    {
        botColor = color;
    }

    public void GetBestMove(ChessBoard chessBoard, ChessBoard tempBoard)
    {
        List<Move> movesForASide = chessBoard.GetAllMovesForAColor(botColor, tempBoard);
    }

    private static void Minimax()
    {

    }
}
