package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;

/**
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 *
 * 创建一个根节点，其值为 nums 中的最大值。
 * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
 * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
 * 返回 nums 构建的 最大二叉树 。
 */
public class 最大二叉树 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums,0,nums.length-1);
    }
    TreeNode build(int[] nums,int l,int r){
        //base case
        if (l > r){
            return null;
        }
        int maxVal = Integer.MIN_VALUE;
        int index = -1;
        for (int i = l; i <= r ; i++) {
            if (nums[i] > maxVal){
                index = i;
                maxVal = nums[i];
            }
        }
        //构造出根节点
        TreeNode root = new TreeNode(maxVal);
        root.left = build(nums,l,index-1);
        root.right = build(nums,index+1,r);
        return root;
    }
}
