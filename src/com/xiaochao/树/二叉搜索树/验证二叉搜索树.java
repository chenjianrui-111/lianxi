package com.xiaochao.树.二叉搜索树;

import com.cjr.shu.TreeNode;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class 验证二叉搜索树 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }
    boolean isValidBST(TreeNode root,TreeNode min,TreeNode max){
        // base case
        if (root == null) return true;
        // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        // 限定左⼦树的最⼤值是 root.val，右⼦树的最⼩值是 root.val
        return isValidBST(root.left, min, root)
                && isValidBST(root.right, root, max);
    }
}
