package com.daimasuixianglu.lianbiao;

/**
 * 虚拟头结点操作
 * 那么可不可以 以一种统一的逻辑来移除 链表的节点呢。
 * 其实可以设置一个虚拟头结点，这样原链表的所有节点就都可以按照统一的方式进行移除了。
 * 来看看如何设置一个虚拟头。依然还是在这个链表中，移除元素1。
 * 这里来给链表添加一个虚拟头结点为新的头结点，此时要移除这个旧头结点元素1。
 * 这样是不是就可以使用和移除链表其他节点的方式统一了呢？
 * 来看一下，如何移除元素1 呢，还是熟悉的方式，然后从内存中删除元素1。
 * 最后呢在题目中，return 头结点的时候，别忘了 return dummyNode->next;， 这才是新的头结点
 */
public class 移除链表元素2 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead=new ListNode(0);
        dummyHead.next=head;
        ListNode cur=dummyHead;
        while (cur.next !=null){
            if (cur.next.val==val){
                cur.next=cur.next.next;
            }else {
                cur=cur.next;
            }
        }
        return dummyHead.next;
    }
}
