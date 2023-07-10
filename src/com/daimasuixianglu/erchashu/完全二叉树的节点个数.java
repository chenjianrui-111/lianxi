package com.daimasuixianglu.erchashu;

/**
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 */
public class 完全二叉树的节点个数 {
    // 通用递归解法
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
class Solution1{
    public int countNodes(TreeNode root) {
        if (root==null){
            return 0;
        }
        int leftDepth=countDepth(root.left);
        int rightDepth=countDepth(root.right);
        if (leftDepth==rightDepth){// 左子树是满二叉树
            // 2^leftDepth其实是 （2^leftDepth - 1） + 1 ，左子树 + 根结点
            return (1 << leftDepth)+countNodes(root.right);
        }else {// 右子树是满二叉树
            return (1 << rightDepth)+countNodes(root.left);
        }
    }
    private int countDepth(TreeNode root){
        int depth=0;
        while (root !=null){
            root=root.left;
            depth++;
        }
        return depth;
    }
}
