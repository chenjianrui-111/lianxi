package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(list, root);
        return list;
    }

    public void inorder(List list, TreeNode node){
        if (node == null) {
            return;
        }
        inorder(list, node.left);
        list.add(node.val);
        inorder(list, node.right);
    }

    /**
     * 迭代版
     * 中序遍历的迭代版比较难，这是因为，要访问的元素（根节点）和要处理的元素（左子节点）是不一致的，需要借助指针。
     * 中序遍历，则先将所有左节点存入栈中，然后弹栈一次，就得到了最左侧的叶子节点。记录这个值，向右节点靠拢。
     * 如果这个节点不存在右节点，则在node = node.right这里，node=null，下次循环node会直接取弹栈的值，即为上个节点的父节点
     * 如果这个节点存在右节点，那它的左节点如果存在，肯定已经遍历过了。它的右节点会被入栈，然后在下次取值。
     */
    public List<Integer> inorderTraversal2(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
    }
}
