package com.xiaochao.树.二叉搜索树;

import com.cjr.shu.TreeNode;

import java.util.LinkedList;
import java.util.List;

//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
public class 不同的二叉搜索树二 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return build(1,n);
    }
    List<TreeNode> build(int lo, int hi){
        LinkedList<TreeNode> res =new LinkedList<>();
        //base case
        if (lo > hi){
            res.add(null);
            return res;
        }
        //1.穷举root的所有可能
        for (int i = lo; i <= hi ; i++) {
            // 2、递归构造出左右子树的所有有效 BST。
            List<TreeNode> leftTree =build(lo,i - 1);
            List<TreeNode> rightTree = build(i + 1,hi);
            //3.给root节点穷举所有的左右子树的组合
            for (TreeNode left :leftTree){
                for (TreeNode right : rightTree){
                    TreeNode root =new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
