import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        var s = new char[][] {{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}};
        int i = new Solution().nearestExit(s, new int[]{1, 2});
    }
    private record Coord(int x, int y) {}

    private record Soln(List<Coord> steps) implements Comparable<Soln> {
        @Override
        public int compareTo(Soln o) {
            return Integer.compare(steps().size(), o.steps().size());
        }

        public Coord last() {
            return steps.get(steps.size() - 1);
        }
    }

    public int nearestExit(char[][] maze, int[] entrance) {
        PriorityQueue<Soln> solns = new PriorityQueue<>();
        solns.add(new Soln(List.of(new Coord(entrance[0], entrance[1]))));
        maze[entrance[0]][entrance[1]] = '+';

        return solve(solns, maze)
            .map(s -> s.steps().size() - 1) // first coordinate does not count as step
            .orElse(-1);
    }

    private Optional<Soln> solve(PriorityQueue<Soln> solutions, char[][] maze) {
        if (solutions.isEmpty()) {
            return Optional.empty();
        }

        Soln best = solutions.poll();
        if (
            maze[best.last().x()][best.last().y()] != '+' && // special case: start
            (
                best.last().x() == 0 || best.last().y() == 0 ||
                best.last().x() == maze.length - 1 || best.last().y() == maze[0].length - 1
            )
        ) {
            return Optional.of(best);
        }
        maze[best.last().x()][best.last().y()] = '+';
        generateNeighbors(best.last())
            .filter(c -> c.x() >= 0 && c.y() >= 0 && c.x() < maze.length && c.y() < maze[0].length)
            .filter(c -> maze[c.x()][c.y()] == '.')
            .map(c -> new Soln(Stream.concat(best.steps().stream(), Stream.of(c)).toList()))
            .forEach(solutions::add);

        return solve(solutions, maze);
    }

    private Stream<Coord> generateNeighbors(Coord coord) {
        return Stream.of(
            new Coord(coord.x() - 1, coord.y()),
            new Coord(coord.x() + 1, coord.y()),
            new Coord(coord.x(), coord.y() - 1),
            new Coord(coord.x(), coord.y() + 1)
        );
    }
}
