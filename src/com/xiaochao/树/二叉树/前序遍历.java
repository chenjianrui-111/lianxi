package com.xiaochao.树.二叉树;

import com.cjr.shu.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 前序遍历 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        preorder(list, root);
        return list;
    }

    public void preorder(List list, TreeNode node){
        if (node == null) return;
        list.add(node.val);
        preorder(list, node.left);
        preorder(list, node.right);
    }
    /**
     * 迭代版
     * 和递归的思想是一样的，但需要手动在堆上维护一个栈来模拟程序栈。
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();

        //手动模拟一个栈，用于存储每个子树的根节点
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //用于遍历节点
        TreeNode node = root;
        while (node != null || !stack.empty()){
            //当前不是叶子节点（此处为，不是最左边的节点）
            while (node != null){
                list.add(node.val);
                //将当前节点入栈
                stack.push(node);
                //处理当前节点的左子树
                node = node.left;
            }
            //root这颗子树的根节点、左子节点都处理完了，处理右子节点。栈顶为最近添加的根节点
            node = stack.pop().right;
        }
        return list;
    }
}
class Solution3 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        //必须
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        //初始将根节点入栈
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }
}
//它们的时间复杂度都是O(n)，空间复杂度平均是O(logn)，最坏情况下树是链表，空间复杂度O(n)
/**
 *Morris遍历
 * 一种巧妙的方法，在线性时间复杂度下，使用常数级额外空间，实现二叉树的各种遍历。
 */
class Solution4{
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        TreeNode p1 = root, p2 = null;

        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    res.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                }
            } else {
                res.add(p1.val);
            }
            p1 = p1.right;
        }
        return res;
    }
}
