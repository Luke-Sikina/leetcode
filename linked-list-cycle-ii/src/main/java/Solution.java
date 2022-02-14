import java.util.HashSet;

public class Solution {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();

        while (head != null && visited.add(head)) {
            head = head.next;
        }

        return head;

    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
          val = x;
          next = null;
        }
    }
}