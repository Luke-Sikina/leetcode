class Solution {
    private static final int BASE_ASCII = 'A' - 1;
    private static final int NUMERIC_BASE = 26; // we're counting in base 26

    public int titleToNumber(String columnTitle) {
        return columnTitle.chars()
            .map(c -> c - BASE_ASCII)
            .reduce(0, (acc, cur) -> cur + acc * NUMERIC_BASE);
    }
}