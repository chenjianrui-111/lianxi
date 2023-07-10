package com.daimasuixianglu.lianbiao;

import com.daimasuixianglu.tulun.Node;

public class Code01_SmallerEquallBiger {

    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value=data;
        }
    }
    public static Node listPartition2(Node head,int pivot){
        Node sH=null;
        Node sT=null;
        Node eH=null;
        Node eT=null;
        Node bH=null;
        Node bT=null;
        Node next=null;
        while (head !=null){
            next=head.next;
            head.next=null;
            if (head.value < pivot){
                if (sH == null){
                    sH=head;
                    sT=head;
                }else {
                    sT.next=head;
                    sT=head;
                }
            }else if (head.value == pivot){
                if (eH == null){
                    eH=head;
                    eT=head;
                }else {
                    eT.next=head;
                    eT=head;
                }
            }else {
                bT.next=head;
                bT=next;
            }
        }
        head=next;
        if (sT !=null) {//如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT;//下一步，谁去连大于区域的头，谁就变成eT
        }
        //上面的if，不管跑了没有，et
        if (eT != null){//如果小于区域和等于区域，不是都没有
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }
        public static void printLinkedList(Node node){
            System.out.print("Linked List: ");
            while (node != null){
                System.out.print(node.value +" ");
                node=node.next;
            }
            System.out.println();
        }
}
