package com.xiaochao.笔试;

import java.util.Scanner;

//每k个链表翻转 ACM模式
public class Main {
    //定义Node节点
    static class ListNode {
        public int val;
        public ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        //1.获取输入信息
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        int k = scanner.nextInt();
        String[] strings = string.split(",");
        //2.创建头结点
        ListNode head = new ListNode(0);
        ListNode tail = head;
        //3.将输入的字符串变为链表节点
        for (String str : strings) {
            ListNode newNode = new ListNode(Integer.valueOf(str));
            tail.next = newNode;
            tail = tail.next;
        }
        head = head.next;
        //每k个反转链表
        ListNode node = reverseGroup(head, k);
        while(node !=null){
            if (node.next == null){
                System.out.print(node.val);
            }else {
                System.out.print(node.val+",");
            }
            node = node.next;
        }
    }

    //不停地取k个进行翻转，如果不够k个，就直接返回,结束
    public static ListNode reverseGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;
        ListNode currentNode = head;
        //获取k个元素的首尾节点
        for (int count = 1; count < k; count++) {
            currentNode = currentNode.next;
            //不够K个则返回
            if(currentNode==null)
                return head;
        }
        ListNode next = currentNode.next;
        //对局部链表进行反转
        reverse(head,currentNode);
        head.next=reverseGroup(next,k);
        return currentNode;
    }

    //写一个头尾节点反转的局部函数
    public static ListNode reverse(ListNode head, ListNode tail) {
        if (head == null || head.next == null)
            return head;
        ListNode pre = null;
        ListNode next = null;
        while (pre != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
