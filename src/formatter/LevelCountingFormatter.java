package formatter;

import java.util.logging.*;


public class LevelCountingFormatter extends Formatter {
    private final CountingLogHandler counter;

    public String format(LogRecord record) {
        String levelName = record.getLevel().getName();
        String msg = formatMessage(record);
        StringBuilder sb = new StringBuilder();

        sb.append(levelName).append(": ").append(msg);

        if(counter != null) {
            int total = counter.getCount(record.getLevel());
            sb.append(" (").append(levelName).append(" total = ").append(total).append(")");
        }

        sb.append(System.lineSeparator());
        return sb.toString();
    }

    public LevelCountingFormatter() {
        this.counter = null;
    }

    public LevelCountingFormatter(CountingLogHandler counter) {
        this.counter = counter;
    }
}
