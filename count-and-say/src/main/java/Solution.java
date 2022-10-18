import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public static void main(String[] args) {
        new Solution().countAndSay(4);
    }

    public String countAndSay(int n) {
        var cur = "1";

        while (n > 1) {
            n--;
            cur = cur.chars()
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getKey(), a.getKey()))
                .map(e -> "" + e.getValue() + (char)e.getKey().intValue())
                .collect(Collectors.joining());
        }

        return cur;
    }
}