package sis.util;
import junit.framework.TestCase;

public class StringUtilTest extends TestCase{
    public void testAppendNewline() {
        String input = "hello";
        String expected = "hello" + StringUtil.NEWLINE;
        String atual = StringUtil.appendNewLine(input);

        assertEquals(expected, atual);
    }
}
