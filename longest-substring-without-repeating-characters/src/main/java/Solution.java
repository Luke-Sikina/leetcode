import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int best = 0;
        List<Set<Character>> actives = new ArrayList<>();
        for (char c : s.toCharArray()) {
            Map<Boolean, List<Set<Character>>> newActives = actives.stream()
                .collect(Collectors.groupingBy(a -> a.add(c)));
            int maybeBest = newActives.getOrDefault(false, new ArrayList<>()).stream().mapToInt(Set::size).max().orElse(best);
            best = Math.max(best, maybeBest);
            actives = newActives.getOrDefault(true, new ArrayList<>());
            actives.add(new HashSet<>(Set.of(c)));
        }

        int maybeBest = actives.stream().mapToInt(Set::size).max().orElse(best);
        best = Math.max(best, maybeBest);

        return best;
    }

    public static void main(String[] args) {
        Map<String, List<String>> foo = new HashMap<>();
        foo.computeIfAbsent("a", s -> foo.put("a", new ArrayList<>()));
        System.out.println(foo.get("a"));
    }
}