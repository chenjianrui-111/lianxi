package com.xiaochao.图.二分图;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 * 示例 1：
 * 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 */
public class 可能的二分法 {
    private boolean ok =true;
    private boolean[] color;
    private boolean[] visited;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        //图节点编号从 1 开始
        color = new boolean[n + 1];
        visited = new boolean[n + 1];
        //转换成邻接表表示图结构
        List<Integer>[] graph =buildGraph(n,dislikes);

        for (int v = 1; v <=n ; v++) {
            if (!visited[v]){
                traverse(graph,v);
            }
        }
        return ok;
    }
    //建图函数
    private List<Integer>[] buildGraph(int n,int[][] dislikes){
        //图节点编号为 1...n
        List<Integer>[] graph =new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : dislikes){
            int from =edge[1],to =edge[0];
            // 「⽆向图」相当于「双向图」
            // v -> w
            graph[from].add(to);
            // w -> v
            graph[to].add(from);
        }
        return graph;
    }
    void traverse(List<Integer>[] graph,int v){
        if (!ok) return;
        visited[v] = true;
        for (int w :graph[v]){
            if (!visited[w]){
                color[w] = !color[v];
                traverse(graph, w);
            }else {
                if (color[v] == color[w]){
                    ok =false;
                }
            }
        }
    }
}
