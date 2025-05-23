package sis.UI;

import java.text.DateFormat;

public class Field {
    private final String name;
    private String label;
    private String shortName;
    private String info;
    private int limit;
    private int columns;
    private boolean upcaseOnly;
    private DateFormat format;
    private Object initialValue;

    public Field(String name) {
        this.name = name;
    }
    public String getName() { return name; }
    public String getLabelName() { return name + "Label"; } // se quiser
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public int getLimit() { return limit; }
    public void setLimit(int limit) { this.limit = limit; }
    public int getColumns() { return columns; }
    public void setColumns(int cols) { this.columns = cols; }
    public boolean isUpcaseOnly() { return upcaseOnly; }
    public void setUpcaseOnly() { this.upcaseOnly = true; }
    public DateFormat getFormat() { return format; }
    public void setFormat(DateFormat fmt) { this.format = fmt; }
    public Object getInitialValue() { return initialValue; }
    public void setInitialValue(Object val) { this.initialValue = val; }
    public String getShortName() {
        return shortName != null
                ? shortName
                : label;
    }
    public void setShortName(String s) {
        this.shortName = s;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
}
