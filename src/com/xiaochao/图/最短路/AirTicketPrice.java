package com.xiaochao.图.最短路;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
/**
 * CVTE在国内外有很多办事处，从一个城市飞往另一个城市有很多条航班路线，在做好规划出发时间的情况下，为了保证机票总额最小，我们允许通过中转的方式来乘坐飞机。假设从A城市到B城市的航班价格为N给你W个城市的航班信息R，输入任意的A城市和B城市，求最便宜的转机价格M。例女:例子1
 * W=3 R={{0,1,600},{1,2,500},{0,21300}},A =0 B=2参数说明 (第一个参数是航班出发点、第二个参数是航班终点，第三个参数是费用)返回
 * M =1100
 * 例子2
 * W = 3, R = {{0,1,600),{1,2,800},{0,2,1300}}, A = 0, B =2参数说明(第一个参数是航班出发点、第二个参数是航班终点，第三个参数是费用)返回
 * M = 1300
 */

/**
 * 对于这个问题，可以运用图论中的最短路径算法解决。因为题目中给出了城市间的航班信息，可以将所有城市及其之间的航班构成一个有向加权图，其中每个城市为图的一个节点，每条航班为节点之间的一条有向边，并附带着该边的费用。
 * 然后，可以使用 Dijkstra 算法或 Bellman-Ford 算法等最短路径算法，计算从起点 A 到终点 B 的最短路径，即最小航班总价格。
 * 如果允许中转，则可以在图上寻找从起点到终点的所有简单路径，然后计算它们的总价格，并从中选择最小值作为答案。这可以通过深度优先搜索、广度优先搜索等遍历算法实现。
 */
public class AirTicketPrice {
    public static void main(String[] args) {
        int w = 3;
        int[][] r = new int[][]{{0,1,600},{1,2,500},{0,2,1300}};
        int A = 0;
        int B = 2;
        int i = findMinAirTicketPrice(w,r,A,B);
        System.out.println(i);
    }
    public static int findMinAirTicketPrice(int w, int[][] R, int A, int B) {
        // 构建邻接表，存储图中每个节点的邻居和对应的边权重
        List<int[]>[] adjList = new List[w];
        for (int i = 0; i < w; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] r : R) {
            int u = r[0], v = r[1], cost = r[2];
            adjList[u].add(new int[]{v, cost});
        }

        // 使用 Dijkstra 算法求最短路径
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[w];
        int[] dist = new int[w];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new int[]{A, 0});
        dist[A] = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (visited[u]) continue;
            visited[u] = true;
            for (int[] neighbor : adjList[u]) {
                int v = neighbor[0], cost = neighbor[1];
                if (dist[v] > d + cost) {
                    dist[v] = d + cost;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        // 如果不允许中转，则直接返回最短路径长度
        if (dist[B] != Integer.MAX_VALUE) return dist[B];

        // 否则，在所有简单路径中寻找最小值
        int minCost = Integer.MAX_VALUE;
        dfs(adjList, visited, dist, A, B, 0, minCost);
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    private static void dfs(List<int[]>[] adjList, boolean[] visited, int[] dist,
                            int curr, int dest, int cost, int minCost) {
        if (curr == dest) {
            minCost = Math.min(minCost, cost);
            return;
        }
        visited[curr] = true;
        for (int[] neighbor : adjList[curr]) {
            int v = neighbor[0], edgeCost = neighbor[1];
            if (!visited[v] && cost + edgeCost + dist[v] < minCost) {
                dfs(adjList, visited, dist, v, dest, cost + edgeCost, minCost);
            }
        }
        visited[curr] = false;
    }
}
