public class Move
{
    public Piece piece;
    private int newPos;

    public Piece takenPiece;
    public int startPos;

    private boolean isTake;

    private Piece castlingRook;
    private boolean isCastle;
    private Move castleMove;
    public Move firstMoveBeforeCastle; // to see if a piece pins the king from castling

    public Piece takenPawn;
    private boolean isEnPassant;

    public Move()
    {
        newPos = -1;
    }

    public Move(Piece piece, int pos, boolean isTake)
    {
        this.piece = piece;
        this.newPos = pos;
        this.isTake = isTake;
        this.startPos = piece.position;
        takenPiece = null;
    }

    public void Copy(Move copy)
    {
        this.piece = copy.piece;
        this.newPos = copy.getPosition();
        this.isTake = copy.isTake;
        this.startPos = copy.startPos;
        this.takenPiece = copy.takenPiece;
    }

    public int getPosition()
    {
        return newPos;
    }

    public boolean IsTake()
    {
        return isTake;
    }

    public void SetTake(Piece piece)
    {
        takenPiece = piece;
    }

    public void SetCastle(Piece piece, boolean isCastle, int newRookPos, Piece king, int inBetweenPos)
    {
        this.isCastle = isCastle;
        castlingRook = piece;
        castleMove = new Move(piece, newRookPos, false);
        firstMoveBeforeCastle = new Move(king, inBetweenPos, false);
    }

    public boolean IsCastle()
    {
        return isCastle;
    }

    public Move GetCastleMove()
    {
        return castleMove;
    }

    public void SetEnPassant(Piece piece, boolean isEnPassant)
    {
        this.isEnPassant = isEnPassant;
        takenPawn = piece;
    }

    public int EnPassantMove()
    {
        return takenPawn.getPosition();
    }

    public boolean IsEnPassant()
    {
        return isEnPassant;
    }

    public String toString()
    {
        return piece.GetName() + " : " + startPos +  "->" + newPos + " : " + (isTake);
    }
}
