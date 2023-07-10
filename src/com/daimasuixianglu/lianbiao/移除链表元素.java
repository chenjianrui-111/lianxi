package com.daimasuixianglu.lianbiao;


/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * 原链表操作
 * 来看第一种操作：直接使用原来的链表来进行移除。
 * 移除头结点和移除其他节点的操作是不一样的，因为链表的其他节点都是通过前一个节点来移除当前节点，而头结点没有前一个节点。
 * 所以头结点如何移除呢，其实只要将头结点向后移动一位就可以，这样就从链表中移除了一个头结点。
 * 这样移除了一个头结点，是不是发现，在单链表中移除头结点 和 移除其他节点的操作方式是不一样，其实在写代码的时候也会发现，需要单独写一段逻辑来处理移除头结点的情况。
 */
public class 移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        //删除头节点
        while (head !=null && head.val==val) {
            head=head.next;
        }
        if (head==null)
            return head;
        //删除非头节点
        ListNode cur=head;
        while (cur!=null && cur.next!=null){
            if (cur.next.val==val){
                cur.next=cur.next.next;
            }else {
                cur=cur.next;
            }
        }
        return head;
    }
}
 class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
