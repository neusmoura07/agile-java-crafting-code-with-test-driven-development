import junit.framework.TestCase;

import java.util.Map;

public class WordCountTest extends TestCase {

    public void testWordCunt() {
        String text = "Hello world! Hello Java. Hello test.";
        WordCount wc = new WordCount(text);

        Map<String, Integer> frequencies = wc.getFrequencies();
        assertEquals(3, (int) frequencies.get("hello"));
        assertEquals(1, (int) frequencies.get("world"));
        assertEquals(1, (int) frequencies.get("java"));
        assertEquals(1, (int) frequencies.get("test"));
        assertNull(frequencies.get("notthere")); // Palavra que não existe
    }

    public void testWordFrequencyReportFormat() {
        String text = "Code test code";
        WordCount wc = new WordCount(text);

        // Testa o formato da string de saída
        assertTrue(wc.getWordFrequencyReport().contains("code: 2"));
        assertTrue(wc.getWordFrequencyReport().contains("test: 1"));
    }
}
