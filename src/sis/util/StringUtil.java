package sis.util;

public class StringUtil {
    static final String NEWLINE = System.getProperty("line.separator");

    private StringUtil() {

    }
    public static String appendNewLine (String str) {
        return str + NEWLINE;
    }
}
