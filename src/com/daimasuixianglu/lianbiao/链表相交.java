package com.daimasuixianglu.lianbiao;

/**
 *给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。
 * 换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
 *设「第一个公共节点」为 node ，「链表 headA」的节点数量为 aa ，「链表 headB」的节点数量为 bb ，「两链表的公共尾部」的节点数量为 cc ，则有：
 * 头节点 headA 到 node 前，共有 a - ca−c 个节点；
 * 头节点 headB 到 node 前，共有 b - cb−c 个节点；
 * 考虑构建两个节点指针 A​ , B 分别指向两链表头节点 headA , headB ，做如下操作：
 * 指针 A 先遍历完链表 headA ，再开始遍历链表 headB ，当走到 node 时，共走步数为：
 * a + (b - c)
 * 指针 B 先遍历完链表 headB ，再开始遍历链表 headA ，当走到 node 时，共走步数为：
 * b + (a - c)
 * 如下式所示，此时指针 A , B 重合，并有两种情况：
 * a + (b - c) = b + (a - c)
 * 若两链表 有 公共尾部 (即 c > 0c>0 ) ：指针 A , B 同时指向「第一个公共节点」node 。
 * 若两链表 无 公共尾部 (即 c = 0c=0 ) ：指针 A , B 同时指向 null 。
 * 因此返回 A 即可。
 */
public class 链表相交 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA=headA;
        ListNode curB=headB;
        while (curA!=curB){
            curA=curA!=null?curA.next:headB;
            curB=curB!=null?curB.next:headA;
        }
        return curA;
    }
}
