import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int NOT_SET = -1;

    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> ranges = new ArrayList<>();
        if (nums.length == 0) {
            return ranges;
        }
        int start = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] + 1 != nums[i]) {
                String range = toRange(start, start == i - 1 ? NOT_SET : i - 1, nums);
                ranges.add(range);
                start = i;
            }
        }

        String lastRange = toRange(start, start == nums.length - 1 ? NOT_SET : nums.length - 1, nums);
        ranges.add(lastRange);

        return ranges;
    }

    private String toRange(int start, int end, int[] nums) {
        if (end == NOT_SET) {
            return nums[start] + "";
        }

        return nums[start] + "->" + nums[end];
    }
}