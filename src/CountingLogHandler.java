import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CountingLogHandler extends Handler {
    private int infoCount = 0;
    private int warningCount = 0;
    private int severeCount = 0;

    public void publish(LogRecord record) {
        if(record.getLevel() == Level.INFO) {
            infoCount++;
        } else if (record.getLevel() == Level.WARNING) {
            warningCount++;
        } else if (record.getLevel() == Level.SEVERE) {
            severeCount++;
        }
    }

    public void flush() {}

    public void close() throws SecurityException{}

    public int getInfoCount() {
        return infoCount;
    }

    public int getWarningCount() {
        return warningCount;
    }

    public int getSevereCount() {
        return severeCount;
    }
}
