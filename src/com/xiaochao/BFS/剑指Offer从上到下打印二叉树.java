package com.xiaochao.BFS;

import com.cjr.shu.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 * [3,9,20,15,7]
 */
public class 剑指Offer从上到下打印二叉树 {
    public int[] levelOrder(TreeNode root) {
        if (root == null){
            return new int[]{};
        }
        ArrayList<Integer> res =new ArrayList<>();
        Queue<TreeNode> q =new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            int size =q.size();
            for (int i = 0; i <size ; i++) {
                TreeNode cur =q.poll();
                res.add(cur.val);
                if (cur.left !=null){
                    q.offer(cur.left);
                }
                if (cur.right != null){
                    q.offer(cur.right);
                }
            }
        }
        int []ans =new int[res.size()];
        for (int i = 0; i <res.size() ; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
