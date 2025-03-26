package chess.pieces;

import junit.framework.TestCase;

public class PieceTest extends TestCase {

    public void testCreatePiece() {
        Piece whiteKing = King.createKing(Piece.Color.WHITE, "");
        Piece blackRook = Rook.createRook(Piece.Color.BLACK, "");
        verifyCreationBlack(
                blackRook,
                Rook.class, Piece.ROOK_REPRESENTATION);
        verifyCreationWhite(
                whiteKing,
                King.class, Piece.KING_REPRESENTATION);
    }

    public void testCountPiece() {
        Piece.resetCount();
        Piece whitePawn = Pawn.createPawn(Piece.Color.BLACK, "");
        assertEquals(1, Piece.getCount());
        Piece blackPawn = Pawn.createPawn(Piece.Color.BLACK, "");
        assertEquals(2, Piece.getCount());

    }

    private void verifyCreationWhite(Piece piece, Class<?> expectedClass, char representation) {

        assertTrue(piece.isWhite());
        assertEquals(expectedClass, piece.getClass());
        assertEquals(representation, piece.getRepresentation());

    }

    private void verifyCreationBlack(Piece piece, Class<?> expectedClass, char representation) {

        assertTrue(piece.isBlack());
        assertEquals(expectedClass, piece.getClass());
        assertEquals(Character.toUpperCase(representation), piece.getRepresentation());

    }

}
