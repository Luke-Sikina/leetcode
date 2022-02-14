import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> anagrams = new ArrayList<>();

        Map<Character, Long> required = p.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Character previous = null;

        for (int i = 0; i < s.toCharArray().length; i++) {
            if (i >= p.length()) {
                addIfPresent(required, s.charAt(i - p.length()), 1);
            }
            addIfPresent(required, s.charAt(i), -1);
            if (allBucketsEmpty(required)) {
                anagrams.add(i - (p.length() - 1));
            }
            previous = s.charAt(i);
        }

        return anagrams;
    }

    private void addIfPresent(Map<Character, Long> map, char c, long toAdd) {
        Long count = map.get(c);
        if (count != null) {
            map.put(c, count + toAdd);
        }
    }

    private boolean allBucketsEmpty(Map<?, Long> buckets) {
        long sum = buckets
            .values()
            .stream()
            .mapToLong(l -> l)
            .map(Math::abs) // you could have some negatives
            .sum();

        return sum == 0;
    }
//
//    public static void main(String[] args) {
//        List<Integer> anagrams = new Solution().findAnagrams("abab", "ab");
//        System.out.println(anagrams);
//    }
}