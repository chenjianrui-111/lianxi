package com.leixing.递归迭代.BFS;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 迭代
 * 迭代其实就是使用「栈」来模拟递归过程，也属于树的遍历中的常见实现形式。
 */
public class 二叉搜索树的范围和2 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        int ans=0;
        Deque<TreeNode> d=new ArrayDeque<>();
        while (root != null || !d.isEmpty()){
            while (root !=null){
                d.addLast(root);
                root=root.left;
            }
            root=d.pollFirst();
            if (low <= root.val && root.val <= high){
                ans+=root.val;
            }
            root=root.right;
        }
        return ans;
    }
}
//时间复杂度：O(n)
//空间复杂度：O(n)
