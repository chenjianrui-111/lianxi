package com.daimasuixianglu.lianbiao;

/**
 * 给出一个长度为 n 的单链表和一个值 x ，单链表的每一个值为 listi ，请返回一个链表的头结点，要求新链表中小于 x 的节点全部在大于等于 x 的节点左侧，
 * 并且两个部分之内的节点之间与原来的链表要保持相对顺序不变。
 * 例如：
 * 给出 1->4-> 3-> 2-> 5->2和 x=3
 * 返回1->2->2->4->3->5
 * 数据范围： n<=200 ， -100<=list[i]<=100
 * 进阶：时间复杂度 O(n) ， 空间复杂度 O(1)
 * 示例1
 * 输入
 * {1,4,3,2,5,2},3
 * 输出
 * {1,2,2,4,3,5}
 * 示例2
 * 输入
 * {1,2,3,4,1},5
 * 输出
 * {1,2,3,4,1}
 */
public class 划分链表 {
    public ListNode partition (ListNode head, int x) {
        if (head == null) return null;
        ListNode dummy1=new ListNode(0);
        ListNode dummy2=new ListNode(0);
        ListNode temp1=dummy1;
        ListNode temp2=dummy2;
        while (head != null){
            if (head.val < x){
                temp1.next = head;
                temp1 = temp1.next;
            }else {
                temp2.next = head;
                temp2 = temp2.next;
            }
            head = head.next;
        }
        temp2.next=null;
        temp1.next = dummy2.next;
        return dummy1.next;
    }
}
