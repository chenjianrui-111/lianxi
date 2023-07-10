package com.xiaochao.笔试;

import java.util.ArrayList;
import java.util.List;

public class 求one的个数和曼哈顿距离 {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         * Return the graph count and max distance of graph edge
         * @param graph int整型二维数组
         * @return int整型一维数组
         */
        int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        int[][] arr;
        boolean[][] visit;
        int m, n;
        int num = 0, maxDis = 0;
        public int[] solve (int[][] graph) {
            // write code here
            arr = graph;
            m = arr.length;
            if(m == 0) return new int[2];
            n = arr[0].length;
            visit = new boolean[m][n];
            for(int i = 0; i < m; ++i){
                for(int j = 0; j < n; ++j){
                    if(!visit[i][j] && arr[i][j] == 1){
                        ++num;
                        dfs(i, j, new ArrayList<>());
                    }
                }
            }
            return new int[]{num, maxDis};
        }

        void dfs(int x, int y, List<int[]> list){
            if(x < 0 || x >= m || y < 0 || y >= n || arr[x][y] == 0 || visit[x][y]) return;
            visit[x][y] = true;
            // 求该点 与当前区域的距离
            for(int[] pre : list){
                int cur = Math.abs(pre[0] - x) +  Math.abs(pre[1] - y);
                maxDis = Math.max(maxDis, cur);
            }

            list.add(new int[]{x, y});
            for(int i = 0; i < dir.length; ++i){
                int nx = x + dir[i][0], ny = dir[i][1] + y;
                dfs(nx, ny, list);
            }
        }
}
