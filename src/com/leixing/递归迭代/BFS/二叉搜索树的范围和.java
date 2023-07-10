package com.leixing.递归迭代.BFS;

/**
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * 提示：
 *
 * 树中节点数目在范围 [1, 2 * 10^4] 内
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * 所有 Node.val 互不相同
 *
 * 递归
 */
public class 二叉搜索树的范围和 {
    int low,high;
    int ans;
    public int rangeSumBST(TreeNode root, int _low, int _high) {
       low=_low;high=_high;
       dfs(root);
       return ans;
    }
    void dfs(TreeNode root){
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (low <= root.val && root.val < high){
            ans += root.val;
        }
        dfs(root.right);
    }
}
//时间复杂度：O(n)
//空间复杂度：O(n)
