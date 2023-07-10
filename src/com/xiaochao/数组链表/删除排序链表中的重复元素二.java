package com.xiaochao.数组链表;

public class 删除排序链表中的重复元素二 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy,q=head;
        while (q!=null){
            if (q.next != null && q.val == q.next.val){
                //发现重复节点，跳过这些重复节点
                while (q.next != null && q.val == q.next.val){
                    q = q.next;
                }
                q = q.next;
                //此时q跳过了这一段重复元素
                if (q == null){
                    p.next = null;
                }
                //不过下一段也可能重复，等下一轮while循环判断
            }else {
                //不是重复节点，读到 dummy 后面
                p.next = q;
                p = p.next;
                q = q.next;
            }
        }
        return dummy.next;
    }
}
class Solution{
    // 定义：输入一条单链表头结点，返回去重之后的单链表头结点
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        if (head.val != head.next.val){
            // 如果头结点和身后节点的值不同，则对之后的链表去重即可
            head.next = deleteDuplicates(head.next);
            return head;
        }
        // 如果如果头结点和身后节点的值相同，则说明从 head 开始存在若干重复节点
        // 越过重复节点，找到 head 之后那个不重复的节点
        while (head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        // 直接返回那个不重复节点开头的链表的去重结果，就把重复节点删掉了
        return deleteDuplicates(head.next);
    }
}
