package chess.pieces;

import junit.framework.*;

import java.util.List;

public class KingTest extends TestCase {
    // Testa os movimentos do rei a partir de uma posição central (e4)
    public void testKingMovesFromCenter() {
        King king = King.createKing(Piece.Color.WHITE, "e4");
        List<String> moves = king.getPossibleMoves();

        // Como o rei se move uma casa em qualquer direção,
        // os movimentos esperados a partir de e4 são: d5, e5, f5, d4, f4, d3, e3, f3.
        assertContains(moves, "d5", "e5", "f5", "d4", "f4", "d3", "e3", "f3");
        assertEquals("O número de movimentos possíveis deve ser 8", 8, moves.size());
    }

    // Testa se a representação do rei branco é 'k'
    public void testKingRepresentationWhite() {
        King king = King.createKing(Piece.Color.WHITE, "e4");
        assertEquals("Representação do rei branco incorreta", 'k', king.getRepresentation());
    }

    // Testa se a representação do rei preto é 'K'
    public void testKingRepresentationBlack() {
        King king = King.createKing(Piece.Color.BLACK, "e4");
        assertEquals("Representação do rei preto incorreta", 'K', king.getRepresentation());
    }

    private void assertContains(List<String> moves, String ... expectedMoves) {
        for(String move: expectedMoves) {
            assertTrue("Falta o movimento" + move, moves.contains(move));
        }
    }
}
