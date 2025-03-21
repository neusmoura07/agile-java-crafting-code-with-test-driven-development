package chess;

import chess.pieces.PieceTest;
import junit.framework.TestSuite;

public class AllTests {

    public static TestSuite suite(){
        TestSuite suite = new TestSuite();
        suite.addTestSuite(PieceTest.class);
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(CharacterTest.class);
        return suite;
    }
}
