import java.util.Arrays;

public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        if (piles.length == 1) {
            return piles[0] / h + (piles[0] % h == 0 ? 0 : 1);
        }

        int max = Arrays.stream(piles).max().orElse(-1);
        int min = Arrays.stream(piles).min().orElse(-1);
        int step = max - min;
        int speed = max;
        int spare = Integer.MAX_VALUE;

        while (step > 1 || spare < 0) {
            //binary search to get next speed
            step = (step + 1) / 2;
            speed = spare >= 0 ? speed - step : speed + step;

            // evaluate speed
            int hours = 0;
            for (int pile : piles) {
                hours += pile / speed + (pile % speed == 0 ? 0 : 1);
            }
            spare = h - hours;
        }

        return speed;
    }

    public static void main(String[] args) {
        int i = new Solution().minEatingSpeed(
            new int[]{332484035, 524908576, 855865114, 632922376, 222257295, 690155293, 112677673, 679580077, 337406589, 290818316, 877337160, 901728858, 679284947, 688210097, 692137887, 718203285, 629455728, 941802184},
            823855818
        );
        // ans 14
    }
}