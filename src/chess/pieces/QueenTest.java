package chess.pieces;

import junit.framework.TestCase;
import java.util.List;

public class QueenTest extends TestCase {

    // Testa os movimentos da rainha a partir de uma posição central (d4)
    public void testQueenMovesFromCenter() {
        Queen queen = Queen.createQueen(Piece.Color.WHITE, "d4");
        List<String> moves = queen.getPossibleMoves();

        // testar se alguns movimentos esperados estão presentes.
        assertTrue("Falta o movimento diagonal superior esquerda (c5)", moves.contains("c5"));
        assertTrue("Falta o movimento diagonal superior direita (e5)", moves.contains("e5"));
        assertTrue("Falta o movimento diagonal inferior esquerda (c3)", moves.contains("c3"));
        assertTrue("Falta o movimento diagonal inferior direita (e3)", moves.contains("e3"));

        // Verifica se há pelo menos um movimento horizontal (mudança na coluna, mantendo a linha 4)
        boolean hasHorizontal = false;
        for (String pos : moves) {
            if (!pos.equals("d4") && pos.charAt(1) == '4') {
                hasHorizontal = true;
                break;
            }
        }
        assertTrue("A rainha deve possuir algum movimento horizontal", hasHorizontal);
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
}
