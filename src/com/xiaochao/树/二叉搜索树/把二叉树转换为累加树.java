package com.xiaochao.树.二叉搜索树;

import com.cjr.shu.TreeNode;

/**
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * 提醒一下，二叉搜索树满足下列约束条件：
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 */
public class 把二叉树转换为累加树 {
    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }
    //记录累加和
    int sum = 0;
    void traverse(TreeNode root){
        if (root == null){
            return;
        }
        traverse(root.right);
        //维护累加和
        sum += root.val;
        //将BST转化为累加树
        root.val = sum;
        traverse(root.left);
    }
}
