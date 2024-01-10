public class Move
{
    private Piece piece;
    private int newPos;

    private boolean isTake;

    public Move(Piece piece, int pos, boolean isTake)
    {
        this.piece = piece;
        this.newPos = pos;
        this.isTake = isTake;
    }

    public String toString()
    {
        return piece.GetName() + " : " + newPos + " : " + isTake;
    }
}
