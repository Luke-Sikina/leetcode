import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
    public int[] findBall(int[][] grid) {
        Stream<Position> balls = IntStream.range(0, grid[0].length)
            .mapToObj(Position::new);

        for (int[] row : grid) {
            balls = balls.map(pos -> process(row, pos));
        }

        return balls.mapToInt(pos -> pos.stuck() ? -1 : pos.pos())
            .toArray();
    }

    private record Position(int pos, boolean stuck) {
        Position(int pos) {
            this(pos, false);
        }
    }

    private Position process(int[] row, Position start) {
        if (start.stuck()) {
            return start;
        }

        boolean interior = start.pos() > 0 && start.pos() < row.length - 1;
        boolean stuck = interior && (
            (row[start.pos()] == 1 && row[start.pos() + 1] == -1) ||
            (row[start.pos()] == -1 && row[start.pos() - 1] == 1)
        );
        int nextPos = start.pos() + row[start.pos()];
        stuck = stuck || nextPos < 0 || nextPos >= row.length;

        return new Position(nextPos, stuck);
    }
}