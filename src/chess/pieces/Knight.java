package chess.pieces;

public class Knight extends Piece {
    private Knight(Color color, String position){
        super(KNIGHT_REPRESENTATION, color, position, 2.5);
    }

    public static Knight createKnight(Color color, String position) {
        return new Knight(color, position);
    }

    @Override
    public double getStrength(){
        return 2.5;
    }
}
