package com.xiaochao.队列和栈.单调栈;

import java.util.Stack;

public class 下一个更大元素 {
    int[] nextGreaterElement(int[] nums) {
        int n =nums.length;
        int[] res =new int[n];
        Stack<Integer> stack =new Stack<>();
        for (int i = n - 1;i>=0;i--){
            //判定个子高矮
            while (!stack.isEmpty() && stack.peek() <= nums[i]){
                //挪开
                stack.pop();
            }
            //nums[i]后面更大的元素
            res[i] = stack.isEmpty() ? -1 :stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }
}
