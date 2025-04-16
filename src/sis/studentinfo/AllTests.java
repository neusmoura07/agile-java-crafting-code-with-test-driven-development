package sis.studentinfo;

import junit.framework.*;
import sis.summer.SummerCourseSessionTest;

public class AllTests {

    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(CourseSessionTest.class);
        suite.addTestSuite(DateUtilTest.class);
        suite.addTestSuite(BasicGradingStrategyTest.class);
        suite.addTestSuite(HonorsGradingStrategyTest.class);
        suite.addTestSuite(SummerCourseSessionTest.class);
        suite.addTestSuite(AccountTest.class);
        suite.addTestSuite(CourseTest.class);
        suite.addTestSuite(PasswordGeneratorTest.class);
        suite.addTestSuite(PerfomanceTest.class);
        suite.addTestSuite(ScorerTest.class);
        suite.addTestSuite(StudentTest.class);
        return suite;
    }
}
