package com.xiaochao.队列和栈.单调栈;

import java.util.Stack;

/**
 * 注意之前我们的 for 循环都是从数组的尾部开始往栈里添加元素，这样栈顶元素就是 nums[i] 之后的元素。
 * 所以只要我们从数组的头部开始往栈里添加元素，栈顶的元素就是 nums[i] 之前的元素，即可计算 nums[i] 的上一个更大元素。
 */
public class 上一个更大元素 {
    // 计算 nums 中每个元素的上一个更大元素
    int[] prevGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stk = new Stack<>();
        // 因为是求 nums[i] 前面的元素，所以正着往栈里放
        for (int i = 0; i < n; i++) {
            // 删掉 nums[i] 前面较小的元素
            while (!stk.isEmpty() && stk.peek() <= nums[i]) {
                stk.pop();
            }
            // 现在栈顶就是 nums[i] 前面的更大元素
            res[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(nums[i]);
        }
        return res;
    }
}
