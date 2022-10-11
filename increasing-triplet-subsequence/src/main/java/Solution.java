import java.util.Optional;

public class Solution {
    private record Triplet(int f, Optional<Integer> s, Optional<Integer> t) {
        public Triplet(int f) {
            this(f, Optional.empty(), Optional.empty());
        }

        public Triplet withFirst(int f) {
            return new Triplet(f, Optional.empty(), t);
        }

        public Triplet withSecond(int s) {
            return new Triplet(f, Optional.of(s), t);
        }

        public Triplet withThird(int t) {
            return new Triplet(f, s, Optional.of(t));
        }

        public boolean valid() {
            return s.isPresent() && t.isPresent();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().increasingTriplet(new int[]{1,2,1,2,1,2,1,2,1,2}));
    }

    public boolean increasingTriplet(int[] nums) {
        return solve(nums, new Triplet(nums[0]), 1);
    }

    private boolean solve(int[] nums, Triplet cur, int index) {
        if (cur.valid() || index >= nums.length) {
            return cur.valid();
        }

        int candidate = nums[index];

        if (cur.s.isEmpty()) {
            if (cur.f() < candidate) {
                return solve(nums, cur.withSecond(candidate), index + 1);
            } else if (cur.f() > candidate) {
                return solve(nums, cur.withFirst(candidate), index + 1);
            } else {
                return solve(nums, cur, index + 1);
            }
        } else {
            // if second is full and we haven't returned, that means the third part of the triplet is not present
            // so no need to check that
            if (cur.s().get() < candidate) {
                // if s is smaller than current index, this is the third part of the triplet, and we're solved
                return true;
            } else if (cur.s().get() > candidate) {
                if (cur.f() > candidate) {
                    return solve(nums, cur.withFirst(candidate), index + 1) || solve(nums, cur, index + 1);
                } else if (cur.f() < candidate) {
                    return solve(nums, cur.withSecond(candidate), index + 1);
                } else {
                    return solve(nums, cur, index + 1);
                }
            } else {
                // if current = second, no upgrade, just increment and recurse
                return solve(nums, cur, index + 1);
            }
        }
    }
}
