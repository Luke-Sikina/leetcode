import java.util.Stack;

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;

        for (int p : pushed) {
            stack.push(p);
            while (popIndex < popped.length && !stack.isEmpty() && stack.peek() == popped[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }

        return stack.isEmpty();
    }
}