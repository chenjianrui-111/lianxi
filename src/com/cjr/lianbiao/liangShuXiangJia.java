package com.cjr.lianbiao;

public class liangShuXiangJia {
    public ListNode addTwoNumbers(ListNode listNode1, ListNode listNode2) {
        //创建一个哑节点，它的指针指向新链表的头节点
        ListNode dummyNode=new ListNode(0);
        //preNode表示当前节点的前一个节点
        ListNode preNode=dummyNode;
        //表示两个节点相加进位的值，加法最多有一个进位，要么是零要么是一
        int carry=0;
        //两个链表只要有一个不为空，或者有进位就一直循环
        while (listNode1 !=null ||listNode2 !=null ||carry!=0){
            //当前节点的累加值，需要加上前面进位的值
            int sum=carry;
            if (listNode1 !=null){
                sum+=listNode1.val;
                listNode1=listNode1.next;
            }
            if (listNode2!=null){
                sum+=listNode2.val;
                listNode2=listNode2.next;
            }
            //创建新的节点，preNode的next指针指向新的节点，因为链表节点
            //只能存储一位数字，所以这里要对sum求余，取个位数。
            preNode.next = new ListNode(sum % 10);
            //如果sum大于等于10，说明有进位，carry为1，
            //否则没有，carry为0
            carry = sum / 10;
            //更新preNode
            preNode = preNode.next;

        }
        return dummyNode.next;
    }
}
