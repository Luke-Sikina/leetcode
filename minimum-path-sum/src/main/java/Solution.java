import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Solution {
    private record Coordinate(int x, int y){}

    private record GridSolution(
        List<Coordinate> path, int[][] grid, Coordinate goal
    ) implements Comparable<GridSolution> {
        public int costEstimate() {
            int knownCost = knownCost();
            // heuristic is taxi cab distance
            var end = path.get(path.size() - 1);
            int estCost = Math.abs(goal.x() - end.x()) + Math.abs(goal().y() - end.y());
            return knownCost + estCost;
        }

        public int knownCost() {
            return path.stream()
                .mapToInt(c -> grid[c.x()][c.y()])
                .sum();
        }

        @Override
        public int compareTo(GridSolution o) {
            return Integer.compare(costEstimate(), o.costEstimate());
        }
    }

    public Stream<Coordinate> getNeighbors(Coordinate s, int[][] grid) {
        return Stream.of(
            new Coordinate(s.x() - 1, s.y()),
            new Coordinate(s.x() + 1, s.y()),
            new Coordinate(s.x(), s.y() - 1),
            new Coordinate(s.x(), s.y() - 1)
        )
        .filter(c -> c.x() >= 0 && c.x() < grid.length && c.y() >= 0 && c.y() < grid[0].length);
    }

    private List<Coordinate> aStarSearch(int[][] grid, Coordinate start, Coordinate goal) {
        PriorityQueue<GridSolution> paths = new PriorityQueue<>();
        paths.add(new GridSolution(List.of(start), grid, goal));

        while (!paths.isEmpty()) {
            GridSolution best = paths.remove();
            getNeighbors(best.path.get(best.path.size() - 1), grid)
                .map(c -> {
                    List<Coordinate> l = new ArrayList<>(best.path());
                    l.add(c);
                    return new GridSolution(l, grid, goal);
                })
                .filter(s -> s.costEstimate())
        }

    }
}