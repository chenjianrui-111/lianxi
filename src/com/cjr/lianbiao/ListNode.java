package com.cjr.lianbiao;

public class ListNode {
    public ListNode next;
    public Integer val;
    ListNode() {}
    public ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
