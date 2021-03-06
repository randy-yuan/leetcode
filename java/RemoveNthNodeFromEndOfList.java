/**
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // 利用快慢两个指针，实现遍历一遍找到末尾开始的第N个元素
    // 巧妙利用额外的头节点，简化程序代码
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode fast = newHead;
        ListNode slow = newHead;
        while (fast.next != null) {
            fast = fast.next;
            if (n <= 0) slow = slow.next;
            n--;
        }
        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        return newHead.next;
    }
}
