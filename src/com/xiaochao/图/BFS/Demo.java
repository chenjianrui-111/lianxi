package com.xiaochao.图.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * visited：是一个数组，记录已被访问过的顶点，避免重复访问。如果顶点a被访问，visited[a]会被设置为true
 * queue：是一个队列，存储已经被访问，但相连的顶点还没有被访问的顶点。记录了当前层需要访问的顶点
 * prev：是一个数组，用于记录搜索路径，但路径是反向存储的。prev[w]存储的是，顶点w是从哪个前驱顶点遍历过来的
 * 性能分析
 * 时间复杂度：最坏情况要遍历整个图，复杂度O(V+E)，V指顶点个数，E指边的个数。通常边的个数大于顶点个数，所以可以简化成O(E)
 * 空间复杂度：O(V)，因为额外使用的三个存储空间，大小都不超过顶点的个数。
 */
public class Demo {
    static int v;
    static LinkedList<Integer> adj[];
    public  void bfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[v];
        visited[s]=true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            //从待访问队列中取出一个顶点
            int w = queue.poll();
            //遍历这个顶点的连接顶点列表
            for (int i = 0; i < adj[w].size(); ++i) {
                //依次访问这个与顶点连接的顶点
                int q = adj[w].get(i);
                //如果当前顶点没有被访问过
                if (!visited[q]) {
                    //记录，是从w访问到q的
                    prev[q] = w;
                    //判断是否为目标顶点
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
                //当前顶点被访问过，直接跳过继续循环
            }
        }
    }

    private  void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }
}
  class Graph { // 无向图
    private int v; // 顶点的个数
    private LinkedList<Integer> adj[]; // 邻接表，数组元素是链表类型

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }
}
