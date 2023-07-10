package com.xiaochao.笔试;

import java.util.Scanner;
/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
 * 输入描述
 * 链表节点数。 e.g: 5
 * 依次是链表各节点。 e.g:
 * 1
 * 2
 * 3
 * 4
 * 5
 * left 开始反转位置。 e.g: 2
 * right 结束反转位置。e.g: 3
 * 输出描述
 * 翻转后的链表
 * 样例输入
 * 5
 * 1
 * 2
 * 3
 * 4
 * 5
 * 2
 * 3
 * 样例输出
 * 1  3  2  4  5
 * 提示
 * 其中:
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 */

public class 链表反转二 {

    public ListNode<Integer> reverseBetween(ListNode<Integer> head, int left, int right) {
            // base case
            if (left == 1) {
                return reverseN(head, right);
            }
            // 前进到反转的起点触发 base case
            head.next = reverseBetween(head.next, left- 1, right - 1);
            return head;
        }

        ListNode successor = null; // 后驱节点
        // 反转以 head 为起点的 n 个节点，返回新的头结点
        ListNode reverseN(ListNode head, int n) {
            if (n == 1) {
                // 记录第 n + 1 个节点
                successor = head.next;
                return head;
            }
            // 以 head.next 为起点，需要反转前 n - 1 个节点
            ListNode last = reverseN(head.next, n - 1);

            head.next.next = head;
            // 让反转之后的 head 节点和后面的节点连起来
            head.next = successor;
            return last;

        }
}
 class Solution01 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ListNode<Integer> res = null;

        int head_size = 0;
        head_size = in.nextInt();
        ListNode<Integer> head = null, head_curr = null;
        for(int head_i = 0; head_i < head_size; head_i++) {
            int head_item = in.nextInt();
            ListNode<Integer> head_temp = new ListNode<Integer>();
            head_temp.data = head_item;
            head_temp.next = null;

            if (head == null) {
                head = head_curr = head_temp;
            } else {
                head_curr.next = head_temp;
                head_curr = head_temp;
            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }

        int left;
        left = Integer.parseInt(in.nextLine().trim());

        int right;
        right = Integer.parseInt(in.nextLine().trim());

        res = new 链表反转二().reverseBetween(head, left, right);
        while (res != null) {
            System.out.print(String.valueOf(res.data) + " ");
            res = res.next;
        }
        System.out.println();

    }
}
class ListNode<T> {
    public T data;
    public ListNode next;
}
