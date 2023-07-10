package com.daimasuixianglu.zhanheduillie;

import java.util.Deque;
import java.util.LinkedList;

/**
 *根据 逆波兰表示法，求表达式的值。
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 注意 两个整数之间的除法只保留整数部分。
 * 可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例 2：
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 示例 3：
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：该算式转化为常见的中缀算术表达式为：
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * 提示：
 * 1 <= tokens.length <= 104
 * tokens[i] 是一个算符（"+"、"-"、"*" 或 "/"），或是在范围 [-200, 200] 内的一个整数
 * 逆波兰表达式主要有以下两个优点：
 * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
 */
public class 逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList();
        for (String token:tokens){
            char c=token.charAt(0);
            if (!isOpe(token)){
                stack.addFirst(stoi(token));
            }else if (c == '+'){
                stack.push(stack.pop()+stack.pop());
            } else if (c == '-') {
                stack.push(- stack.pop() + stack.pop());
            }else if (c == '*') {
                stack.push(stack.pop() * stack.pop());
            }else {
                int num1=stack.pop();
                int num2=stack.pop();
                stack.push(num2/num1);
            }
        }
        return stack.pop();
    }
    private boolean isOpe(String s) {
        return s.length() == 1 && s.charAt(0) <'0' || s.charAt(0) >'9';
    }
    private int stoi(String s) {
        return Integer.valueOf(s);
    }
}
