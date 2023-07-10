package com.daimasuixianglu.erchashu;


import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的最小深度 {
    public int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int depth=0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            depth++;
            for (int i = 0; i <size ; i++) {
                TreeNode node=queue.poll();
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
                // 当左右孩子都为空的时候，说明是最低点的一层了，退出
                if (node.left==null && node.right==null){
                    return depth;
                }
            }
        }
        return depth;
    }
}
