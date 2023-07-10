package com.daimasuixianglu.erchashu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 中序遍历顺序: 左-中-右 入栈顺序： 左-右
public class 中序遍历栈 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack=new Stack<>();
       TreeNode cur=root;
       while (cur!=null || !stack.isEmpty()){
           if (cur!=null){
               stack.push(cur);
               cur=cur.left;
           }else {
               cur=stack.pop();
               result.add(cur.val);
               cur=cur.right;
           }
       }
       return result;
    }
}
