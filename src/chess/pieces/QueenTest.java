package chess.pieces;

import junit.framework.TestCase;
import java.util.List;

public class QueenTest extends TestCase {

    // Testa os movimentos da rainha a partir de uma posição central (d4)
    public void testQueenMovesFromCenter() {
        Queen queen = Queen.createQueen(Piece.Color.WHITE, "d4");
        List<String> moves = queen.getPossibleMoves();

        // testar se alguns movimentos esperados estão presentes.
        assertContains(moves, "c5", "e5", "c3", "e3");

    }

    // Testa se a representação da rainha branca é 'q'
    public void testQueenRepresentationWhite() {
        Queen queen = Queen.createQueen(Piece.Color.WHITE, "d4");
        assertEquals("Representação da rainha branca incorreta", 'q', queen.getRepresentation());
    }

    // Testa se a representação da rainha preta é 'Q'
    public void testQueenRepresentationBlack() {
        Queen queen = Queen.createQueen(Piece.Color.BLACK, "d4");
        assertEquals("Representação da rainha preta incorreta", 'Q', queen.getRepresentation());
    }

    private void assertContains(List<String> moves, String ... expectedMoves) {
        for(String move: expectedMoves) {
            assertTrue("Falta o movimento" + move, moves.contains(move));
        }
    }
}
