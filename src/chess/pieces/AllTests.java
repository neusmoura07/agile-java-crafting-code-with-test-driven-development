package chess.pieces;

import junit.framework.TestSuite;

public class AllTests {

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(PieceTest.class);
        suite.addTestSuite(BishopTest.class);
        suite.addTestSuite(KingTest.class);
        suite.addTestSuite(KnightTest.class);
        suite.addTestSuite(PawnTest.class);
        suite.addTestSuite(QueenTest.class);
        suite.addTestSuite(RookTest.class);
        return suite;
    }

}
