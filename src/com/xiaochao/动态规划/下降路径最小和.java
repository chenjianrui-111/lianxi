package com.xiaochao.动态规划;

import java.util.Arrays;

/**
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列
 * （即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col)
 * 或者 (row + 1, col + 1)
 * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 */
public class 下降路径最小和 {

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res,dp(matrix,n - 1,j));
        }
        return res;
    }
    int dp(int[][] matrix,int i,int j){
        //非法索引检查
        if (i < 0 || j < 0 || j >= matrix[0].length || i >= matrix.length ){
            return  99999;
        }
        //base case
        if (i == 0){
            return matrix[i][j];
        }
        //状态转移
        return matrix[i][j] + min(
                dp(matrix,i - 1,j),
                dp(matrix,i - 1,j - 1),
                dp(matrix,i - 1,j + 1)
        );
    }

    int min(int a,int b,int c){
        return Math.min(a, Math.min(b,c));
    }

}
class Solution{

    int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int res = Integer.MAX_VALUE;
        // 备忘录⾥的值初始化为 66666
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], 66666);
        }
        // 终点可能在 matrix[n-1] 的任意⼀列
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dp(matrix, n - 1, j));
        }
        return res;
    }
    // 备忘录
    int[][] memo;
    int dp(int[][] matrix, int i, int j) {
        // 1、索引合法性检查
        if (i < 0 || j < 0 ||
                i >= matrix.length ||
                j >= matrix[0].length) {

            return 99999;
        }
        // 2、base case
        if (i == 0) {
            return matrix[0][j];
        }
        // 3、查找备忘录，防⽌重复计算
        if (memo[i][j] != 66666) {
            return memo[i][j];
        }
        // 进⾏状态转移
        memo[i][j] = matrix[i][j] + min(
                dp(matrix, i - 1, j),
                dp(matrix, i - 1, j - 1),
                dp(matrix, i - 1, j + 1)
        );
        return memo[i][j];
    }
    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
