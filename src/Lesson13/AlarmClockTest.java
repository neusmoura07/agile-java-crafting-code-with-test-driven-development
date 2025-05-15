package Lesson13;


import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AlarmClockTest extends TestCase {
    private AlarmClock clock;
    private List<String> fired = Collections.synchronizedList(new ArrayList<>());
    private final Object monitor = new Object();

    public void setUp() {
        // Listener que notifica via monitor
        AlarmListener listener = name -> {
            synchronized (monitor) {
                fired.add(name);
                monitor.notifyAll();
            }
        };
        clock = new AlarmClock(listener);
    }

    public void testSingleSchedule() throws Exception {
        String event = "Alarme Único";
        Date when = new Date(System.currentTimeMillis() + 300);

        clock.schedule(event, when);

        // espera o notify
        synchronized (monitor) {
            while (fired.isEmpty()) {
                monitor.wait();
            }
        }

        assertEquals(1, fired.size());
        assertEquals(event, fired.get(0));
    }

    public void testCancelBeforeFire() throws Exception {
        String event = "Alarme a Cancelar";
        Date when = new Date(System.currentTimeMillis() + 300);

        clock.schedule(event, when);
        clock.cancel(event);

        // aguarda além do horário sem polling
        synchronized (monitor) {
            // espera no máximo 600ms antes de considerar que não houve alarme
            monitor.wait(600);
        }

        assertTrue("Não deve ter disparado, mas disparou: " + fired, fired.isEmpty());
    }
}
