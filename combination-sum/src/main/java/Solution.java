import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    public List<List<Integer>> combinationSum(int[] arr, int target) {
        return getTargetCombination(arr, 0, target, new ArrayList<>())
            .collect(Collectors.toList());
    }


    public Stream<List<Integer>> getTargetCombination(int[] sumCandidates, int position, int currentTarget, List<Integer> partialSum) {
        if (currentTarget == 0) {
            return Stream.of(new ArrayList<>(partialSum));
        }
        if (position == sumCandidates.length) {
            return Stream.empty();
        }

        Stream<List<Integer>> first = Stream.empty();
        if (sumCandidates[position] <= currentTarget) {
            partialSum.add(sumCandidates[position]);
            first = getTargetCombination(sumCandidates, position, currentTarget - sumCandidates[position], partialSum);
            // removing the last element because post adding of the value the call came back
            partialSum.remove(partialSum.size() - 1);
        }

        Stream<List<Integer>> second = getTargetCombination(sumCandidates, position + 1, currentTarget, partialSum);
        return Stream.concat(first, second);
    }

}