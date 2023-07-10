package com.daimasuixianglu.erchashu;

import java.util.Stack;

/**
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 */
//递归
public class 二叉搜索树的最小绝对差 {
    TreeNode pre;
    int result=Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if (root==null) return 0;
        traversal(root);
        return result;
    }
    public void traversal(TreeNode root){
        if (root == null) return;
        //左
        traversal(root.left);
        //中
        if (pre!=null){
            result=Math.min(result,root.val-pre.val);
        }
        pre=root;
        //右
        traversal(root.right);
    }
}
class  Solution08{
    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        if (root == null) return 0;
        TreeNode pre=null;
        TreeNode cur=root;
        int result=Integer.MAX_VALUE;
        while (cur!=null ||!stack.isEmpty()){
            if (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }else {
                cur=stack.pop();
                if (pre!=null){
                    result=Math.min(result,cur.val-pre.val);
                }
                pre=cur;
                cur=cur.right;
            }
        }
        return result;
    }
}
