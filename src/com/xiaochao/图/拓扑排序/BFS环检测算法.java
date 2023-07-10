package com.xiaochao.图.拓扑排序;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1、构建邻接表，和之前⼀样，边的⽅向表示「被依赖」关系。
 * 2、构建⼀个 indegree 数组记录每个节点的⼊度，即 indegree[i] 记录节点 i 的⼊度。
 * 3、对 BFS 队列进⾏初始化，将⼊度为 0 的节点⾸先装⼊队列。
 * 4、开始执⾏ BFS 循环，不断弹出队列中的节点，减少相邻节点的⼊度，并将⼊度变为 0 的节点加⼊队列。
 * 5、如果最终所有节点都被遍历过（count 等于节点数），则说明不存在环，反之则说明存在环。
 */
public class
BFS环检测算法 {
    // 主函数
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 建图，有向边代表「被依赖」关系
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 构建⼊度数组
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            // 节点 to 的⼊度加⼀
            indegree[to]++;
        }
        // 根据⼊度初始化队列中的节点
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                // 节点 i 没有⼊度，即没有依赖的节点
                // 可以作为拓扑排序的起点，加⼊队列
                q.offer(i);
            }
        }
        // 记录遍历的节点个数
        int count = 0;
        // 开始执⾏ BFS 循环
        while (!q.isEmpty()) {
            // 弹出节点 cur，并将它指向的节点的⼊度减⼀
            int cur = q.poll();
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    // 如果⼊度变为 0，说明 next 依赖的节点都已被遍历
                    q.offer(next);
                }
            }
        }
        // 如果所有节点都被遍历过，说明不成环
        return count == numCourses;
    }
    // 建图函数
    List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph =new LinkedList[n];
        for (int i = 0; i <n ; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] t:edges){
            int from =t[1],to =t[0];
            graph[from].add(to);
        }
        return graph;
    }

}
