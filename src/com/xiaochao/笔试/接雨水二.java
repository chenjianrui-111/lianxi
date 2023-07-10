package com.xiaochao.笔试;

import java.util.PriorityQueue;

/**
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4
 */
public class 接雨水二 {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->a[2]-b[2]);
        boolean[][] vis = new boolean[m][n];
        for (int i = 0; i < n; i++) {
            q.add(new int[]{0, i, heightMap[0][i]});
            q.add(new int[]{m - 1, i, heightMap[m - 1][i]});
            vis[0][i] = vis[m - 1][i] = true;
        }
        for (int i = 1; i < m - 1; i++) {
            q.add(new int[]{i, 0, heightMap[i][0]});
            q.add(new int[]{i, n - 1, heightMap[i][n - 1]});
            vis[i][0] = vis[i][n - 1] = true;
        }
        int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        int ans = 0;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0], y = poll[1], h = poll[2];
            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (vis[nx][ny]) continue;
                if (h > heightMap[nx][ny]) ans += h - heightMap[nx][ny];
                q.add(new int[]{nx, ny, Math.max(heightMap[nx][ny], h)});
                vis[nx][ny] = true;
            }
        }
        return ans;
    }
}
//时间复杂度：所有的点均进出一次优先队列（堆），复杂度为 O((m∗n)log⁡(m∗n))
//空间复杂度：O(m∗n)
