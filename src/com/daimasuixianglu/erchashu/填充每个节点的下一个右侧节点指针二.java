package com.daimasuixianglu.erchashu;

import java.util.LinkedList;
import java.util.Queue;

/**
 *填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，
 * 则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 */
public class 填充每个节点的下一个右侧节点指针二 {
    public Node connect(Node root) {
        Queue<Node> queue=new LinkedList<>();
        if(root == null){
            return root;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            //前一个节点
            Node preNode=null;
            for (int i = 0; i <size ; i++) {
                //出队
                Node node = queue.poll();
                //如果pre为空就表示node节点是这一行的第一个，
                //没有前一个节点指向他，否则就让前一个节点指向他
                if (preNode != null) {
                    preNode.next = node;
                }
                //然后再让当前节点成为前一个节点
                preNode = node;
                //左右子节点如果不为空就入队
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return root;
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public Node[] children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
