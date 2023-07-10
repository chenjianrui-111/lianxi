package com.daimasuixianglu.tulun;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 * 两个网格块属于同一 连通分量 需满足下述全部条件：
 * 两个网格块颜色相同
 * 在上、下、左、右任意一个方向上相邻
 * 连通分量的边界 是指连通分量中满足下述条件之一的所有网格块：
 * 在上、下、左、右任意一个方向上与不属于同一连通分量的网格块相邻
 * 在网格的边界上（第一行/列或最后一行/列）
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
 * 示例 1：
 * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * 输出：[[3,3],[3,2]]
 */
public class 边界着色 {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m=grid.length,n=grid[0].length;
        int[][] ans=new int[m][n];
        int[][] dirs=new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        Deque<int[]> d=new ArrayDeque<>();
        d.addLast(new int[]{row,col});
        while (!d.isEmpty()){
            int[] poll=d.pollFirst();
            int x=poll[0],y=poll[1],cnt=0;
            for (int []di:dirs){
                int nx=x+di[0],ny=y+di[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (grid[x][y] !=grid[nx][ny]) continue;
                else cnt++;
                if (ans[nx][ny] !=0) continue;
                d.addLast(new int[]{nx,ny});
            }
            ans[x][y]=cnt==4?grid[x][y] :color;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ans[i][j] == 0) ans[i][j] = grid[i][j];
            }
        }
        return ans;
    }
}
