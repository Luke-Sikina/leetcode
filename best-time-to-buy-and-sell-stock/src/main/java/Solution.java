import java.util.ArrayList;
import java.util.function.Function;

class Solution {
    public int maxProfit(int[] prices) {
        prices = deduplicate(prices);
        int maxProfit = 0;
        if (prices.length <= 1) {
            return maxProfit;
        }

        Pair relativeMax = new Pair(-1, Integer.MIN_VALUE);
        for (int minI = 0; minI < prices.length - 1; minI++) {
            relativeMax = relativeMax.index >= minI + 1 ? relativeMax : max(prices, minI);
            maxProfit = Math.max(maxProfit, relativeMax.value - prices[minI]);
        }
        return maxProfit;
    }

    private int[] deduplicate(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int prev = arr[0];
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (prev != arr[i]) {
                ret.add(prev);
            }
            prev = arr[i];
        }
        ret.add(arr[arr.length - 1]);

        return ret.stream().mapToInt(i -> i).toArray();
    }

    private Pair max(int[] prices, int minI) {
        var max = new Pair(0, Integer.MIN_VALUE);

        for (int i = minI + 1; i < prices.length; i++) {
            max = max.value < prices[i] ? new Pair(i, prices[i]) : max;
        }

        return max;
    }

    private record Pair(int index, int value){};

//    public static void main(String[] args) {
//        int i = new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4});
//        System.out.println(i);
//    }
}