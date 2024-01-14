public class Move
{
    public Piece piece;
    private int newPos;
    public int startPos;

    private boolean isTake;

    private Piece castlingRook;
    private boolean isCastle;

    public Move(Piece piece, int pos, boolean isTake)
    {
        this.piece = piece;
        this.newPos = pos;
        this.isTake = isTake;
        this.startPos = piece.position;
    }

    public int getPosition()
    {
        return newPos;
    }

    public boolean IsTake()
    {
        return isTake;
    }

    public void SetCastle(Piece piece, boolean isCastle)
    {
        this.isCastle = isCastle;
        castlingRook = piece;
    }

    public String toString()
    {
        return piece.GetName() + " : " + newPos + " : " + isTake;
    }
}
