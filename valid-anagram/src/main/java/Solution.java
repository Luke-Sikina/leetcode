import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Integer, Long> sCounts = countChars(s);
        Map<Integer, Long> tCounts = countChars(t);

        if (sCounts.size() != tCounts.size()) {
            return false;
        }

        return sCounts.entrySet().stream()
            .mapToLong(e -> e.getValue() - tCounts.getOrDefault(e.getKey(), 0L))
            .filter(i -> i != 0)
            .findAny()
            .isEmpty();
    }

    private Map<Integer, Long> countChars(String s) {
        return s.chars().
            boxed().
            collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}