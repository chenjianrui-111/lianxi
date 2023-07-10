package com.daimasuixianglu.shejishujujiegou;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在「常数时间」内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * 示例:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
//双栈解法
public class MinStack {
    Deque<Integer> data=new ArrayDeque<>();
    Deque<Integer> help=new ArrayDeque<>();

    public void push(int val){
        data.addLast(val);
        if (help.isEmpty() || help.peekLast() >=val){
            help.addLast(val);
        }else {
            help.addLast(help.peekLast());
        }
    }
    public void pop(){
        data.pollLast();
        help.pollLast();
    }
    public int top(){
        return data.peekLast();
    }
    public int getMin(){
        return help.peekLast();
    }
}
