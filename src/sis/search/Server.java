package sis.search;
import java.util.*;
import java.util.concurrent.*;
import java.util.*;

public class Server extends Thread{
    private BlockingQueue<Search> queue = new LinkedBlockingQueue<Search>();
    private ResultsListener listener;
    static final String START_MSG = "started";
    static final String END_MSG = "finished";

    private static ThreadLocal<List<String>> threahLog = new ThreadLocal<List<String>>() {
        protected List<String> initialValue() {
            return new ArrayList<String>();
        }
    };

    private List<String> completeLog = Collections.synchronizedList(new ArrayList<String>());

    public Server(ResultsListener listener) {
        this.listener = listener;
        start();
    }

    public void run(){
        while(true){
            try {
                execute(queue.take());
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }

    public void shutDown() throws Exception {
        this.interrupt();
    }

    public void add(Search search) throws Exception{
        queue.put(search);
    }

    public List<String> getLog() {
        return completeLog;
    }

    private void execute(Search search) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                log(START_MSG, search);
                search.execute();
                log(END_MSG, search);
                listener.executed(search);
                completeLog.addAll(threahLog.get());
            }
        });
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread th, Throwable thrown) {
                completeLog.add(search + " " + thrown.getMessage());
                listener.executed(search);
            }
        }
        );
        thread.start();
    }

    private void log(String message, Search search) {
        threahLog.get().add(search + " " + message + " at " + new Date());
    }
}
