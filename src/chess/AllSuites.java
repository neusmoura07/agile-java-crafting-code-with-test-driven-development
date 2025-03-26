package chess;

import junit.framework.TestSuite;

public class AllSuites {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(chess.util.AllTests.suite());
        suite.addTest(chess.pieces.AllTests.suite());
        suite.addTest(chess.game.AllTests.suite());
        return suite;
    }

}
