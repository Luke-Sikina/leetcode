import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    public static void main(String[] args) {
        char[][] chars = {
            {'.','8','7','6','5','4','3','2','1'},
            {'2','.','.','.','.','.','.','.','.'},
            {'3','.','.','.','.','.','.','.','.'},
            {'4','.','.','.','.','.','.','.','.'},
            {'5','.','.','.','.','.','.','.','.'},
            {'6','.','.','.','.','.','.','.','.'},
            {'7','.','.','.','.','.','.','.','.'},
            {'8','.','.','.','.','.','.','.','.'},
            {'9','.','.','.','.','.','.','.','.'}
        };

        System.out.println(new Solution().isValidSudoku(chars));

    }
    private record Coord(int x, int y){}

    public boolean isValidSudoku(char[][] board) {
        Map<Coord, Set<Integer>> vert = new HashMap<>();
        Map<Coord, Set<Integer>> horz = new HashMap<>();
        Map<Coord, Set<Integer>> grid = new HashMap<>();

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                char c = board[x][y];
                if (c == '.') {
                    continue;
                }
                int squareVal = asciiToNum(c);
                Coord vertKey = new Coord(x, 0);
                Coord horzKey = new Coord(0, y);
                Coord gridKey = new Coord(x / 3, y / 3);
                if (!vert.containsKey(vertKey)) {
                    vert.put(vertKey, new HashSet<>());
                }
                if (!horz.containsKey(horzKey)) {
                    horz.put(horzKey, new HashSet<>());
                }
                if (!grid.containsKey(gridKey)) {
                    grid.put(gridKey, new HashSet<>());
                }

                if (!(
                    vert.get(vertKey).add(squareVal) &&
                    horz.get(horzKey).add(squareVal) &&
                    grid.get(gridKey).add(squareVal)
                )) {
                    return false;
                }
            }
        }

        return vert.values().stream().map(s -> s.stream().mapToInt(i -> i).sum()).noneMatch(n -> n > 45)
            && horz.values().stream().map(s -> s.stream().mapToInt(i -> i).sum()).noneMatch(n -> n > 45)
            && grid.values().stream().map(s -> s.stream().mapToInt(i -> i).sum()).noneMatch(n -> n > 45);
    }

    private int asciiToNum(char num) {
        return num - 48;
    }
}