package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;

/**
 * 给你一棵二叉树，它的根为 root 。请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
 */
//在二叉树中切出一个小二叉树（子树），计算这个子树节点之和与剩下的节点之和的乘积。
//想求最大乘积，那就穷举，把所有可能的切法都穷举一遍，计算乘积。
//任何子树的节点之和都可以在后序位置获得，而剩下的其他节点之和就是整棵二叉树的节点之和减去子树节点之和。
//所以我们写一个 getSum 函数，先执行一遍计算整棵树的节点之和，然后再调用一次利用它的后序位置计算乘积。

public class 分裂二叉树的最大乘积 {
    public int maxProduct(TreeNode root) {
        // 先利用求和函数得到整棵树的节点之和
        treeSum = getSum(root);
        // 再次调用，利用后序位置计算子树之积
        getSum(root);
        return (int) (res % (1e9 + 7));
    }
    long res =0;
    int treeSum = 0;

    int getSum(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftSum = getSum(root.left);
        int rightSum = getSum(root.right);
        int rootSum = leftSum + rightSum + root.val;
        //后序位置计算乘积
        res = Math.max(res,(long) rootSum * (treeSum - rootSum));
        return rootSum;
    }
}
