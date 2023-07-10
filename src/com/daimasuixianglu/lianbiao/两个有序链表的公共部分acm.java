package com.daimasuixianglu.lianbiao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 给定两个升序链表，打印两个升序链表的公共部分。
 * 输入描述:
 * 第一个链表的长度为 n。
 * 第二个链表的长度为 m。
 * 链表结点的值为 val。
 * 输出描述:
 * 输出一行整数表示两个升序链表的公共部分的值 (按升序输出)。
 * 示例1
 * 输入
 * 4
 * 1 2 3 4
 * 5
 * 1 2 3 5 6
 * 输出
 * 1 2 3
 */
public class 两个有序链表的公共部分acm {
    //单链表结构
    static class Node{
        int value;
        Node next;
        Node(int value){
            this.value = value;
        }
    }

    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static int nextInt(){
        try {
            st.nextToken();
            return (int)st.nval;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static Node create(){
        int N=nextInt();
        if (N == 0){
            return null;
        }
        Node cur=null;
        Node head=null;
        for (int i = 0; i < N ; i++) {
            int value = nextInt();
            if (head == null){
                Node node=new Node(value);
                head = node;
                cur = head;
            }else {
                Node node=new Node(value);
                cur.next = node;
                cur = node;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Node n1 = create();
        Node p1 = n1;
        Node n2 = create();
        Node p2 = n2;
        StringBuilder sb=new StringBuilder();
        while (p1 != null && p2 != null){
            if (p1.value == p2.value){
                sb.append(p1.value).append(" ");
                p1=p1.next;
                p2=p2.next;
            }else if (p1.value > p2.value){
                p2=p2.next;
            }else {
                p1=p1.next;
            }
        }
        System.out.println(sb.toString());
    }
}
