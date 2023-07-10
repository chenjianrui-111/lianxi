package com.daimasuixianglu.erchashu;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        postOrder(root,res);
        return res;
    }
    void  postOrder(TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }
        postOrder(root.left,res);
        postOrder(root.right,res);
        res.add(root.val);
    }
}
