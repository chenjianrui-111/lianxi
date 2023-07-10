package com.xiaochao.队列和栈;

import java.util.Stack;

/**
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
 */
public class 逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token :tokens){
            if ("+-*/".contains(token)){
                // 是个运算符，从栈顶拿出两个数字进行运算，运算结果入栈
                int a = stack.pop(),b=stack.pop();
                switch (token){
                    case "+":
                        stack.push(a+b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "-":
                        stack.push(b - a);
                        break;
                    case "/":
                        stack.push(b / a);
                        break;
                }
            }
            else {
                //是个数字，则直接入栈
                stack.push(Integer.parseInt(token));
            }
        }
        // 最后栈中剩下一个数字，即是计算结果
        return stack.pop();
    }
}
