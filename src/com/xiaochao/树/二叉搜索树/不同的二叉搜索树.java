package com.xiaochao.树.二叉搜索树;

//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。

public class 不同的二叉搜索树 {
//    public int numTrees(int n) {
//        // 计算闭区间 [1, n] 组成的 BST 个数
//        return count(1, n);
//    }
//
//    int count(int lo,int hi){
//        //base case
//        //显然当 lo > hi 闭区间 [lo, hi] 肯定是个空区间，也就对应着空节点 null
//        if (lo > hi) return 1;
//
//        int res = 0;
//        for (int i = lo; i <= hi ; i++) {
//            // i 的值作为根节点 root
//            int left =count(lo, i -1);
//            int right = count(i+1,hi);
//            // 左右子树的组合数乘积是 BST 的总数
//            res +=left * right;
//        }
//        return res;
//    }
// 备忘录
int[][] memo;

    int numTrees(int n) {
        // 备忘录的值初始化为 0
        memo = new int[n + 1][n + 1];
        return count(1, n);
    }

    int count(int lo, int hi) {
        if (lo > hi) return 1;
        // 查备忘录
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int left = count(lo, mid - 1);
            int right = count(mid + 1, hi);
            res += left * right;
        }
        // 将结果存入备忘录
        memo[lo][hi] = res;

        return res;
    }
}
