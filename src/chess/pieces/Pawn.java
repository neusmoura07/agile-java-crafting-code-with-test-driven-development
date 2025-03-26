package chess.pieces;

public class Pawn extends Piece{
    private Pawn(Color color, String position) {
        super(PAWN_REPRESENTATION, color, position, 1.0);
    }

    public static Pawn createPawn(Color color, String position) {
        return new Pawn(color, position);
    }

}
