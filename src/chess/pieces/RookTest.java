package chess.pieces;

import junit.framework.*;

public class RookTest extends TestCase{

    public void testCreateRook() {
        Rook rookWhite = Rook.createRook(Piece.Color.WHITE, "b4");
        Rook rookBlack = Rook.createRook(Piece.Color.BLACK, "b4");

        assertTrue(rookWhite.isWhite());
        assertEquals(Pawn.ROOK_REPRESENTATION, rookWhite.getRepresentation());
        assertEquals(Rook.class,rookWhite.getClass());

        assertTrue(rookBlack.isBlack());
        assertEquals(Character.toUpperCase(Pawn.ROOK_REPRESENTATION), rookBlack.getRepresentation());
        assertEquals(Rook.class,rookBlack.getClass());

    }
}
