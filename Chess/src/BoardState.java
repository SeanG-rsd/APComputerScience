public class BoardState
{
    public int enpassant;
    public int castle;
    public int capturedPiece;
    public int side;
    public int move;
    public int fifty;

    public int hashKey;
    public BoardState(int move, int capturedPiece, int side, int enpassant, int castle, int fifty, int hashKey)
    {
        this.move = move;
        this.capturedPiece = capturedPiece;
        this.side = side;
        this.enpassant = enpassant;
        this.castle = castle;
        this.fifty = fifty;
        this.hashKey = hashKey;
    }

}
