import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public int majorityElement(int[] nums) {
        float length = nums.length;

        return Arrays.stream(nums)
            .boxed()
            // bucket values
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            // find bucket with majority count
            .filter(e -> ((float) e.getValue() / length) >= 0.5)
            .findAny()
            .orElseThrow()
            .getKey();
    }
}