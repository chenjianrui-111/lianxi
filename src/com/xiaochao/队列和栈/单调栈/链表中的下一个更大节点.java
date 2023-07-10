package com.xiaochao.队列和栈.单调栈;

import com.cjr.lianbiao.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 给定一个长度为 n 的链表 head
 * 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
 * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
 */
public class 链表中的下一个更大节点 {
    public int[] nextLargerNodes(ListNode head) {
        // 把单链表转化成数组，方便通过索引访问
        ArrayList<Integer> nums = new ArrayList<>();
        for (ListNode p = head; p != null; p = p.next) {
            nums.add(p.val);
        }
        // 存放答案的数组
        int[] res = new int[nums.size()];
        Stack<Integer> stk = new Stack<>();
        // 单调栈模板，求下一个更大元素，从后往前遍历
        for (int i = nums.size() - 1; i >= 0; i--) {
            while (!stk.isEmpty() && stk.peek() <= nums.get(i)) {
                stk.pop();
            }
            // 本题要求没有下一个更大元素时返回 0
            res[i] = stk.isEmpty() ? 0 : stk.peek();
            stk.push(nums.get(i));
        }
        return res;
    }
}
