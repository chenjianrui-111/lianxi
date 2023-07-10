package com.daimasuixianglu.erchashu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 前序遍历顺序：中-左-右，入栈顺序：中-右-左
 */
public class 前序遍历栈 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if (root==null){
            return list;
        }
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node=stack.pop();
            list.add(node.val);
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return list;
    }
}
