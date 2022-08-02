class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        k--;
        var y = k % matrix.length;
        var x = k / matrix.length;
        return matrix[x][y];
    }
}