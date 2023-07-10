package com.daimasuixianglu.lianbiao;

import java.util.PriorityQueue;

public class 合并k个升序链表 {
    public static void main(String[] args) {
    }
    public static ListNode mergeKLists(ListNode[] lists){
        if (lists.length == 0) return null;
        //虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode p =dummy;
        //优先级队列，最小堆
        PriorityQueue<ListNode> pq=new PriorityQueue<>(lists.length,(a,b)->(a.val - b.val));
        //将k个链表的头结点加入最小堆
        for (ListNode head : lists){
            if (head != null)
                pq.add(head);
        }
        while (!pq.isEmpty()){
            // 获取最小节点，接到结果链表中
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null){
                pq.add(node.next);
            }
            //p指针不断前进
            p = p.next;
        }
        return dummy.next;
    }
}
