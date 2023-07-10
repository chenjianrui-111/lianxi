package com.leixing.树的搜索;

/**
 *给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
 * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
 */
public class 二叉搜索树中的搜索 {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        return root.val < val ?searchBST(root.right ,val) : searchBST(root.left, val);
    }
}
/**
 *时间复杂度：O(n)
 * 空间复杂度：忽略递归带来的额外空间开销，复杂度为 O(1)
 */
