package com.xiaochao.图.拓扑排序;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS拓扑排序算法 {
    // 主函数
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 建图，和环检测算法相同
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 计算⼊度，和环检测算法相同
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            indegree[to]++;
        }
        // 根据⼊度初始化队列中的节点，和环检测算法相同
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        // 记录拓扑排序结果
        int[] res = new int[numCourses];
        // 记录遍历节点的顺序（索引）
        int count = 0;
        // 开始执⾏ BFS 算法
        while (!q.isEmpty()) {
            int cur = q.poll();
            // 弹出节点的顺序即为拓扑排序结果
            res[count] = cur;
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        if (count != numCourses) {
            // 存在环，拓扑排序不存在
            return new int[]{};
        }

        return res;
    }
    // 建图函数
    List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph =new LinkedList[n];
        for (int i = 0; i <n ; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges){
            int from =edge[1] ,to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }
}
