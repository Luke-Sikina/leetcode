/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1), prev = head;
        int overflow = 0;

        while (l1 != null && l2 != null) {
            var cur = new ListNode((l1.val + l2.val + overflow) % 10);
            overflow = (l1.val + l2.val + overflow) / 10;
            prev.next = cur;
            prev = cur;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 == null) {
            l1 = l2;
        }

        while (l1 != null) {
            prev.next = l1;
            prev = l1;
            int sum = l1.val + overflow;
            l1.val = sum % 10;
            overflow = sum / 10;
            l1 = l1.next;
        }

        if (overflow != 0) {
            prev.next = new ListNode(overflow);
        }

        return head.next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */