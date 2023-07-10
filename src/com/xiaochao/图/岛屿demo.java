package com.xiaochao.图;

import com.cjr.shu.TreeNode;

public class 岛屿demo {
    // 二叉树遍历框架
    void traverse(TreeNode root) {
        traverse(root.left);
        traverse(root.right);
    }

    // 二维矩阵遍历框架
    void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            // 超出索引边界
            return;
        }
        if (visited[i][j]) {
            // 已遍历过 (i, j)
            return;
        }
        // 进入节点 (i, j)
        visited[i][j] = true;
        dfs(grid, i - 1, j, visited); // 上
        dfs(grid, i + 1, j, visited); // 下
        dfs(grid, i, j - 1, visited); // 左
        dfs(grid, i, j + 1, visited); // 右

        // 方向数组，分别代表上、下、左、右
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

//        void dfs(int[][] grid, int i, int j, boolean[][] visited) {
//            int m = grid.length, n = grid[0].length;
//            if (i < 0 || j < 0 || i >= m || j >= n) {
//                // 超出索引边界
//                return;
//            }
//            if (visited[i][j]) {
//                // 已遍历过 (i, j)
//                return;
//            }
//
//            // 进入节点 (i, j)
//            visited[i][j] = true;
//            // 递归遍历上下左右的节点
//            for (int[] d : dirs) {
//                int next_i = i + d[0];
//                int next_j = j + d[1];
//                dfs(grid, next_i, next_j, visited);
//            }
//            // 离开节点 (i, j)
    }
}
