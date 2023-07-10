package com.cjr.paixu;

public class 对链表进行插入排序 {
    public ListNode insertionSortList(ListNode head) {
        if (head==null){
            return null;
        }
        //创建哑节点，用于将在head前插入节点转化为哑节点后插入，统一处理
        ListNode dummyHead=new ListNode(0);
        dummyHead.next=head;
        //记录已经排序完成的节点末尾
        ListNode lastSorted=head;
        //当前需要插入的新节点
        ListNode current=head.next;
        while (current!=null){
            if (lastSorted.val<current.val){
                //当新插入的值正好为最大值，直接插入链表尾部
                lastSorted=lastSorted.next;
            }else {
                //从头开始寻找插入位置
                ListNode previous=dummyHead;
                while (previous.next.val<=current.val){
                    previous=previous.next;
                }
                //将新节点插入链表
                lastSorted.next=current.next;
                current.next=previous.next;
                previous.next=current;
            }
            //更新节点
            current=lastSorted.next;
        }
        return dummyHead.next;
    }
}
