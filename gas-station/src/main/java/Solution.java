import java.util.stream.IntStream;

class Solution {
    public static final int FAIL = -1;

    public int canCompleteCircuit(int[] gas, int[] cost) {
        return IntStream
            .range(0, gas.length)
            .map(i -> canCompleteFrom(i, gas, cost))
            .filter(start -> start > FAIL)
            .findAny()
            .orElse(FAIL);
    }

    private int canCompleteFrom(int start, int[] gas, int[] cost) {
        int i = start;
        int tank = gas[start];
        int steps = 0;
        while (tank > 0 && steps < gas.length) {
            steps++;
            tank = tank - cost[i];
            if (tank < 0) {
                return FAIL;
            }
            i = (i + 1) % gas.length;
            tank = tank + gas[i];
        }

        return steps == gas.length ? start : FAIL;
    }

    public static void main(String[] args) {
        int i = new Solution().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2});
        System.out.println(i);
    }
}