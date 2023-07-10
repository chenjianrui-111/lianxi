package com.cjr.lianbiao;

public class jiOuLianBiao {
    public ListNode oddEvenList(ListNode head) {
        if (head ==null ||head.next==null){
            return head;
        }
        //奇数链表的头结点
        ListNode oddHead=head;
        //奇数链表的当前节点
        ListNode oddCur=oddHead;

        //偶数链表的头结点
        ListNode evenHead=head.next;
        //偶数链表的当前节点
        ListNode evenCur=evenHead;

        while (evenCur !=null &&evenCur.next!=null){
            //奇数节点串在一起
            oddCur.next=oddCur.next.next;
            //偶数节点串在一起
            evenCur.next=evenCur.next.next;
            //奇偶指针后移
            oddCur=oddCur.next;
            evenCur=evenCur.next;
        }
        //最后偶数链表加在奇数链表的后面
        oddCur.next=evenHead;
        return oddHead;
    }
}
