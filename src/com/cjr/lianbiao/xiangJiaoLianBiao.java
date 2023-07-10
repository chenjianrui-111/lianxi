package com.cjr.lianbiao;

public class xiangJiaoLianBiao {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA ==null ||headB==null){
            return  null;
        }
        ListNode Pa=headA,Pb=headB;
        while (Pa !=Pb){
            Pa=Pa==null?headB:Pa.next;
            Pb=Pb==null?headA:Pb.next;
        }
        return Pa;
    }
}
