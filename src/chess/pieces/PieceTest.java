package chess.pieces;

import junit.framework.TestCase;

import java.awt.*;

public class PieceTest extends TestCase {

    public void testCreatePiece() {
        Piece whiteKing = Piece.createPiece(Piece.Color.white, Piece.Type.KING, Piece.KING_REPRESENTATION);
        Piece blackRook = Piece.createPiece(Piece.Color.black, Piece.Type.ROOK, Piece.ROOK_REPRESENTATION);


        verifyCreationBlack(
                blackRook,
                Piece.Type.ROOK, Piece.ROOK_REPRESENTATION);
        verifyCreationWhite(
                whiteKing,
                Piece.Type.KING, Piece.KING_REPRESENTATION);


    }

    public void testCountPiece() {
        Piece.resetCount();
        Piece whitePawn = Piece.createPiece(Piece.Color.black, Piece.Type.PAWN,Piece.PAWN_REPRESENTATION);
        assertEquals(1, Piece.getCount());
        Piece blackPawn = Piece.createPiece(Piece.Color.black, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION);
        assertEquals(2, Piece.getCount());

    }

    public void testPawnRepresentation() {
        Piece whitePawn = Piece.createPiece(Piece.Color.white, Piece.Type.PAWN,Piece.PAWN_REPRESENTATION);
        Piece blackPawn = Piece.createPiece(Piece.Color.black, Piece.Type.PAWN, Piece.PAWN_REPRESENTATION);

        assertEquals("O peão branco deve ser 'p'", Piece.PAWN_REPRESENTATION, whitePawn.getRepresentation());
        assertEquals("O peão preto deve ser 'P'", Character.toUpperCase(Piece.PAWN_REPRESENTATION), blackPawn.getRepresentation());
    }


    private void verifyCreationWhite(Piece piece, Piece.Type type, char representation) {

        assertTrue(piece.isWhite());
        assertEquals(type, piece.getType());
        assertEquals(representation, piece.getRepresentation());

    }

    private void verifyCreationBlack(Piece piece, Piece.Type type, char representation) {

        assertTrue(piece.isBlack());
        assertEquals(type, piece.getType());
        assertEquals(Character.toUpperCase(representation), piece.getRepresentation());

    }

}
