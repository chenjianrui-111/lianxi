package com.daimasuixianglu.shejishujujiegou;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通队列的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 */
//「双队列」解法
//我们可以使用「两个队列」来实现栈：
//一个 data 队列，用于存储元素。
//一个 help队列，帮助实现 LIFO 的输出。
//通常「倒腾」两个队列的操作可以放在「输入」的 push() 里面，也可以放在「输出」的 pop() 和 peek() 中。
public class MyStack {
    Deque<Integer> data = new ArrayDeque<>();
    Deque<Integer> help = new ArrayDeque<>();

    public void push(int x){
        data.addLast(x);
    }
    public int pop(){
        while (data.size()>1){
            help.addLast(data.pollFirst());
        }
        int u=data.pollFirst();
        swap();
        return u;
    }
    public int top(){
        while (data.size() > 1) {
            help.addLast(data.pollFirst());
        }
        int u=data.peekFirst();
        help.addLast(data.pollFirst());
        swap();
        return u;
    }
    public boolean empty(){
        return data.isEmpty() && help.isEmpty();
    }
    void swap(){
        Deque<Integer> tmp=data;
        data=help;
        help=tmp;
    }
}
class MyStack01 {

    // Deque 接口继承了 Queue 接口
    // 所以 Queue 中的 add、poll、peek等效于 Deque 中的   //addLast、pollFirst、peekFirst
    Deque<Integer> deque;
    public MyStack01() {
        deque=new ArrayDeque<>();
    }

    public void push(int x) {
        deque.addLast(x);
    }

    public int pop() {
        int size=deque.size();
        size--;
        while (size-->0){
            deque.addLast(deque.peekFirst());
            deque.pollFirst();
        }
        int res = deque.pollFirst();
        return res;
    }

    public int top() {
        return deque.peekLast();
    }

    public boolean empty() {
        return deque.isEmpty();
    }
}
