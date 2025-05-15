package sis.clock;
import java.util.*;
import java.util.concurrent.locks.*;
import junit.framework.*;

public class ClockTest extends TestCase {
    private Clock clock;
    private Lock lock;
    private Condition receivedEnoughTics;
    private Object monitor = new Object();

    protected void setUp() {
        lock = new ReentrantLock();
        receivedEnoughTics = lock.newCondition();
    }

    public void testClock() throws Exception {
        final int seconds = 5;
        final List<Date> tics = new ArrayList<Date>();
        ClockListener listener = createClockListener(tics, seconds);
        clock = new Clock(listener);
        lock.lock();
        try {
            receivedEnoughTics.await();
        }
        finally {
            lock.unlock();
        }
        clock.stop();
        verify(tics, seconds);
    }
    private ClockListener createClockListener(final List<Date> tics, final int seconds) {
        return new ClockListener() {
            private int count = 0;
            public void update(Date date) {
                tics.add(date);
                if(++count == seconds) {
                    lock.lock();
                    try {
                        receivedEnoughTics.signalAll();
                    }
                    finally {
                        lock.unlock();
                    }
                }
            }
        };
    }

    private void verify(List<Date> tics, int seconds) {
        assertEquals(seconds, tics.size());
        for (int i = 1; i < seconds; i++)
            assertEquals(1, getSecondsFromLast(tics, i));
    }
    private long getSecondsFromLast(List<Date> tics, int i) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(tics.get(i));
            int now = calendar.get(Calendar.SECOND);
        calendar.setTime(tics.get(i - 1));
            int then = calendar.get(Calendar.SECOND);
        if (now == 0)
            now = 60;
        return now - then;
    }
}
