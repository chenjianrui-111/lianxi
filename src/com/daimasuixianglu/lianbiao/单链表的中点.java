package com.daimasuixianglu.lianbiao;

public class 单链表的中点 {
    ListNode middleNode(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        // 慢指针指向中点
        return slow;
    }
}
//如果链表长度为偶数，也就是说中点有两个的时候，这个解法返回的节点是靠后的那个节点。
