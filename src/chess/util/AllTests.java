package chess.util;


import junit.framework.TestSuite;

public class AllTests extends TestSuite {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CharacterTest.class);
        suite.addTestSuite(StringUtilTest.class);
        return suite;
    }
}
