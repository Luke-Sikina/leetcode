public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode lH = null, lT = null, gH = null, gT = null;

        while (head != null) {
            if (head.val < x) {
                if (lH == null) {
                    lH = head;
                    lT = head;
                } else {
                    lT.next = head;
                    lT = lT.next;
                }
            } else {
                if (gH == null) {
                    gH = head;
                    gT = head;
                } else {
                    gT.next = head;
                    gT = gT.next;
                }
            }
            head = head.next;
        }

        if (lH == null && gH == null) {
            return head;
        } else if (lH == null) {
            return gH;
        } else if (gH == null) {
            return lH;
        } else {
            lT.next = gH;
            gT.next = null;
            return lH;
        }
    }
}
