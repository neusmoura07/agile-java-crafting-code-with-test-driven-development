package sis;
import junit.framework.TestSuite;

public class AllSuites {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(sis.report.AllTests.suite());
        suite.addTest(sis.studentinfo.AllTests.suite());
        suite.addTest(sis.util.AllTests.suite());
        suite.addTest(sis.summer.AllTests.suite());
        return suite;
    }
}
