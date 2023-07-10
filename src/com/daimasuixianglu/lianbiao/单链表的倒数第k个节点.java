package com.daimasuixianglu.lianbiao;

public class 单链表的倒数第k个节点 {
    // 主函数
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 删除倒数第 n 个，要先找倒数第 n + 1 个节点
        ListNode x = findFromEnd(dummy, n + 1);
        // 删掉倒数第 n 个节点
        x.next = x.next.next;
        return dummy.next;
    }
    // 返回链表的倒数第 k 个节点
    ListNode findFromEnd(ListNode head, int k){
        ListNode p1= head;
        //p1先走k步
        for (int i = 0; i <k ; i++) {
            p1 = p1.next;
        }
        ListNode p2= head;
        // p1 和 p2 同时走 n - k 步
        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        //p2现在指向第n-k个节点
        return p2;
    }
}
