package com.cjr.shu;

public class tianChongMeiGeJieDianDeXiaYiGeYouCeJieDianZhiZhen {
    public Node connect(Node root) {
        if (root ==null)
            return root;
        //cur我们可以把它看作是每一层的链表
        Node cur=root;
        while (cur!=null){
            Node dummy=new Node(0);
            Node pre=dummy;

            //然后开始遍历当前层的链表，因为是完美二叉树，所以如果有左子节点就一定有右子节点
            while (cur!=null &&cur.left!=null){
                //让pre节点的next指向当前节点的左子节点，也就是把它穿起来
                pre.next=cur.left;
                //然后再更新pre
                pre=pre.next;
                //让pre节点的next指向当前节点的右子节点
                pre.next=cur.right;
                pre=pre.next;
            }
            //把下一层串联成一个链表之后，让他赋值给cur，
            //后续继续循环，直到cur为空为止
            cur = dummy.next;
        }
        return  root;
    }
}
