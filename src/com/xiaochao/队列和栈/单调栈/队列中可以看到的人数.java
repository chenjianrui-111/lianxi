package com.xiaochao.队列和栈.单调栈;

import java.util.Stack;

/**
 * 有 n 个人排成一个队列，从左到右 编号为 0 到 n - 1。给你以一个整数数组 heights，每个整数互不相同，heights[i] 表示第 i 个人的高度。
 * 一个人能 看到 他右边另一个人的条件是这两人之间的所有人都比他们两人矮。
 * 请你返回一个长度为 n 的数组 answer ，其中 answer[i] 是第 i 个人在他右侧队列中能 看到的人数。
 * 输入：heights = [10,6,8,5,11,9]
 * 输出：[3,1,2,1,1,0]
 * 解释：
 * 第 0 个人能看到编号为 1 ，2 和 4 的人。
 * 第 1 个人能看到编号为 2 的人。
 * 第 2 个人能看到编号为 3 和 4 的人。
 * 第 3 个人能看到编号为 4 的人。
 * 第 4 个人能看到编号为 5 的人。
 * 第 5 个人谁也看不到因为他右边没人。
 */
public class 队列中可以看到的人数 {
    public int[] canSeePersonsCount(int[] heights) {
        int n =heights.length;
        int[] res = new int[n];
        // int[] 记录 {身高，小于等于该身高的人数} 二元组
        Stack<Integer> stk = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            // 记录右侧比自己矮的人
            int count = 0;
            // 单调栈模板，计算下一个更大或相等元素（身高）
            while (!stk.isEmpty() && heights[i] > stk.peek()) {
                stk.pop();
                count++;
            }
            // 不仅可以看到比自己矮的人，如果后面存在更高的的人，也可以看到这个高人
            res[i] = stk.isEmpty() ? count : count + 1;
            // 单调栈中放入下标，保证单调递减（栈为空或者h[i]<h[j]）
            stk.push(heights[i]);
        }
        return  res;
    }
}
