class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int count = 0;
        if (nums.length < 3) {
            return count;
        }
        int len = 2;
        int diff = nums[1] - nums[0];

        for (int i = 2; i < nums.length; i++) {
            if (diff == nums[i] - nums[i - 1]) {
                len++;
            } else {
                count += slicesInValidArrOfLength(len);
                len = 2;
                diff = nums[i] - nums[i - 1];
            }
        }

        return count + slicesInValidArrOfLength(len);
    }

    private int slicesInValidArrOfLength(int len) {
        if (len < 3) {
            return 0;
        }
        len = len - 2;
        return (len * (len + 1)) / 2;
    }
}