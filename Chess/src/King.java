import java.util.List;

public class King extends Piece
{

    public King(PieceColor color, int position)
    {
        super(PieceType.KING, color, position);
    }

    @Override
    public void GetMoves(Piece[] board, List<Move> moves) {

    }

    @Override
    public String GetName() {
        return null;
    }
}
