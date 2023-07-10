package com.xiaochao.队列和栈;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s，判断字符串是否有效。
 * 有效字符串需满足：
 * 1、 左括号必须用相同类型的右括号闭合。
 * 2、 左括号必须以正确的顺序闭合。
 * 示例 1：
 * 输入：s = "([)]"
 * 输出：false
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 */
public class 有效的括号 {
    public boolean isValid(String s) {
        Stack<Character> stack =new Stack<>();
        for (char c:s.toCharArray()){
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else //字符c是右括号
            if (!stack.isEmpty() && leftOf(c) == stack.peek())
                stack.pop();
            else
                // 和最近的左括号不匹配
                return false;
        }
        // 是否所有的左括号都被匹配了
        return stack.isEmpty();
    }
    char leftOf(char c){
        if (c == '}') return '{';
        if (c == ')') return '(';
        return '[';
    }
}
