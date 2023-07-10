package com.daimasuixianglu.lianbiao;

/**
 *给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 双指针的经典应用，如果要删除倒数第n个节点，让fast移动n步，然后让fast和slow同时移动，直到fast指向链表末尾。删掉slow所指向的节点就可以了。
 * 首先这里我推荐大家使用虚拟头结点
 * 定义fast指针和slow指针，初始值为虚拟头结点
 * fast首先走n + 1步 ，为什么是n+1呢，因为只有这样同时移动的时候slow才能指向删除节点的上一个节点
 * fast和slow同时移动，之道fast指向末尾
 * 删除slow指向的下一个节点
 */
public class 删除链表的倒数第N个结点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead=new ListNode(0);
        dummyHead.next=head;
        ListNode fast=head;
        ListNode slow=dummyHead;
        for (int i = 0; i <n ; i++) {
            fast=fast.next;
        }
        while (fast!=null){
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return dummyHead.next;
    }
}
