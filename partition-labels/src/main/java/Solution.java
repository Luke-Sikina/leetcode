import java.util.*;

class Solution {
    public List<Integer> partitionLabels(String s) {
        List<CharRange> ranges = calculateRanges(s);
        ranges.sort(new CharRangeComparitor());
        LinkedList<Range> partitions = new LinkedList<>();
        partitions.add(new Range(0, 0));

        for (CharRange range : ranges) {
            if (partitions.getLast().overlaps(range)) {
                partitions.getLast().add(range);
            } else {
                partitions.add(new Range(range.start, range.end));
            }
        }

        return partitions.stream()
            .map(Range::length)
            .toList();
    }

    private static final class CharRangeComparitor implements Comparator<CharRange> {

        /**
         * Compares its two arguments for order.  Returns a negative integer,
         * zero, or a positive integer as the first argument is less than, equal
         * to, or greater than the second.<p>
         */
        @Override
        public int compare(CharRange first, CharRange second) {
            int start = Integer.compare(first.start, second.start);
            int end = Integer.compare(first.end, second.end);
            return start == 0 ? end : start;
        }
    }

    private List<CharRange> calculateRanges(String s) {
        Map<Character, CharRange> ranges = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!ranges.containsKey(c)) {
                ranges.put(s.charAt(i), new CharRange(i, i, s.charAt(i)));
            } else {
                ranges.get(s.charAt(i)).setEnd(i);
            }
        }

        return new ArrayList<>(ranges.values());
    }

    private static final class Range {
        int start, end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean overlaps(CharRange range) {
            return !(range.start > end || range.end < start);
        }

        public void add(CharRange range) {
            start = Math.min(range.start, start);
            end = Math.max(range.end, end);
        }

        public int length() {
            return end + 1 - start;
        }

    }
    private static final class CharRange {
        int start, end;
        char character;

        public CharRange(int start, int end, char character) {
            this.start = start;
            this.end = end;
            this.character = character;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public char getCharacter() {
            return character;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
}