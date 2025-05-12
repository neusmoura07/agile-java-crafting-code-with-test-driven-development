package sis.testing;

import java.lang.reflect.Modifier;
import java.util.*;
import junit.framework.*;
import junit.runner.*;


public class SuiteBuilder {

    public List<String> gatherTestClassNames() {
        TestCollector collector = new ClassPathTestCollector() {
            public boolean isTestClass(String classFileName) {
                if (!super.isTestClass(classFileName))
                    return false;
                String className = classNameFromFile(classFileName);
                Class klass = createClass(className);
                return TestCase.class.isAssignableFrom(klass) && isConcrete(klass);
            }
        };
        return Collections.list(collector.collectTests());
    }

    private Class createClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public TestSuite suite() {
        TestSuite suite = new TestSuite();
        for (String className : gatherTestClassNames()) {
            try {
                @SuppressWarnings("unchecked")
                Class<? extends TestCase> tc =
                        (Class<? extends TestCase>) Class.forName(className);
                suite.addTestSuite(tc);
            } catch (ClassNotFoundException e) {
                // ignorar ou logar
            }
        }
        return suite;
    }

    private boolean isConcrete(Class klass) {
        if(klass.isInterface())
            return false;
        int modifiers = klass.getModifiers();
        return !Modifier.isAbstract(modifiers);
    }



}
