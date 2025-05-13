package chess.game;

import junit.framework.TestCase;
import java.util.*;

public class GameTest extends TestCase {
    private Game game;

    @Override
    protected void setUp() throws Exception {
        game = new Game();
        game.initializeGame();  // coloca todas as peças na posição inicial
    }

    public void testGetSortedPositions_initialSetup() {
        List<String> sorted = game.getSortedPositions();

        // construímos o esperado: a8,a7,…,a1, b8,b7,…,b1, … h1
        List<String> expected = new ArrayList<>();
        char[] files = "abcdefgh".toCharArray();
        char[] ranks = "87654321".toCharArray();
        for (char f : files) {
            for (char r : ranks) {
                String pos = "" + f + r;
                // só adiciona se há peça lá
                if (game.getBoard().getPieceAt(pos) != null) {
                    expected.add(pos);
                }
            }
        }

        assertEquals("Total de posições não bate", expected.size(), sorted.size());
        assertEquals("Listas de posições diferem", expected, sorted);
    }
}
