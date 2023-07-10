package com.daimasuixianglu.zhanheduillie;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 */
public class 有效的括号 {
    public boolean isValid(String s) {
        Deque<Character> deque = new LinkedList<>();
        char ch ;
        for (int i = 0; i <s.length() ; i++) {
            ch=s.charAt(i);
            if (ch == '('){
                deque.push(')');
            }else if (ch == '{') {
                deque.push('}');
            }else if (ch == '[') {
                deque.push(']');
            }else if (deque.isEmpty() ||deque.peek() !=ch){
                return false;
            }else {//如果是右括号判断是否和栈顶元素匹配
                deque.pop();
            }
        }
        //最后判断栈中元素是否匹配
        return deque.isEmpty();
    }
}
