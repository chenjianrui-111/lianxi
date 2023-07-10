package com.cjr.shu;

import java.util.ArrayList;
import java.util.List;

public class inorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        inoder(root,res);
        return res;
    }
    public void inoder(TreeNode root,List<Integer> res){
        //递归终止条件
        if (root ==null)
            return;
        //递归遍历左子节点
        inoder(root.left,res);
        //访问当前节点
        res.add(root.val);
        //递归遍历右子节点
        inoder(root.right,res);
    }
}
