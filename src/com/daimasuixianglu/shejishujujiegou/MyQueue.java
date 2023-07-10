package com.daimasuixianglu.shejishujujiegou;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 * 实现 MyQueue 类：
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 */
//我们创建两个栈，分别为 out 和 in：
//「in 用作处理输入操作 push()，使用 in 时需确保 out 为空」
//「out 用作处理输出操作 pop() 和 peek()，使用 out 时需确保 in 为空」
public class MyQueue {
    Deque<Integer> out,in;
    public MyQueue(){
        in=new ArrayDeque<>();
        out=new ArrayDeque<>();
    }
    public void push(int x){
        while (!out.isEmpty()) in.addLast(out.pollLast());
        in.addLast(x);
    }
    public int pop(){
        while (!in.isEmpty()) out.addLast(in.pollLast());
        return out.pollLast();
    }
    public int peek(){
       while (!in.isEmpty()) out.addLast(in.pollLast());
       return out.peekLast();
    }
    public boolean empty(){
        return out.isEmpty() && in.isEmpty();
    }
}
//时间复杂度：O(n)
//空间复杂度：O(n)
class MyQueue01 {
    Deque<Integer> out, in;
    public MyQueue01() {
        in = new ArrayDeque<>();
        out = new ArrayDeque<>();
    }

    public void push(int x) {
        in.addLast(x);
    }

    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) out.addLast(in.pollLast());
        }
        return out.pollLast();
    }

    public int peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) out.addLast(in.pollLast());
        }
        return out.peekLast();
    }

    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
}
//时间复杂度：pop() 和 peek() 操作都是均摊O(1)
//空间复杂度：O(n)
