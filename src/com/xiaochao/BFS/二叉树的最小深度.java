package com.xiaochao.BFS;

import com.cjr.shu.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的最小深度 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int depth  = 1;
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size ; i++) {
                TreeNode cur = q.poll();
                if (cur.left == null && cur.right == null)
                    return depth;
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            depth++;
        }
        return depth;
    }
}
