package com.xiaochao.图;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 */
public class 岛屿数量 {
    public int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length,n = grid[0].length;
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if (grid[i][j] == '1'){
                    // 每发现一个岛屿，岛屿数量加一
                    res ++;
                    // 然后使用 DFS 将岛屿淹了
                    dfs(grid,i,j);
                }
            }
        }
        return res;
    }
    // 从 (i, j) 开始，将与之相邻的陆地都变成海水
    void dfs(char[][] grid,int i,int j){
        int m =grid.length,n=grid[0].length;
        if (i < 0 || j< 0 || i >= m || j >= n){
            // 超出索引边界
            return;
        }
        if (grid[i][j] == '0'){
            // 已经是海水了
            return;
        }
        // 将 (i, j) 变成海水
        grid[i][j] = '0';
        // 淹没上下左右的陆地
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
