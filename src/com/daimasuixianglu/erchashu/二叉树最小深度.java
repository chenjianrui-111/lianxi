package com.daimasuixianglu.erchashu;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 递归法，相比求MaxDepth要复杂点
 * 因为最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
 */
public class 二叉树最小深度 {
    public int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int leftDepth=minDepth(root.left);
        int rightDepth=minDepth(root.right);
        if (root.left==null){
            return rightDepth+1;
        }
        if (root.right==null){
            return leftDepth+1;
        }
        return Math.min(leftDepth,rightDepth)+1;
    }
}
class solution{
    public int minDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        int depth=0;
        Deque<TreeNode> deque=new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()){
            int size=deque.size();
            depth++;
            for (int i = 0; i <size ; i++) {
                TreeNode node=deque.poll();
                if (node.left==null && node.right==null){
                    return depth;
                }
                if (node.left!=null){
                    deque.offer(node.left);
                }
                if (node.right!=null){
                    deque.offer(node.right);
                }
            }
        }
        return depth;
    }
}
