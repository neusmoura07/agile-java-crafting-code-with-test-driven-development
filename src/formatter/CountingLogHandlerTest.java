package formatter;

import com.sun.tools.javac.Main;
import junit.framework.TestCase;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CountingLogHandlerTest extends TestCase {

    public void testCountingLogHandler() {
        Logger logger = Logger.getLogger(Main.class.getName());
        CountingLogHandler handler = new CountingLogHandler();
        logger.addHandler(handler);

        logger.info("Mensagem de INFO");
        logger.warning("Mensagem de WARNING");
        logger.severe("Mensagem de SEVERE");
        logger.severe("Outra mensagem de SEVERE");

        assertEquals(1,handler.getCount(Level.INFO));
        assertEquals(1,handler.getCount(Level.WARNING));
        assertEquals(2,handler.getCount(Level.SEVERE));
    }

}
