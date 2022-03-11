class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        TailLen tailLen = calculateTailAndLen(head);
        k = k % tailLen.len;
        if (k == 0) {
            return head;
        }
        k = tailLen.len - k; // rotating left is easier than right
        tailLen.tail.next = head; // create loop

        while (k > 1) {
            k --;
            head = head.next;
        }

        ListNode newHead = head.next;

        head.next = null; // cut loop
        return newHead;
    }

    private TailLen calculateTailAndLen(ListNode head) {
        int len = 1;
        while (head.next != null) {
            head = head.next;
            len ++;
        }
        return new TailLen(head, len);
    }

    private record TailLen(ListNode tail, int len) {}
}