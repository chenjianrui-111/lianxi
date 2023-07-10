package com.xiaochao.树.最小生成树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给你一个整数 n ，它表示一个 带权有向 图的节点数，节点编号为 0 到 n - 1 。
 * 同时给你一个二维整数数组 edges ，其中 edges[i] = [fromi, toi, weighti] ，表示从 fromi 到 toi 有一条边权为 weighti 的 有向 边。
 * 最后，给你三个 互不相同 的整数 src1 ，src2 和 dest ，表示图中三个不同的点。
 * 请你从图中选出一个 边权和最小 的子图，使得从 src1 和 src2 出发，在这个子图中，都 可以 到达 dest 。如果这样的子图不存在，请返回 -1 。
 * 子图 中的点和边都应该属于原图的一部分。子图的边权和定义为它所包含的所有边的权值之和。
 * 输入：n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
 * 输出：9
 * 解释：
 * 上图为输入的图。
 * 蓝色边为最优子图之一。
 * 注意，子图 [[1,0,3],[0,5,6]] 也能得到最优解，但无法在满足所有限制的前提下，得到更优解。
 */
//基于edges分别建立正、反向的邻接表
//利用Dijkstra算法分别求出src1、src2（正向邻接表），dest（反向邻接表）到图中每个点的单源最短路径
//枚举每个中间点，当三个点都可以到达此中间点时更新一次ans
//注意点：
//Dijkstra只能用堆优化的做法，不然会超时
//要用long[]存储dist，不然会越界
//Java的优先队列使用long时不能直接减，要用内置的api
public class 得到要求路径的最小带权子图 {
    private final int inf = -1;

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        // 1. 基于edges分别建立正、反向的邻接表
        List<int[]>[] adj1 = create(n, edges, false);
        List<int[]>[] adj2 = create(n, edges, true);
        // 2. 利用Dijkstra算法分别求出src1、src2（正向邻接表），dest（反向邻接表）到图中每个点的单源最短路径
        long[] dist1 = dijkstra(src1, n, adj1);
        long[] dist2 = dijkstra(src2, n, adj1);
        long[] dist3 = dijkstra(dest, n, adj2);
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 3. 枚举每个中间点，当三个点都可以到达此中间点时更新一次ans
            if (dist1[i] != inf && dist2[i] != inf && dist3[i] != inf) {
                ans = Math.min(ans, dist1[i] + dist2[i] + dist3[i]);
            }
        }
        return ans == Long.MAX_VALUE ? -1 : ans;
    }

    private List<int[]>[] create(int n, int[][] edges, boolean rev) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], w = edge[2];
            if (!rev) {
                adj[x].add(new int[]{y, w});
            } else {
                adj[y].add(new int[]{x, w});
            }
        }
        return adj;
    }

    private long[] dijkstra(int start, int n, List<int[]>[] adj) {
        long[] dist = new long[n];
        Arrays.fill(dist, inf);
        dist[start] = 0;
        boolean[] vis = new boolean[n];
        // 注意定义long排序的写法
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        heap.offer(new long[]{start, 0});
        while (!heap.isEmpty()) {
            long[] cur = heap.poll();
            if (vis[(int) cur[0]]) {
                continue;
            }
            vis[(int) cur[0]] = true;
            for (int[] next : adj[(int) cur[0]]) {
                if (dist[next[0]] == inf || dist[next[0]] > cur[1] + next[1]) {
                    dist[next[0]] = cur[1] + next[1];
                    heap.offer(new long[]{next[0], dist[next[0]]});
                }
            }
        }
        return dist;
    }
}
