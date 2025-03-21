package chess.util;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase{

    public void testAppendNewLine() {
        String hello = "hello world";
        String expected = hello + StringUtil.NEWLINE;
        String atual = StringUtil.appendNewLine(hello);

        assertEquals(expected, atual);

    }
}
