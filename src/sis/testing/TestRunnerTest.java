package sis.testing;

import java.util.*;
import java.lang.reflect.*;

public class TestRunnerTest {
    private TestRunner runner;
    private static final String methodNameA = "testA";
    private static final String methodNameB = "testB";
    public static final String IGNORE_REASON1 = "because";
    public static final String IGNORE_REASON2 = "why not";
    public static final String IGNORE_INITIALS = "jjl";

    @TestMethod
    public void singleMethodTest() {
        runTests(SingleMethodTest.class);
        verifyTests(methodNameA);
    }

    @TestMethod
    public void multipleMethodTest() {
        runTests(MultipleMethodTest.class);
        verifyTests(methodNameA, methodNameB);
    }

    @TestMethod
    public void ignoreMethodTest() {
        runTests(IgnoreMethodTest.class);
        verifyTests(methodNameA, methodNameB);
        assertIgnoreReasons();
    }

    @TestMethod
    public void ignoreWithDefaultReason() {
        runTests(DefaultIgnoreMethodTest.class);
        verifyTests(methodNameA, methodNameB);
        Map<Method, Ignore> ignoredMethods = runner.getIgnoredMethods();
        Map.Entry<Method, Ignore> entry = getSoleEntry(ignoredMethods);
        Ignore ignore = entry.getValue();
        assert TestRunner.DEFAULT_IGNORE_REASON.equals(ignore.reasons()[0]);
    }

    @TestMethod
    public void dateTest() {
        runTests(IgnoreDateTest.class);
        Map<Method, Ignore> ignoredMethods = runner.getIgnoredMethods();
        Map.Entry<Method, Ignore> entry = getSoleEntry(ignoredMethods);
        Ignore ignore = entry.getValue();
        sis.testing.Date date = ignore.date();
        assert 1 == date.month();
        assert 2 == date.day();
        assert 2005 == date.year();
    }

    @TestMethod
    public void packageAnnotations() {
        Package pkg = this.getClass().getPackage();
        TestPackage testPackage = pkg.getAnnotation(TestPackage.class);
        assert testPackage.isPerformance();
    }

    private void runTests(Class testClass) {
        runner = new TestRunner(testClass);
        runner.run();
    }

    private void verifyTests(String... expectedTestMethodNames) {
        verifyNumberOfTests(expectedTestMethodNames);
        verifyMethodNames(expectedTestMethodNames);
        verifyCounts(expectedTestMethodNames);
    }

    private void verifyCounts(String... testMethodNames) {assert testMethodNames.length == runner.passed() :
            "expected " + testMethodNames.length + " passed";
        assert 0 == runner.failed() : "expected no failures";
    }

    private void verifyNumberOfTests(String... testMethodNames) {
        assert testMethodNames.length == runner.getTestMethods().size() :
                "expected " + testMethodNames.length + " test method(s)";
    }
    private void verifyMethodNames(String... testMethodNames) {
        Set<String> actualMethodNames = getTestMethodNames();
        for (String methodName: testMethodNames)
            assert actualMethodNames.contains(methodName):
                    "expected " + methodName + " as test method";
    }
    private Set<String> getTestMethodNames() {
        Set<String> methodNames = new HashSet<String>();
        for (Method method: runner.getTestMethods())
            methodNames.add(method.getName());
        return methodNames;
    }

    private void assertIgnoreReasons() {
        Map<Method, Ignore> ignoredMethods = runner.getIgnoredMethods();
        Map.Entry<Method, Ignore> entry = getSoleEntry(ignoredMethods);
        assert "testC".equals(entry.getKey().getName()):
                "unexpected ignore method: " + entry.getKey();
        Ignore ignore = entry.getValue();
        String[] ignoreReasons = ignore.reasons();
        assert 2 == ignoreReasons.length;
        assert IGNORE_REASON1.equals(ignoreReasons[0]);
        assert IGNORE_REASON2.equals(ignoreReasons[1]);
        assert IGNORE_INITIALS.equals(ignore.initials());
    }
    private <K, V> Map.Entry<K, V> getSoleEntry(Map<K, V> map) {
        assert 1 == map.size(): "expected one entry";
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        return it.next();
    }

}

class SingleMethodTest{
    @TestMethod public void testA() {}
}

class MultipleMethodTest{
    @TestMethod public void testA() {}
    @TestMethod public void testB() {}
}

class IgnoreMethodTest {
    @TestMethod public void testA() {}
    @TestMethod public void testB() {}
    @Ignore(
            reasons={TestRunnerTest.IGNORE_REASON1,
                    TestRunnerTest.IGNORE_REASON2},
            initials=TestRunnerTest.IGNORE_INITIALS,
            date=@Date(month=1, day=2, year=2005))
    @TestMethod public void testC() {}
}

class DefaultIgnoreMethodTest {
    @TestMethod public void testA() {}
    @TestMethod public void testB() {}
    @Ignore(initials=TestRunnerTest.IGNORE_INITIALS,
            date=@Date(month=1, day=2, year=2005))
    @TestMethod public void testC() {}
}

class IgnoreDateTest {
    @Ignore(
            initials=TestRunnerTest.IGNORE_INITIALS,
            date=@Date(month=1, day=2, year=2005))
    @TestMethod public void testC() {}
}


