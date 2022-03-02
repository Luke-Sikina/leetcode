class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        int si = 0;
        for (char tChar : tChars) {
            if (tChar == sChars[si]) {
                si++;
            }
            if (si == sChars.length) {
                return true;
            }
        }

        return false;
    }
}