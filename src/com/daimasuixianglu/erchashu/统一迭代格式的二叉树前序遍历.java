package com.daimasuixianglu.erchashu;

import com.sun.deploy.panel.TreeEditors;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class 统一迭代格式的二叉树前序遍历 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res=new LinkedList<>();
        Stack<TreeNode> stack=new Stack<>();
        if (root !=null) stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node=stack.peek();
            if (node!=null){
                stack.pop();// 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right!=null) stack.push(node.right);  // 添加右节点（空节点不入栈）
                if (node.left!=null) stack.push(node.left);    // 添加左节点（空节点不入栈）
                stack.push(node);                          // 添加中节点
                stack.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
            }else {
                // 只有遇到空节点的时候，才将下一个节点放进结果集
                stack.pop();// 将空节点弹出
                node=stack.peek();// 重新取出栈中元素
                stack.pop();
                res.add(node.val);// 加入到结果集
            }
        }
        return res;
    }
}
