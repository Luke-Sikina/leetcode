import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        return Arrays.stream(words)
            .collect(Collectors.groupingBy(Function.identity()))
            .entrySet()
            .stream()
            .filter(e -> isSubSequence(s, e.getKey()))
            .mapToInt(e -> e.getValue().size())
            .sum();
    }

    private boolean isSubSequence(String fullStr, String sequence) {
        int seqI = 0, fullI = 0;
        while (fullI < fullStr.length() && seqI < sequence.length()) {
            if (sequence.charAt(seqI) == fullStr.charAt(fullI)) {
                seqI++;
            }
            fullI++;
        }
        return seqI == sequence.length();
    }
}