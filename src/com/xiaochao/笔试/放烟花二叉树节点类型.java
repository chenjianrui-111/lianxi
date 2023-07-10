package com.xiaochao.笔试;

import java.util.HashSet;
import java.util.Set;

/**
 * 途虎十周年庆典开幕仪式上，空中绽放了一颗二叉树形的巨型烟花，给定一颗二叉树root代表烟花，
 * 节点值表示巨型烟花这一位置的颜色值，请你帮小乐虎计算巨型烟花一共有多少种不同的颜色。
 * 输入
 * {1，3，2，5，#，2}
 * 输出
 * 4
 */
public class 放烟花二叉树节点类型 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 烟花颜色数
     * @param root TreeNode类 烟花二叉树
     * @return int整型
     */
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val){
            this.val = val;
        }
    }
    Set<Integer> set =new HashSet<>();
    public int numColor(TreeNode root){
        dfs(root);
        return set.size();
    }
    private void dfs(TreeNode root){
        if (root == null){
            return;
        }
        set.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}
