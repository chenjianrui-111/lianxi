package com.xiaochao.DP.数位dp;

/**
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 *输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为 [1, 2, 6, 9]。
 */
public class 矩阵中的最长递增路径 {
    int m, n;
    int[][] matrix, memo;
    public int longestIncreasingPath(int[][] _matrix) {
        /*
        方法2:记忆化搜索
        利用dfs将深度优先搜索过程中得到matrix[i][j]的最长路径长度存储到memo[i][j]中
        然后如果memo[i][j]没有计算才需要dfs,否则说明已经计算过了就不用进一步计算
        */
        matrix = _matrix;
        m = matrix.length;
        n = matrix[0].length;
        // 存储matrix的最长递增路经
        int res = 1;
        // 记忆载体
        memo = new int[m][n];
        // 遍历每个节点
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // 若没有搜索过才需要进行搜索
                if(memo[i][j] == 0) {
                    res = Math.max(res, dfs(i, j));
                }
                // 这里为什么没有比较memo[i][j]!=0的情况?
                // 因为后面matrix[nextI][nextJ]为起点的路径总比matrix[i][j]为起点的短
                // 满足matrix[nextI][nextJ]>matrix[i][j]才会进行dfs的
            }
        }
        return res;
    }
    /*
    返回以matrix[i][j]为起点的最长递增路径
    */
    private int dfs(int i, int j) {
        // 若之前搜索过了直接返回之前存储的最大长度
        if(memo[i][j] != 0) {
            return memo[i][j];
        }
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        // 以matrix[i][j]为起点的最长递增路径
        int maxLen = 1;
        for(int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            if(nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n
                    && matrix[nextI][nextJ] > matrix[i][j]) {
                // 选择4个方向的最大路径的最大值作为maxLen
                maxLen = Math.max(maxLen, dfs(nextI, nextJ) + 1);
            }
        }
        // 将以matrix[i][j]为起点的最长递增路径存储在memo[i][j]中
        // 注意:在递归过程中将matrix[nextI,nextJ]为起点的最长路径都存储在memo了
        memo[i][j] = maxLen;
        // 返回该最长路径长度
        return maxLen;
    }
}
