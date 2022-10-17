import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean checkIfPangram(String sentence) {
        Set<Character> chars = new HashSet<>();

        for (char c : sentence.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                chars.add(c);
            }
            if (chars.size() == 26) {
                return true;
            }
        }

        return false;
    }
}