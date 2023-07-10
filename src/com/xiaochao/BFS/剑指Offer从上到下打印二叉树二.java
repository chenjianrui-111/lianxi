package com.xiaochao.BFS;

import com.cjr.shu.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class 剑指Offer从上到下打印二叉树二 {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q =new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            int sz = q.size();
            //记录这一层的节点
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i <sz ; i++) {
                TreeNode cur = q.poll();
                level.add(cur.val);
                if (cur.left != null){
                    q.offer(cur.left);
                }
                if (cur.right != null){
                    q.offer(cur.right);
                }
            }
            res.add(level);
        }
        return res;
    }
}
