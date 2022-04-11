import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<Integer> gridContents = Arrays.stream(grid)
            .flatMapToInt(Arrays::stream)
            .boxed()
            .toList();
        k = k % gridContents.size();
        List<List<Integer>> shiftedGrid = new ArrayList<>();

        for (int i = 0; i  < gridContents.size(); i++) {
            if (i % grid[0].length == 0) {
                shiftedGrid.add(new ArrayList<>());
            }
            int gridIndex = (gridContents.size() + i - k) % gridContents.size();

            shiftedGrid.get(shiftedGrid.size() - 1).add(gridContents.get(gridIndex));
        }

        return shiftedGrid;
    }
}