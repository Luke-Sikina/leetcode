class Solution {
    public int reverse(int x) {
        if (x > 1000000000 || x < -1000000000) {
            return 0;
        }
        boolean negative = x < 0;
        x = Math.abs(x);
        int reversed = 0;

        while (x > 0) {
            reversed = reversed * 10;
            reversed += (x % 10);
            x = x / 10;
        }

        return negative ? -1 * reversed : reversed;
    }
}