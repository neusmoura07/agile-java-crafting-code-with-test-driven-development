package chess.pieces;

import junit.framework.*;

public class KnightTest extends TestCase {

    public void testCreateKnight(){
        Knight knightWhite = Knight.createKnight(Piece.Color.WHITE, "b4");
        Knight knightBlack = Knight.createKnight(Piece.Color.BLACK, "b4");

        assertTrue(knightWhite.isWhite());
        assertEquals(Knight.KNIGHT_REPRESENTATION, knightWhite.getRepresentation());
        assertEquals(Knight.class,  knightWhite.getClass());

        assertTrue(knightBlack.isBlack());
        assertEquals(Character.toUpperCase(Knight.KNIGHT_REPRESENTATION), knightBlack.getRepresentation());
        assertEquals(Knight.class, knightBlack.getClass());
    }
}
