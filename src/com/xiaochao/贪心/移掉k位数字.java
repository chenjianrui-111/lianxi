package com.xiaochao.贪心;

import java.util.Stack;

/**
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 * 示例 1 ：
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 */
public class 移掉k位数字 {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack =new Stack<>();
        for (char c:num.toCharArray()){
            //单调栈
            if (k > 0 && c < stack.peek() && !stack.isEmpty()){
                stack.pop();
                k--;

            }
            // 防止 0 作为数字的开头
            if (stack.isEmpty() && c == '0') {
                continue;
            }
            stack.push(c);
        }
        //此时栈中都是单调递增的
        while (!stack.isEmpty() && k > 0){
            stack.pop();
            k--;
        }
        if (stack.isEmpty()){
            return "0";
        }
        //将栈中字符转换为字符串
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        // 出栈顺序和字符串顺序是反的
        return sb.reverse().toString();
    }
}
