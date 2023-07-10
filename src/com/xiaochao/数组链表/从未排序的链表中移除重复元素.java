package com.xiaochao.数组链表;

import java.util.HashMap;

/**
 * 给定一个链表的第一个节点 head ，找到链表中所有出现多于一次的元素，并删除这些元素所在的节点，返回删除后的链表。
 * 示例 1:
 * 输入：head = [1,2,3,2]
 * 输出：[1,3]
 * 解释：2 在链表中出现了两次，所以所有的 2 都需要被删除。删除了所有的 2 之后，我们还剩下 [1,3]。
 */
public class 从未排序的链表中移除重复元素 {
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        HashMap<Integer, Integer> count = new HashMap<>();
        // 先遍历一遍链表，记录每个值出现的次数
        ListNode p = head;
        while (p != null) {
            count.put(p.val, count.getOrDefault(p.val, 0) + 1);
            p = p.next;
        }
        // 虚拟头结点（哨兵节点），存放结果链表
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 再遍历一遍节点，把重复出现的节点剔除
        p = dummy;
        while (p != null) {
            // unique 指针负责寻找不重复的节点
            ListNode unique = p.next;
            while (unique != null && count.get(unique.val) > 1) {
                // 跳过重复节点，直到找到不重复的节点
                unique = unique.next;
            }
            // 接入不重复的节点或尾部空指针
            p.next = unique;
            // p 前进，继续寻找不重复节点
            p = p.next;
        }
        return dummy.next;
    }
}
