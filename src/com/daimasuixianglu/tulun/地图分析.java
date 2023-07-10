package com.daimasuixianglu.tulun;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 你现在手里有一份大小为 N*N  的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。
 * 其中 0 代表海洋，1 代表陆地，请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的。
 * 我们这里说的距离是「曼哈顿距离」：(x0,y0) 和(x1,y1)  这两个单元格之间的距离是 |x0-x1|+|y0-y1|。
 * 如果网格上只有陆地或者海洋，请返回 -1。
 * 输入：[[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
 */
public class 地图分析 {
    public int maxDistance(int[][] grid) {
        int n=grid.length;
        Deque<int[]> deque=new ArrayDeque<>();
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <n ; i++) {
            for (int j=0;j<n;j++){
                if (grid[i][j] == 1){
                    deque.add(new int[]{i,j});
                    map.put(i*n+j,0);
                }
            }
        }
        int ans=-1;
        int[][] dirs=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        while (!deque.isEmpty()){
            int[] poll =deque.poll();
            int dx=poll[0],dy=poll[1];
            int step=map.get(dx*n+dy);
            for (int[]di:dirs) {
                int nx=dx+di[0],ny=dy+di[1];
                if (nx<0 ||nx>=n ||ny<0 ||ny>=n) continue;
                if (grid[nx][ny] !=0) continue;
                grid[nx][ny]=step+1;
                deque.add(new int[]{nx,ny});
                map.put(nx*n+ny,step+1);
                ans=Math.max(ans,step+1);
            }
        }
        return ans;
    }
}
//时间复杂度：O(N2)
//空间复杂度：O(N2)
