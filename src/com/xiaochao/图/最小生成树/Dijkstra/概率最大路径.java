package com.xiaochao.图.最小生成树.Dijkstra;

import java.util.*;

/**
 * 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，
 * 且该边遍历成功的概率为 succProb[i] 。
 * 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
 * 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
 */
public class 概率最大路径 {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<double[]>[] graph = new LinkedList[n];
        for (int i = 0; i <n ; i++) {
            graph[i] = new LinkedList<>();
        }
        // 构造邻接表结构表示图
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight = succProb[i];
            // ⽆向图就是双向图；先把 int 统⼀转成 double，待会再转回来
            graph[from].add(new double[]{(double)to, weight});
            graph[to].add(new double[]{(double)from, weight});
        }

        return dijkstra(start, end, graph);
    }
    class State {
        // 图节点的 id
        int id;
        // 从 start 节点到达当前节点的概率
        double probFromStart;
        State(int id, double probFromStart) {
            this.id = id;
            this.probFromStart = probFromStart;
        }
    }
    double dijkstra(int start, int end, List<double[]>[] graph) {
        // 定义：probTo[i] 的值就是节点 start 到达节点 i 的最⼤概率
        double[] probTo = new double[graph.length];
        // dp table 初始化为⼀个取不到的最⼩值
        Arrays.fill(probTo, -1);
        // base case，start 到 start 的概率就是 1
        probTo[start] = 1;
        // 优先级队列，probFromStart 较⼤的排在前⾯
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(b.probFromStart, a.probFromStart);
        });
        // 从起点 start 开始进⾏ BFS
        pq.offer(new State(start, 1));
        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curNodeID = curState.id;
            double curProbFromStart = curState.probFromStart;
            // 遇到终点提前返回
            if (curNodeID == end) {
                return curProbFromStart;
            }

            if (curProbFromStart < probTo[curNodeID]) {
                // 已经有⼀条概率更⼤的路径到达 curNode 节点了
                continue;
            }
            // 将 curNode 的相邻节点装⼊队列
            for (double[] neighbor : graph[curNodeID]) {
                int nextNodeID = (int)neighbor[0];
                // 看看从 curNode 达到 nextNode 的概率是否会更⼤
                double probToNextNode = probTo[curNodeID] * neighbor[1];
                if (probTo[nextNodeID] < probToNextNode) {
                    probTo[nextNodeID] = probToNextNode;
                    pq.offer(new State(nextNodeID, probToNextNode));
                }
            }
        }
        // 如果到达这⾥，说明从 start 开始⽆法到达 end，返回 0
        return 0.0;
    }
}
