package com.xiaochao.图.最短路;

import java.util.*;

/**
 * 城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。图中的边用一个二维整数数组 edges 表示，
 * 其中每个 edges[i] = [ui, vi] 表示一条节点 ui 和节点 vi 之间的双向连通边。每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time 分钟。
 * 每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都 同时 改变。
 * 你可以在 任何时候 进入某个节点，但是 只能 在节点 信号灯是绿色时 才能离开。如果信号灯是  绿色 ，你 不能 在节点等待，必须离开。
 * 第二小的值 是 严格大于 最小值的所有值中最小的值。
 * 例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
 * 给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
 * 注意：
 * 你可以 任意次 穿过任意顶点，包括 1 和 n 。
 * 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
 */
//public class 到达目的地的第二短时间 {
//    private  final int inf = -1;
//    public int secondMinimum(int n, int[][] edges, int time, int change) {
//        List<int[]>[] adj = create(n,edges);
//        long[] dist = dijkstra(time, n, adj,change);
//        int ans = Integer.MAX_VALUE;
//        for (int i = 0; i < n ; i++) {
//
//        }
//    }
//    private List<int[]>[] create(int n,int[][] edges){
//        List<int[]>[] adj = new List[n];
//        for (int i = 0; i < n ; i++) {
//            adj[i] = new ArrayList<>();
//        }
//        for (int[] edge : edges) {
//            int from = edge[0];
//            int to = edge[1];
//            int weight = edge[2];
//            adj[from].add(new int[]{to,weight});
//            adj[to].add(new int[]{from,weight});
//        }
//        return adj;
//    }
//    private long dijkstra(int start, int n, List<int[]>[] adj, int time, int change){
//        long[] dist =new long[n];
//        Arrays.fill(dist,inf);
//        dist[start] = 0;
//        boolean[] visited = new boolean[n];
//        // 注意定义long排序的写法
//        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
//        heap.offer(new long[]{start, 0});
//        while (!heap.isEmpty()) {
//            long[] cur = heap.poll();
//            if (visited[(int) cur[0]]) {
//                continue;
//            }
//            visited[(int) cur[0]] = true;
//            for (int[] next : adj[(int) cur[0]]) {
//                if (dist[next[0]] == inf || dist[next[0]] > cur[1] + next[1]) {
//                    dist[next[0]] = cur[1] + next[1];
//                    heap.offer(new long[]{next[0], dist[next[0]]});
//                }
//            }
//        }
//        return dist[dist.length - 1];
//    }
//}
class Solution001{
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(u).add(new int[]{v, time});
            graph.get(v).add(new int[]{u, time});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        int[] dist2 = new int[n + 1];
        Arrays.fill(dist2, Integer.MAX_VALUE);
        dist2[1] = Integer.MAX_VALUE;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0], d = curr[1];

            if (d > dist2[node]) {
                continue;
            }

            for (int[] next : graph.getOrDefault(node, new ArrayList<>())) {
                int neighbor = next[0], weight = next[1];
                int t = d + weight;

                if (t > dist[neighbor] + change) {
                    t = dist[neighbor] + change;
                }

                if (t < dist[neighbor]) {
                    int tmp = dist[neighbor];
                    dist[neighbor] = t;
                    t = tmp;
                    pq.offer(new int[]{neighbor, dist[neighbor]});
                }

                if (t > dist[neighbor] && t < dist2[neighbor]) {
                    dist2[neighbor] = t;
                    pq.offer(new int[]{neighbor, dist2[neighbor]});
                }
            }
        }

        return dist2[n];
    }
}
