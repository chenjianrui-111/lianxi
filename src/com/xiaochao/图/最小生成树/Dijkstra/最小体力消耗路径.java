package com.xiaochao.图.最小生成树.Dijkstra;

import java.util.*;

/**
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 */
public class 最小体力消耗路径 {
    //方向数组，上下左右的坐标偏移量
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    //返回坐标(x,y)的上下左右相邻坐标
    List<int[]> adj(int[][] matrix, int x, int y) {
        int m = matrix.length, n = matrix[0].length;
        //存储相邻的节点
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                //索引越界
                continue;
            }
            neighbors.add(new int[]{nx, ny});
        }
        return neighbors;
    }

    private static class state {
        //矩阵中的一个位置
        int x, y;
        //从起点(0,0) 到当前位置的最小体力消耗(距离)
        int effortFromStart;

        state(int x, int y, int effortFromStart) {
            this.x = x;
            this.y = y;
            this.effortFromStart = effortFromStart;
        }
    }

    // Dijkstra 算法，计算 (0, 0) 到 (m - 1, n - 1) 的最⼩体⼒消耗
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        // 定义：从 (0, 0) 到 (i, j) 的最⼩体⼒消耗是 effortTo[i][j]
        int[][] effortTo = new int[m][n];
        // dp table 初始化为正⽆穷
        for (int i = 0; i < m; i++) {
            Arrays.fill(effortTo[i], Integer.MAX_VALUE);
        }
        // base case，起点到起点的最⼩消耗就是 0
        effortTo[0][0] = 0;
        // 优先级队列，effortFromStart 较⼩的排在前⾯
        Queue<state> pq = new PriorityQueue<>((a, b) -> {
            return a.effortFromStart - b.effortFromStart;
        });

        // 从起点 (0, 0) 开始进⾏ BFS
        pq.offer(new state(0, 0, 0));
        while (!pq.isEmpty()) {
            state curState = pq.poll();
            int curX = curState.x;
            int curY = curState.y;
            int curEffortFromStart = curState.effortFromStart;
            //到达中带你提前结束
            if (curX == m - 1 && curY == n - 1) {
                return curEffortFromStart;
            }
            if (curEffortFromStart > effortTo[curX][curY]) {
                continue;
            }
            // 将 (curX, curY) 的相邻坐标装⼊队列
            for (int[] neighbor : adj(heights, curX, curY)) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                // 计算从 (curX, curY) 达到 (nextX, nextY) 的消耗
                int effortToNextNode = Math.max(
                        effortTo[curX][curY],
                        Math.abs(heights[curX][curY] - heights[nextX][nextY])
                );
                // 更新 dp table
                if (effortTo[nextX][nextY] > effortToNextNode) {
                    effortTo[nextX][nextY] = effortToNextNode;
                    pq.offer(new state(nextX, nextY, effortToNextNode));
                }
            }
        }
        // 正常情况不会达到这个 return
        return -1;
    }
}
class Solution{
    int N =10009;
    int[] p = new int[N];
    int row,col;
    void union(int a,int b){
        p[find(a)] = p[find(b)];
    }
    boolean query(int a,int b){
        return p[find(a)] == p[find(b)];
    }
    int find(int x){
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
    public int minimumEffortPath(int[][] heights) {
        row = heights.length;
        col = heights[0].length;
        //初始化并查集
        for (int i = 0; i < row * col ; i++) {
            p[i] = i;
        }
        // 预处理出所有的边
        // edge 存的是 [a, b, w]：代表从 a 到 b 的体力值为 w
        // 虽然我们可以往四个方向移动，但是只要对于每个点都添加「向右」和「向下」两条边的话，其实就已经覆盖了所有边了
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < col ; j++) {
                int idx = getIndex(i,j);
                if (i + 1 < row){
                    int a = idx, b  =getIndex(i+1,j);
                    int w = Math.abs(heights[i][j]-heights[i+1][j]);
                    edges.add(new int[]{a,b,w});
                }
                if (j + 1 < col){
                    int a= idx,b=getIndex(i,j+1);
                    int w = Math.abs(heights[i][j] - heights[i][j+1]);
                    edges.add(new int[]{a,b,w});
                }
            }
        }
        Collections.sort(edges,(a,b)->
            a[2] - b[2]
        );
        // 从「小边」开始添加，当某一条边别应用之后，恰好使用得「起点」和「结点」联通
        // 那么代表找到了「最短路径」中的「权重最大的边」
        int start = getIndex(0,0),end = getIndex(row-1,col-1);
        for (int[] edge : edges) {
            int a = edge[0] , b = edge[1] ,w = edge[2];
            union(a,b);
            if (query(start,end)){
                return w;
            }
        }
        return 0;
    }
    int getIndex(int i,int j){
        return i * col + j;
    }
}
//令行数为 r，列数为 c，那么节点的数量为 r ∗ c，无向边的数量严格为 r ∗ (c − 1) + c ∗ (r − 1)，数量级上为 r ∗ c。 •
// 时间复杂度：获取所有的边复杂度为 O(r ∗ c)，排序复杂度为 O((r ∗ c)log (r ∗ c))，遍历得到最终解复杂度为 O(r ∗ c)。
// 整体复杂度为 O((r ∗ c)log (r ∗ c))。 • 空间复杂度：使用了并查集数组。复杂度为 O(r ∗ c)。
