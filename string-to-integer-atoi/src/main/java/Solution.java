import java.math.BigDecimal;

class Solution {
    private static final int INT_START = '0';
    private static final int INT_END = '9';
    private static final char DASH = '-';
    private static final char PLUS = '+';
    public int myAtoi(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return 0;
        }
        int sign = s.charAt(0) == DASH ? -1 :1;
        if (s.charAt(0) == DASH || s.charAt(0) == PLUS) {
            s = s.substring(1);
        }

        try {
            return sign * s.chars()
                .takeWhile(c -> c >= INT_START && c <= INT_END)
                .map(c -> c - INT_START)
                .boxed()
                .map(BigDecimal::new)
                .reduce(new BigDecimal(0), (col, cur) -> {
                    return (col.multiply(new BigDecimal(10)).add(cur));
                })
                .intValueExact();
        } catch (ArithmeticException e) {
            return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }
}