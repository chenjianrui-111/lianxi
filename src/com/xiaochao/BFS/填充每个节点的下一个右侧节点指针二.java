package com.xiaochao.BFS;

import com.cjr.shu.Node;

import java.util.LinkedList;
import java.util.Queue;

//但这题和 116. 填充每个节点的下一个右侧节点指针 还不一样，输入的不是完全二叉树，所以不好直接用递归。
public class 填充每个节点的下一个右侧节点指针二 {

    public Node connect(Node root) {
        if (root == null)
            return null;
        //二叉树层序遍历框架
        Queue<Node> q =new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            int sz =q.size();
            //遍历一层需要前指针
            Node pre = null;
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                //链接当前层所有节点的 next 指针
                if (pre != null){
                    pre.next = cur;
                }
                pre = cur;
                //将下一层节点装入队列
                if (cur.left != null){
                    q.offer(cur.left);
                }
                if (cur.right != null){
                    q.offer(cur.right);
                }
            }
        }
        return root;
    }
}
