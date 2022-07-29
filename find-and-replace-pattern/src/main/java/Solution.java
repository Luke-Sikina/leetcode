import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public static void main(String[] args) {
        new Solution().findAndReplacePattern(new String[]{"abc","deq","mee","aqq","dkd","ccc"}, "abb");
    }
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        Pattern regex = createPattern(pattern);
        return Arrays.stream(words)
            .map(regex::matcher)
            .filter(Matcher::find)
            .map(Matcher::group)
            .toList();
    }

    /**
     * Leverage back references to make a regex
     * Example:
     *     p = "abbccc"
     *     regex = "^(\w)\1{0}(\w)\2{1}(\w)\3{2}$"
     */
    private Pattern createPattern(String p) {
        if (p.isEmpty()) {
            return Pattern.compile("^$");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("^");
        int count = 0; int groupCount = 1;
        char prev = '0'; // problem states chars are [a,z], so this should be safe
        for (char c : p.toCharArray()) {
            if (prev == '0') { // start of string
                prev = c;
                count++;
            } else if (prev == c) {
                count++;
            } else {
                addChar(sb, count, groupCount);
                prev = c;
                count = 1;
                groupCount++;
            }
        }
        // add last char of string
        addChar(sb, count, groupCount);
        sb.append("$");
        return Pattern.compile(sb.toString());
    }

    private void addChar(StringBuilder sb, int count, int group) {
        sb.append("(\\w)\\");
        sb.append(group);
        sb.append("{");
        sb.append(count - 1);
        sb.append("}");
    }
}