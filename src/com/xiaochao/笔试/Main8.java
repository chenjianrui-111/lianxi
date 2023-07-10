package com.xiaochao.笔试;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main8 {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            int gap = input.indexOf("]");
            String ops = input.substring(1, gap);
            String par = input.substring(gap + 3, input.lastIndexOf(']'));
            String[] opsArr = ops.split(",");
            String[] parArr = par.split(",");

            StringBuffer sb = new StringBuffer();
            sb.append('[');
            MyQueue cq = new MyQueue();
            for (int i = 0; i < opsArr.length; i++) {
                String op = opsArr[i];
                op = op.substring(op.indexOf("\"") + 1, op.lastIndexOf("\""));
                if (op.equalsIgnoreCase("MYQUEUE")) {
                    cq = new MyQueue();
                    sb.append("null");
                }

                if (op.equalsIgnoreCase("PUSH")) {
                    String p = parArr[i];
                    p = p.substring(p.indexOf("[") + 1, p.lastIndexOf("]"));
                    int tmpInt = Integer.parseInt(p);
                    cq.push(tmpInt);
                    sb.append("null");
                }
                if (op.equalsIgnoreCase("POP")) {
                    int outInt = cq.pop();
                    sb.append(outInt);
                }
                if (op.equalsIgnoreCase("PEEK")) {
                    int outInt = cq.peek();
                    sb.append(outInt);
                }
                if (op.equalsIgnoreCase("EMPTY")) {
                    boolean outBool = cq.empty();
                    sb.append(outBool);
                }
                if (i < opsArr.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append(']');
            System.out.print(sb.toString());
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}
class MyQueue {
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
