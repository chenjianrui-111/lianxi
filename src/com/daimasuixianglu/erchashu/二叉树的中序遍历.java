package com.daimasuixianglu.erchashu;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的中序遍历 {
    List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        inOrder(root,res);
        return res;
    }
    void inOrder(TreeNode root,List<Integer> res){
        if (root == null){
            return;
        }
        inOrder(root.left,res);
        res.add(root.val);
        inOrder(root.right,res);
    }
}
