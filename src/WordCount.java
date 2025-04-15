import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordCount {
    private Map<String, Integer> frequencies;

    public WordCount(String text) {
        frequencies = new HashMap<>();
        countWords(text);
    }

    private void countWords(String text) {
        String lowerText = text.toLowerCase();
        String[] words = lowerText.split("\\W+");

        for (String word : words) {
            if(word.isEmpty())
                continue;
            int count = frequencies.getOrDefault(word, 0);
            frequencies.put(word, count + 1);
        }
    }

    public Map<String, Integer> getFrequencies() {
        return frequencies;
    }

    public Set<String> getWordFrequencyReport() {
        Set<String> report = new HashSet<>();
        for(Map.Entry<String, Integer> entry : frequencies.entrySet()){
            report.add(entry.getKey() + ": " + entry.getValue());
        }
        return report;
    }
}
