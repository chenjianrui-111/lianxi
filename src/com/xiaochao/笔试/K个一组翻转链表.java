package com.xiaochao.笔试;

import java.util.Scanner;

public class K个一组翻转链表 {
    //定义Node节点
    static class ListNode {
        public int val;
        public Main.ListNode next = null;

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
        Main.ListNode head = new Main.ListNode(0);
        Main.ListNode tail = head;
        //3.将输入的字符串变为链表节点
        for (String str : strings) {
            Main.ListNode newNode = new Main.ListNode(Integer.valueOf(str));
            tail.next = newNode;
            tail = tail.next;
        }
        head = head.next;
        //每k个反转链表
        Main.ListNode node = reverseGroup(head, k);
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
    public static Main.ListNode reverseGroup(Main.ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;
        Main.ListNode currentNode = head;
        //获取k个元素的首尾节点
        for (int count = 1; count < k; count++) {
            currentNode = currentNode.next;
            //不够K个则返回
            if(currentNode==null)
                return head;
        }
        Main.ListNode next = currentNode.next;
        //对局部链表进行反转
        reverse(head,currentNode);
        head.next=reverseGroup(next,k);
        return currentNode;
    }

    //写一个头尾节点反转的局部函数
    public static Main.ListNode reverse(Main.ListNode head, Main.ListNode tail) {
        if (head == null || head.next == null)
            return head;
        Main.ListNode pre = null;
        Main.ListNode next = null;
        while (pre != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}

class Solution {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null){
            return head;
        }
        //定义一个假的节点。
        ListNode dummy=new ListNode(0);
        //假节点的next指向head。
        // dummy->1->2->3->4->5
        dummy.next=head;
        //初始化pre和end都指向dummy。pre指每次要翻转的链表的头结点的上一个节点。end指每次要翻转的链表的尾节点
        ListNode pre=dummy;
        ListNode end=dummy;

        while(end.next!=null){
            //循环k次，找到需要翻转的链表的结尾,这里每次循环要判断end是否等于空,因为如果为空，end.next会报空指针异常。
            //dummy->1->2->3->4->5 若k为2，循环2次，end指向2
            for(int i=0;i<k&&end != null;i++){
                end=end.next;
            }
            //如果end==null，即需要翻转的链表的节点数小于k，不执行翻转。
            if(end==null){
                break;
            }
            //先记录下end.next,方便后面链接链表
            ListNode next=end.next;
            //然后断开链表
            end.next=null;
            //记录下要翻转链表的头节点
            ListNode start=pre.next;
            //翻转链表,pre.next指向翻转后的链表。1->2 变成2->1。 dummy->2->1
            pre.next=reverse(start);
            //翻转后头节点变到最后。通过.next把断开的链表重新链接。
            start.next=next;
            //将pre换成下次要翻转的链表的头结点的上一个节点。即start
            pre=start;
            //翻转结束，将end置为下次要翻转的链表的头结点的上一个节点。即start
            end=start;
        }
        return dummy.next;


    }
    //链表翻转
    // 例子：   head： 1->2->3->4
    public ListNode reverse(ListNode head) {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.next == null){
            return head;
        }
        //前一个节点指针
        ListNode preNode = null;
        //当前节点指针
        ListNode curNode = head;
        //下一个节点指针
        ListNode nextNode = null;
        while (curNode != null){
            nextNode = curNode.next;//nextNode 指向下一个节点,保存当前节点后面的链表。
            curNode.next=preNode;//将当前节点next域指向前一个节点   null<-1<-2<-3<-4
            preNode = curNode;//preNode 指针向后移动。preNode指向当前节点。
            curNode = nextNode;//curNode指针向后移动。下一个节点变成当前节点
        }
        return preNode;

    }

}
