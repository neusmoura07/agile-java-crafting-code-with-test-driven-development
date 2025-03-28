package chess.pieces;

import junit.framework.*;

public class BishopTest extends TestCase {

    public void testCreateBishop() {
        Bishop bishopWhite = Bishop.createBishop(Piece.Color.WHITE, "e4");
        Bishop bishopBlack = Bishop.createBishop(Piece.Color.BLACK, "b4");


        assertTrue(bishopWhite.isWhite());
        assertEquals(Bishop.class, bishopWhite.getClass());
        assertEquals(Piece.BISHOP_REPRESENTATION, bishopWhite.getRepresentation());

        assertTrue(bishopBlack.isBlack());
        assertEquals(Bishop.class, bishopBlack.getClass());
        assertEquals(Character.toUpperCase(Piece.BISHOP_REPRESENTATION), bishopBlack.getRepresentation());
    }



}
