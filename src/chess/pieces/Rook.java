package chess.pieces;

public class Rook extends Piece {
    private Rook(Color color, String position) {
        super(ROOK_REPRESENTATION, color, position, 5.0);
    }

    public static Rook createRook(Color color, String position) {
        return new Rook(color, position);
    }

}
