package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;

public class 翻转二叉树 {

    TreeNode invertTree(TreeNode root){
        //遍历二叉树，交换每个节点的子节点
        traverse(root);
        return root;
    }
    //二叉树遍历
    void traverse(TreeNode root){
        if (root == null){
            return;
        }
        //前序位置
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        //遍历框架，去遍历左右子树的节点
        traverse(root.left);
        traverse(root.right);
    }

    /**** 分解思想 ****/
    TreeNode invertTree2(TreeNode root){
        if (root == null){
            return null;
        }
        //利用函数定义，先反转左右子树
        TreeNode left =invertTree2(root.left);
        TreeNode right= invertTree2(root.right);

        //然后交换左右子节点
        root.left = right;
        root.right = left;

        //和定义逻辑自恰:以root为根的这棵二叉树已经被翻转，返回root
        return root;
    }
}

