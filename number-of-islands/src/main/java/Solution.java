class Solution {
    private static final char LAND = '1';
    private static final char WATER = '0';

    public int numIslands(char[][] grid) {
        int count = 0;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == LAND) {
                    zeroOutIsland(grid, x, y);
                    count++;
                }
            }
        }

        return count;
    }

    private void zeroOutIsland(char[][] grid, int x, int y) {
        grid[x][y] = WATER;
        if (x > 0 && grid[x - 1][y] == LAND) {
            zeroOutIsland(grid, x - 1, y);
        }
        if (x + 1 < grid.length && grid[x + 1][y] == LAND) {
            zeroOutIsland(grid, x + 1, y);
        }

        if (y > 0 && grid[x][y - 1] == LAND) {
            zeroOutIsland(grid, x, y - 1);
        }
        if (y + 1 < grid[0].length && grid[x][y + 1] == LAND) {
            zeroOutIsland(grid, x, y + 1);
        }
    }
}