class Solution {
    public int findDuplicate(int[] nums) {
        int sum = nums.length * (nums.length - 1) / 2;
        for (int num : nums) {
            sum -= num;
        }
        return -1 * sum;
    }
}