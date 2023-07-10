package com.xiaochao.dfs;

import com.cjr.shu.TreeNode;

/**
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * 叶节点 是指没有子节点的节点。
 *      1
 *     / \
 *    2   3
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 */
public class 求根节点到叶节点数字之和 {
    StringBuilder path = new StringBuilder();
    int res = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfs(root);
        return res;
    }
    void dfs(TreeNode root){
        if (root == null) {
            return ;
        }
        path.append(root.val);
        if (root.left == null && root.right == null){
            //到达叶子节点，累加路径和
            res += Integer.parseInt(path.toString());
        }
        //二叉树递归框架，遍历左右子树
        dfs(root.left);
        dfs(root.right);
        //后序遍历，撤销节点值
        path.deleteCharAt(path.length() - 1);
    }
}
