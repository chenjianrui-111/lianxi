package com.xiaochao.图.DFS;

import java.util.LinkedList;

/**
 * visited：是一个数组，记录已被访问过的顶点，避免重复访问。如果顶点a被访问，visited[a]会被设置为true
 * prev：是一个数组，用于记录搜索路径，但路径是反向存储的。prev[w]存储的是，顶点w是从哪个前驱顶点遍历过来的
 性能分析
 时间复杂度：每条边最多被访问两次：一次遍历，一次回溯，所以时间复杂度O(E)，E指边的个数
 空间复杂度：O(V)，额外空间大小不会超过顶点个数
 */
public class Demo {
    static int v;
    static LinkedList<Integer> adj[];
    boolean found = false; // 全局变量或者类成员变量

    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); ++i) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }
    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
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
