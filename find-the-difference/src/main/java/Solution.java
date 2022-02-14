import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public char findTheDifference(String s, String t) {
        Map<Character, Long> countsShuffled = countChars(t);
        Map<Character, Long> countsOriginal = countChars(s);
        for (Map.Entry<Character, Long> pair : countsShuffled.entrySet()) {
            if (!countsOriginal.getOrDefault(pair.getKey(), (long)0).equals(pair.getValue())) {
                return pair.getKey();
            }
        }

        return 0;
    }

    private Map<Character, Long> countChars(String s) {
        return s.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}