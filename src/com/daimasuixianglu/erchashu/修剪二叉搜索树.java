package com.daimasuixianglu.erchashu;

/**
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，
 * 使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 */
public class 修剪二叉搜索树 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root==null) return null;
        if (root.val<low){
            return trimBST(root.right,low,high);
        }
        if (root.val>high){
            return trimBST(root.left,low,high);
        }
        //root在[low,high]范围内
        root.left=trimBST(root.left,low,high);
        root.right=trimBST(root.right,low,high);
        return root;
    }
}

/**
 * 1.确定终止条件
 * 修剪的操作并不是在终止条件上进行的，所以就是遇到空节点返回就可以了。
 * 2.确定单层递归的逻辑
 * 如果root（当前节点）的元素小于low的数值，那么应该递归右子树，并返回右子树符合条件的头结点。
 * 如果root(当前节点)的元素大于high的，那么应该递归左子树，并返回左子树符合条件的头结点。
 * 3.接下来要将下一层处理完左子树的结果赋给root->left，处理完右子树的结果赋给root->right。
 */
class Solution10 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root==null) return null;
        if (root.val<low){
            TreeNode right=trimBST(root.right,low,high);// 寻找符合区间[low, high]的节点
            return right;
        }
        if (root.val>high){
            TreeNode left=trimBST(root.left,low,high);// 寻找符合区间[low, high]的节点
            return left;
        }
        root.left=trimBST(root.left,low,high);// root->left接入符合条件的左孩子
        root.right=trimBST(root.right,low,high); // root->right接入符合条件的右孩子
        return root;
    }
}
