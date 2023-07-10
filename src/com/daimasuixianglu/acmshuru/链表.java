package com.daimasuixianglu.acmshuru;

import java.util.Scanner;
import java.util.Stack;

public class 链表 {
    //题目描述
    //对于一个链表L:L0->L1->...->Ln
    //将其翻转成L0->Ln->L1->Ln-1->L2->Ln-2->...
    //先构建一个节点类，用于构建链表
    static class LinkNode{
        int val;
        LinkNode next;
        public LinkNode(int val){
            this.val=val;
        }
    }

    public static void main(String[] args) {
        //输入是一串数字，请将其转换成单链表后，在进行操作
        Scanner sc=new Scanner(System.in);
        //以字符串形式输入
        String str=sc.next().toString();
        //通过分隔符将其转换为字符串数组
        String[] arr=str.split(",");
        int[] ints=new int[arr.length];
        for (int i = 0; i <ints.length ; i++) {
            ints[i] = Integer.parseInt(arr[i]);
        }
        Stack<LinkNode> stack=new Stack<>();
        LinkNode head=new LinkNode(0);
        LinkNode p = head;
        //链表初始化并放入stack中
        for (int i = 0; i <ints.length ; i++) {
            p.next=new LinkNode(ints[i]);
            p=p.next;
            stack.add(p);
        }
        head = head.next;
        //开始链表转换
        p = head;
        LinkNode q=stack.peek();
        while ((!p.equals(q)) && (!p.next.equals(q))){
            q = stack.pop();
            q.next = p.next;
            p.next = q;
            p = p.next.next;
            q=stack.peek();
        }
        q.next = null;

        //输出
        while (head != null){
            if (head.next == null){
                System.out.print(head.val);
            }else {
                System.out.print(head.val+",");
            }
            head = head.next;
        }
    }
}
