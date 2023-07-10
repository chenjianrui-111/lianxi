package com.leixing.递归迭代.DFS;

import sun.reflect.generics.tree.Tree;

/**
 *给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）
 * 提示:
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 *
 * 树的遍历 + DFS
 * 一个朴素的做法是搜索以每个节点为根的（往下的）所有路径，并对路径总和为 targetSum 的路径进行累加统计。
 * 使用 dfs1 来搜索所有节点，复杂度为 O(n)；在 dfs1 中对于每个当前节点，使用 dfs2 搜索以其为根的所有（往下的）路径，同时累加路径总和为 targetSum的所有路径，复杂度为 O(n)。
 * 整体复杂度为 O(n^2)数据范围为 10^3可以过。
 */
public class 路径总和三 {
    int ans,t;
    public int pathSum(TreeNode root, int targetSum) {
        t=targetSum;
        dfs1(root);
        return ans;
    }
    void dfs1(TreeNode root){
        if (root == null) {
            return;
        }
        dfs2(root,root.val);
        dfs1(root.left);
        dfs1(root.right);
    }
    void dfs2(TreeNode root,int val){
        if (val ==t){
            ans++;
        }
        if (root.left !=null){
            dfs2(root.left,val + root.left.val);
        }
        if (root.right !=null){
            dfs2(root.right,val + root.right.val);
        }
    }
}

/**
 * 时间复杂度：O(n^2)
 * 空间复杂度：忽略递归带来的额外空间开销，复杂度为 O(1)
 */
 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
}
