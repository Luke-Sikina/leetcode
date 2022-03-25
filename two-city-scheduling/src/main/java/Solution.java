import java.util.Arrays;
import java.util.List;

class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int a = 0, b = 0, totalCost = 0, max = costs.length / 2;

        // make list sorted by difference in cost between a and b, highest to lowest
        List<TravelCost> sortedCosts = Arrays.stream(costs)
            .map(c -> new TravelCost(c[0], c[1]))
            .sorted()
            .toList();

        // for each cost in the sorted list, add to the lowest cost city if not full,
        // else, add to the other city
        for (TravelCost cost : sortedCosts) {
            if (cost.a < cost.b) {
                if (a < max) {
                    a++;
                    totalCost += cost.a;
                } else {
                    b++;
                    totalCost += cost.b;
                }
            } else if (b < max) {
                b++;
                totalCost += cost.b;
            } else {
                a++;
                totalCost += cost.a;
            }
        }

        return totalCost;
    }

    private record TravelCost(int a, int b) implements Comparable<TravelCost> {
        @Override
        public int compareTo(TravelCost o) {
            int myDiff = Math.abs(a - b);
            int oDiff = Math.abs(o.a - o.b);
            return -1 * Integer.compare(myDiff, oDiff);
        }
    }
}