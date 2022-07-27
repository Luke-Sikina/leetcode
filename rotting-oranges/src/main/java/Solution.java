import java.util.*;

class Solution {
    private record Coordinate(int x, int y){}
    private static final int FRESH = 1, ROTTEN = 2;

    public int orangesRotting(int[][] grid) {
        int fresh = (int) Arrays.stream(grid)
            .flatMapToInt(Arrays::stream)
            .filter(i -> i == FRESH)
            .count();
        Set<Coordinate> coordsToRot = new HashSet<>();
        int iterations = 0, oldFresh = 0;

        while (fresh > 0 && fresh != oldFresh) {
            iterations++;
            oldFresh = fresh;
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[x].length; y++) {
                    if (grid[x][y] == ROTTEN) {
                        if (x - 1 >= 0 && grid[x - 1][y] == FRESH) {
                            coordsToRot.add(new Coordinate(x - 1, y));
                        }
                        if (x + 1 < grid.length && grid[x + 1][y] == FRESH) {
                            coordsToRot.add(new Coordinate(x + 1, y));
                        }
                        if (y - 1 >= 0 && grid[x][y - 1] == FRESH) {
                            coordsToRot.add(new Coordinate(x, y - 1));
                        }
                        if (y + 1 < grid[0].length && grid[x][y + 1] == FRESH) {
                            coordsToRot.add(new Coordinate(x, y + 1));
                        }
                    }
                }
            }
            // batch mutations to state, execute between iterations;
            coordsToRot.forEach(c -> grid[c.x()][c.y()] = ROTTEN);
            fresh -= coordsToRot.size();
            coordsToRot.clear();
        }

        return fresh > 0 ? -1 : iterations;
    }
}