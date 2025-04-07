package formatter;

import com.sun.tools.javac.Main;
import junit.framework.TestCase;

import java.util.logging.*;


public class LevelCountingFormatterTest extends TestCase {

    public void testFormatterWithoutCounter() {
        Formatter fmt = new LevelCountingFormatter();
        LogRecord rec = new LogRecord(Level.WARNING, "watch out");

        String out = fmt.format(rec);
        assertEquals("WARNING: watch out" + System.lineSeparator(), out);
    }

    public void testFormatterWithCounter() {
        Logger logger = Logger.getLogger(Main.class.getName());

        CountingLogHandler counter = new CountingLogHandler();
        logger.addHandler(counter);

        Formatter fmt = new LevelCountingFormatter(counter);

        LogRecord rec1 = new LogRecord(Level.WARNING, "watch out");
        counter.publish(rec1);
        LogRecord rec2 = new LogRecord(Level.WARNING, "watch out again");
        counter.publish(rec2);

        LogRecord rec3 = new LogRecord(Level.WARNING, "final warning");
        counter.publish(rec3);
        String out = fmt.format(rec3);

        assertEquals("WARNING: final warning (WARNING total = 3)" + System.lineSeparator(),out);

    }

}
