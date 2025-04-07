package formatter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CountingLogHandler extends Handler {
    private final StringBuilder formattedLog = new StringBuilder();
    private final Map<Level, Integer> counts = new HashMap<>();

    public CountingLogHandler() {
        setFormatter(new LevelCountingFormatter(this));
    }

    public void publish(LogRecord record) {
        counts.merge(record.getLevel(), 1, Integer::sum);
        String formatted = getFormatter().format(record);
        formattedLog.append(formatted);
    }

    public void flush() {}

    public void close() throws SecurityException{}

    public int getCount(Level level) {
        return counts.getOrDefault(level, 0);
    }

    public String getFormattedLog() {
        return formattedLog.toString();
    }
}
