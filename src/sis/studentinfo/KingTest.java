package sis.studentinfo;

import chess.pieces.King;
import chess.pieces.Piece;
import junit.framework.*;

import java.util.List;

public class KingTest extends TestCase {

    public void testKingMovesFromCenter() {
        King king = King.createKing(Piece.Color.WHITE, "e4");
        List<String> moves = king.getPossibleMoves();

        List<String> expectedMoves = List.of("d3", "d4", "d5", "e3", "e5", "f3", "f4", "f5");
        assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));

    }

    public void testKingMovesFromEdge() {
        King king = King.createKing(Piece.Color.WHITE, "a4");
        List<String> moves = king.getPossibleMoves();

        List<String> expectedMoves = List.of("a3", "a5", "b3", "b4", "b5");

        assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
    }

    public void testKingHasCorrectColor() {
        King whiteKing = King.createKing(Piece.Color.WHITE, "e4");
        King blackKing = King.createKing(Piece.Color.BLACK, "d8");

        assertEquals(Piece.Color.WHITE, whiteKing.getColor());
        assertEquals(Piece.Color.BLACK, blackKing.getColor());
    }

    public void testKingHasCorrectRepresentation() {
        King whiteKing = King.createKing(Piece.Color.WHITE, "e4");
        King blackKing = King.createKing(Piece.Color.BLACK, "d8");

        assertEquals('k', whiteKing.getRepresentation());
        assertEquals('K', blackKing.getRepresentation());
    }

}
