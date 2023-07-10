package com.daimasuixianglu.tulun;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
 * 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
 * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
 */
public class 访问所有节点的最短路径 {
        int INF = 0x3f3f3f3f;
        public int shortestPathLength(int[][] graph) {
            int n = graph.length;
            int mask = 1 << n;

            // 初始化所有的 (state, u) 距离为正无穷
            int[][] dist = new int[mask][n];
            for (int i = 0; i < mask; i++) Arrays.fill(dist[i], INF);

            // 因为可以从任意起点出发，先将起始的起点状态入队，并设起点距离为 0
            Deque<int[]> d = new ArrayDeque<>(); // state, u
            for (int i = 0; i < n; i++) {
                dist[1 << i][i] = 0;
                d.addLast(new int[]{1 << i, i});
            }

            // BFS 过程，如果从点 u 能够到达点 i，则更新距离并进行入队
            while (!d.isEmpty()) {
                int[] poll = d.pollFirst();
                int state = poll[0], u = poll[1], step = dist[state][u];
                if (state == mask - 1) return step;
                for (int i : graph[u]) {
                    if (dist[state | (1 << i)][i] == INF) {
                        dist[state | (1 << i)][i] = step + 1;
                        d.addLast(new int[]{state | (1 << i), i});
                    }
                }
            }
            return -1; // never
        }
}
