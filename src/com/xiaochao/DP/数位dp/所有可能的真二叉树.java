package com.xiaochao.DP.数位dp;

import java.util.LinkedList;
import java.util.List;

public class 所有可能的真二叉树 {
     class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }
    // 备忘录，记录 n 个节点能够组合成的所有可能二叉树
    List<TreeNode>[] memo;

    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) {
            // 题目描述的满二叉树不可能是偶数个节点
            return new LinkedList<>();
        }
        memo = new LinkedList[n + 1];
        return helper(n);
    }

    // 定义：输入一个 n，生成节点树为 n 的所有可能的满二叉树
    public List<TreeNode> helper(int n) {
        List<TreeNode> res = new LinkedList<>();
        // base case
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        if (memo[n] != null) {
            // 避免冗余计算
            return memo[n];
        }

        // 递归生成所有符合条件的左右子树
        for (int i = 1; i < n; i += 2) {
            int j = n - i - 1;
            // 利用函数定义，生成左右子树
            List<TreeNode> leftSubTrees = helper(i);
            List<TreeNode> rightSubTrees = helper(j);
            // 左右子树的不同排列也能构成不同的二叉树
            for (TreeNode left : leftSubTrees) {
                for (TreeNode right : rightSubTrees) {
                    // 生成根节点
                    TreeNode root = new TreeNode(0);
                    // 组装出一种可能的二叉树形状
                    root.left = left;
                    root.right = right;
                    // 加入结果列表
                    res.add(root);
                }
            }
        }
        // 存入备忘录
        memo[n] = res;
        return res;
    }
}
