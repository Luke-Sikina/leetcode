class Solution {
    public boolean detectCapitalUse(String word) {
        if (word.toLowerCase().equals(word)) {
            return true;
        }
        if (word.toUpperCase().equals(word)) {
            return true;
        }
        String noFirstChar = word.substring(1);
        return noFirstChar.toLowerCase().equals(noFirstChar);
    }
}