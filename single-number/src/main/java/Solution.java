import java.util.Arrays;

class Solution {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        if (nums[0] != nums[1]) {
            return nums[0];
        }

        for (int i = 1; i < nums.length; i+=2) {
            if (nums[i - 1] != nums[i]) {
                return nums[i-1];
            }
        }

        return nums[nums.length - 1];
    }
}