package com.daimasuixianglu.lianbiao;

/**
 * 给定一个链表，请判断该链表是否为回文结构。
 * 回文是指该字符串正序逆序完全一致。
 * 数据范围： 链表节点数 ，链表中每个节点的值满足
 * 示例1
 * 输入
 * {1}
 * 输出
 * true
 * 示例2
 * 输入
 * {2,1}
 * 输出
 * false
 * 说明
 * 2->1
 * 示例3
 * 输入
 * {1,2,2,1}
 * 输出
 * true
 * 说明
 * 1->2->2->1
 */
public class 判断一个链表是否为回文结构 {
    public boolean isPail (ListNode head) {
        // write code here
       ListNode slow,fast;
       slow=fast=head;
       while (fast != null && fast.next != null){
           slow=slow.next;
           fast=fast.next.next;
       }
       if (fast != null){
           slow=slow.next;
       }
       ListNode left = head;
       ListNode right=reverse(slow);
       while (right != null){
           if (left.val !=right.val)
               return false;
           left = left.next;
           right= right.next;
       }
       return true;
    }
    ListNode reverse(ListNode head){
        ListNode pre = null,cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
//算法总体的时间复杂度 O(N)，空间复杂度 O(1)
/**
 *这种解法虽然高效，但破坏了输入链表的原始结构，能不能避免这个瑕疵呢？
 * 其实这个问题很好解决，关键在于得到p, q这两个指针位置：
 * 只要在函数 return 之前加一段代码即可恢复原先链表顺序：
 * p.next = reverse(q);
 */
