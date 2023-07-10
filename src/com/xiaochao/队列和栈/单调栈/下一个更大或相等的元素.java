package com.xiaochao.队列和栈.单调栈;

import java.util.Stack;

public class 下一个更大或相等的元素 {
    // 计算 nums 中每个元素的下一个更大或相等的元素
    int[] nextGreaterOrEqualElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stk = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            // 把这里改成 < 号
            while (!stk.isEmpty() && stk.peek() < nums[i]) {
                stk.pop();
            }
            // 现在栈顶就是 nums[i] 身后的大于等于 nums[i] 的元素
            res[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(nums[i]);
        }
        return res;
    }
}
