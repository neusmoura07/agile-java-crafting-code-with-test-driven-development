package Lesson13;

import java.util.*;

public class AlarmClock extends Thread{
    private final AlarmListener listener;
    private final Map<String, Date> alarms = new HashMap<>();
    private volatile boolean running = true;

    public AlarmClock(AlarmListener listener) {
        this.listener = listener;
        setDaemon(true);
        start();
    }

    /** Agenda (ou reagenda) um alarme chamado 'name' para disparar em 'time'. */
    public void schedule(String name, Date time) {
        synchronized (alarms) {
            alarms.put(name,time);
        }
    }

    /** Cancela o alarme de nome 'name', se existir. */
    public void cancel(String name) {
        synchronized (alarms) {
            alarms.remove(name);
        }

    }

    /** Para o loop da thread. */
    public void shutdown() {
        running = false;
        synchronized (this){
            notifyAll();
        }
    }

    public void run() {
        Timer timer = new Timer(true);

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                long now = System.currentTimeMillis();
                synchronized (alarms) {
                    Iterator<Map.Entry<String,Date>> it = alarms.entrySet().iterator();
                    while(it.hasNext()) {
                        Map.Entry<String,Date> e = it.next();
                        if(e.getValue().getTime() <= now) {
                            listener.alarm(e.getKey());
                            it.remove();
                        }
                    }
                }
            }
        }, 0 ,500);

        synchronized (this) {
            while (running) {
                try {
                    wait();
                } catch (InterruptedException ignored) {}
            }
        }
        timer.cancel();
    }

}
