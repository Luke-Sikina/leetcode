import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    private static final Pattern trimmer = Pattern.compile("(^0*)(.*$)");

    public static void main(String[] args) {
        String s = new Solution().removeKdigits("1234567890", 9);
        System.out.println(s);
    }

    public String removeKdigits(String num, int k) {
        Digits digits = new Digits(num);
        if (k == 0) {
            return digits.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
        }

        String rawDigits = removeK(digits, k).stream()
            .map(Object::toString)
            .collect(Collectors.joining());

        Matcher matcher = trimmer.matcher(rawDigits);
        if (matcher.find()) {
            return matcher.group(2).isEmpty() ? "0" : matcher.group(2);
        } else {
            return rawDigits;
        }
    }

    private Digits removeK(Digits start, int k) {
        if (k == 0) {
            return start;
        }
        int minValue = Integer.MAX_VALUE;
        Digits minDigits = null;
        for (int i = 0; i < start.size(); i++) {
            Optional<Digits> maybeDigits = start.removeDigit(i);
            if (maybeDigits.isPresent()) {
                Digits digits = removeK(maybeDigits.get(), k - 1);
                if (digits != null && digits.value() < minValue) {
                    minDigits = digits;
                    minValue = minDigits.value();
                }
            }
        }

        return minDigits;
    }


    private static final class Digits extends ArrayList<Integer> {
        public Digits(String num) {
            super(toDigits(num));
        }

        public Digits(List<Integer> in) {
            super(in);
        }

        private static List<Integer> toDigits(String num) {
            return num.chars()
                .boxed()
                .map(i -> (char) i.intValue())
                .map(c -> Integer.parseInt("" + c))
                .toList();
        }

        public Integer value() {
            if (isEmpty()) {
                return 0;
            }
            return stream()
                .reduce(0, (subtotal, element) -> (subtotal * 10) + element);
        }

        public Optional<Digits> removeDigit(int i) {
            if (i < 0 || i >= size()) {
                return Optional.empty();
            }

            Digits digits = new Digits(Stream.concat(
                subList(0, i).stream(),
                subList(i + 1, size()).stream()
            ).toList());

            return digits.value() == Integer.MAX_VALUE ? Optional.empty() : Optional.of(digits);
        }
    }
}