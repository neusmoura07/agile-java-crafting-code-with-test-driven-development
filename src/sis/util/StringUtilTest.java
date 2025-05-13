package sis.util;
import junit.framework.TestCase;

public class StringUtilTest extends TestCase{
    private static final String TEXT = "this is it, isn't it";

    public void testOccurrencesOne() {
        assertEquals(1, StringUtil.occurrences(TEXT, "his"));
    }

    public void testOcurrencesNone() {
        assertEquals(0, StringUtil.occurrences(TEXT, "smelt"));
    }

    public void testOcurrencesMany() {
        assertEquals(3, StringUtil.occurrences(TEXT, "is"));
        assertEquals(2, StringUtil.occurrences(TEXT, "it"));
    }

    public void testOcurrencesSearchStringTooLarge() {
        assertEquals(0, StringUtil.occurrences(TEXT, TEXT + "sdfas"));
    }

    public void testAppendNewline() {
        String input = "hello";
        String expected = "hello" + StringUtil.NEWLINE;
        String atual = StringUtil.appendNewLine(input);

        assertEquals(expected, atual);
    }


}
