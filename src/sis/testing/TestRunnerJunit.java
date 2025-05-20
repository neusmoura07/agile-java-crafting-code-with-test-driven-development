package sis.testing;

public class TestRunnerJunit {
    public static void main(String[] args) {
        new junit.swingui.TestRunner().run(TestRunnerJunit.class);
    }

    public static junit.framework.Test suite() {
        return new SuiteBuilder().suite();
    }
}
