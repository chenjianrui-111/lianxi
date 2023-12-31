package com.daimasuixianglu.lianbiao;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 如果再定义一个新的链表，实现链表元素的反转，其实这是对内存空间的浪费。
 * 其实只需要改变链表的next指针的指向，直接将链表反转 ，而不用重新定义一个新的链表
 * 双指针法
 * 首先定义一个cur指针，指向头结点，再定义一个pre指针，初始化为null。
 * 然后就要开始反转了，首先要把 cur->next 节点用tmp指针保存一下，也就是保存一下这个节点。
 * 为什么要保存一下这个节点呢，因为接下来要改变 cur->next 的指向了，将cur->next 指向pre ，此时已经反转了第一个节点了。
 * 接下来，就是循环走如下代码逻辑了，继续移动pre和cur指针。
 * 最后，cur 指针已经指向了null，循环结束，链表也反转完毕了。此时我们return pre指针就可以了，pre指针就指向了新的头结点。
 */
public class 反转链表 {
    public ListNode reverseList(ListNode head) {
        ListNode temp;
        ListNode cur=head;
        ListNode pre=null;
        while (cur!=null){
            temp=cur.next;
            cur.next=pre;//反转操作
            //更新pre和cur的指针
            pre=cur;
            cur=temp;
        }
        return pre;
    }
}
