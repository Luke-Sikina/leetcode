import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String minRemoveToMakeValid(String s) {
        Queue<Integer> openParens = new LinkedList<>();
        List<Character> result = new ArrayList<>();
        int offset = 0;

        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '(') {
                openParens.add(i);
                result.add(c);
            } else if (c == ')') {
                if (openParens.isEmpty()) {
                    offset++; // removing this char
                } else {
                    openParens.poll(); // remove matching open paren
                    result.add(c);
                }
            } else {
                // normal char
                result.add(c);
            }
        }

        // remove remaining unmatched open parens
        for (Integer openParen : openParens) {
            result.remove(openParen - offset);
            offset++;
        }

        return result.stream()
            .map(Object::toString)
            .collect(Collectors.joining());
    }
}