package com.xiaochao.图.二分图;

import java.util.LinkedList;
import java.util.Queue;

public class BFS判断二分图 {
    // 记录图是否符合⼆分图性质
    private boolean ok = true;
    // 记录图中节点的颜⾊，false 和 true 代表两种不同颜⾊
    private boolean[] color;
    // 记录图中节点是否被访问过
    private boolean[] visited;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new boolean[n];
        visited = new boolean[n];

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                // 改为使⽤ BFS 函数
                bfs(graph, v);
            }
        }
        return ok;
    }
    // 从 start 节点开始进⾏ BFS 遍历
    private void bfs(int[][] graph, int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);

        while (!q.isEmpty() && ok) {
            int v = q.poll();
            // 从节点 v 向所有相邻节点扩散
            for (int w : graph[v]) {
                if (!visited[w]) {
                    // 相邻节点 w 没有被访问过
                    // 那么应该给节点 w 涂上和节点 v 不同的颜⾊
                    color[w] = !color[v];
                    // 标记 w 节点，并放⼊队列
                    visited[w] = true;
                    q.offer(w);
                } else {
                    // 相邻节点 w 已经被访问过
                    // 根据 v 和 w 的颜⾊判断是否是⼆分图
                    if (color[w] == color[v]) {
                        // 若相同，则此图不是⼆分图
                        ok = false;
                    }
                }
            }
        }
    }
}
