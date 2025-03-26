package sis.summer;

import junit.framework.TestSuite;

public class AllTests {

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(SummerCourseSessionTest.class);
        return suite;
    }
}
