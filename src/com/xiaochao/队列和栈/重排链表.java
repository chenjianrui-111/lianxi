package com.xiaochao.队列和栈;

import com.cjr.lianbiao.ListNode;

import java.util.Stack;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class 重排链表 {
    public void reorderList(ListNode head) {
        Stack<ListNode> stack =new Stack<>();
        ListNode p = head;
        // 先把所有节点装进栈里，得到倒序结果
        while (p != null){
            stack.push(p);
             p = p.next;
        }
        p = head;
        while (p != null){
            //链表尾部的节点
            ListNode lastNode = stack.pop();
            ListNode next = p.next;
            if (lastNode == next || lastNode.next == next){
                // 结束条件，链表节点数为奇数或偶数时均适用
                lastNode.next = null;
                break;
            }
            p.next = lastNode;
            lastNode.next = next;
            p = next;
        }
    }
}
