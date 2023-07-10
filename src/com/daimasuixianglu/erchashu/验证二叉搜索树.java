package com.daimasuixianglu.erchashu;

import java.util.Stack;

/**给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 有效 二叉搜索树定义如下：
 节点的左子树只包含 小于 当前节点的数。
 节点的右子树只包含 大于 当前节点的数。
 所有左子树和右子树自身必须也是二叉搜索树。
 * 输入：root = [2,1,3]
 * 输出：true
 */
//迭代
public class 验证二叉搜索树 {
    public boolean isValidBST(TreeNode root) {
        if (root==null) return true;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode pre=null;
        while (!stack.isEmpty() || root!=null) {
            while (root!=null){
                stack.push(root);
                root=root.left;//左
            }
            //中
            TreeNode node=stack.pop();
            if (pre!=null && node.val<=pre.val){
                return false;
            }
            pre=node;
            root=node.right;
        }
        return true;
    }
}
//递归
class Solution07{
    TreeNode max;
    public boolean isValidBST(TreeNode root) {
        if (root==null) return true;
        //左
        boolean left=isValidBST(root.left);
        if (!left){
            return false;
        }
        //中
        if (max!=null && root.val<=max.val){
            return false;
        }
        max=root;
        //右
        boolean right=isValidBST(root.right);
        if (!right){
            return false;
        }
        return true;
    }
}
