package chess.pieces;

import junit.framework.*;

public class PawnTest extends TestCase{

    public void testCreatePawn() {
        Pawn pawnWhite = Pawn.createPawn(Piece.Color.WHITE, "b4");
        Pawn pawnBlack = Pawn.createPawn(Piece.Color.BLACK, "b4");

        assertTrue(pawnWhite.isWhite());
        assertEquals(Pawn.PAWN_REPRESENTATION, pawnWhite.getRepresentation());
        assertEquals(Pawn.class,pawnWhite.getClass());

        assertTrue(pawnBlack.isBlack());
        assertEquals(Character.toUpperCase(Pawn.PAWN_REPRESENTATION), pawnBlack.getRepresentation());
        assertEquals(Pawn.class,pawnBlack.getClass());

    }
}
