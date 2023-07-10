package com.daimasuixianglu.erchashu;

/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
 * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 如果存在，返回 true ；否则，返回 false 。
 * 叶子节点 是指没有子节点的节点。
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 解释：等于目标和的根节点到叶节点路径如上图所示。
 *
 */
public class 路径总和 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return  traversal(root,targetSum-root.val);
    }
    public boolean traversal(TreeNode root,int count){
        if (root.left==null &&root.right==null && count ==0 ) return true;//遇到叶子节点，并且计数为0
        if (root.left==null && root.right==null) return false;

        if (root.left!=null){
            //左
            count-=root.left.val;
            if (traversal(root.left,count)) return true;
            count+=root.left.val;
        }
        if (root.right!=null){
            //右
            count-=root.right.val;
            if (traversal(root.right,count)) return true;
            count+=root.right.val;
        }
        return false;
    }
}
