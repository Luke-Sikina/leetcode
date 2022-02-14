import java.util.ArrayList;
import java.util.List;

class Solution {
    public int addDigits(int num) {
        while (num / 10 > 0) {
            num = splitDigits(num).stream()
                .mapToInt(i -> i)
                .sum();
        }

        return num;
    }

    private List<Integer> splitDigits(int toSplit) {
        List<Integer> digits = new ArrayList<>();

        while (toSplit > 0) {
            digits.add(toSplit % 10);
            toSplit = toSplit / 10;
        }

        return digits;
    }
}