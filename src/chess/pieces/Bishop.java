package chess.pieces;

import java.util.*;

public class Bishop extends Piece {
    private Bishop(Color color, String position) {
        super('b', color, position, 3.0);
    }

    public static Bishop createBishop(Color color, String position) {
        return new Bishop(color, position);
    }





}
