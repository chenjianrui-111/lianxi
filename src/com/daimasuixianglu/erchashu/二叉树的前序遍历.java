package com.daimasuixianglu.erchashu;

import java.util.ArrayList;

public class 二叉树的前序遍历 {
    ArrayList<Integer> preOrderReverse(TreeNode root) {
        ArrayList<Integer> result=new ArrayList<>();
        preOrder(root,result);
        return result;
    }
    void  preOrder(TreeNode root,ArrayList<Integer> result){
        if (root == null){
            return;
        }
        result.add(root.val);
        preOrder(root.left,result);
        preOrder(root.right,result);
    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){};
    TreeNode(int val){this.val=val;}
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val=val;
        this.left=left;
        this.right=right;
    }
}
