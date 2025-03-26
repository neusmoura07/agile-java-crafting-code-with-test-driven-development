package chess.game;

import chess.util.CharacterTest;
import junit.framework.TestSuite;

public class AllTests {

    public static TestSuite suite(){
        TestSuite suite = new TestSuite();
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(CharacterTest.class);
        return suite;
    }
}
