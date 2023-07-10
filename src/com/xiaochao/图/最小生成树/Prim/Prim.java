package com.xiaochao.图.最小生成树.Prim;

import java.util.List;
import java.util.PriorityQueue;

public class
Prim {
    // 核⼼数据结构，存储「横切边」的优先级队列
    private PriorityQueue<int[]> pq;
    // 类似 visited 数组的作⽤，记录哪些节点已经成为最⼩⽣成树的⼀部分
    private boolean[] inMST;
    // 记录最⼩⽣成树的权重和
    private int weightSum = 0;
    // graph 是⽤邻接表表示的⼀幅图，
    // graph[s] 记录节点 s 所有相邻的边，
    // 三元组 int[]{from, to, weight} 表示⼀条边
    private List<int[]>[] graph;
    public Prim(List<int[]>[] graph) {
        this.graph = graph;
        this.pq = new PriorityQueue<>((a, b) -> {
            // 按照边的权重从⼩到⼤排序
            return a[2] - b[2];
        });
        // 图中有 n 个节点
        int n = graph.length;
        this.inMST = new boolean[n];
        // 随便从⼀个点开始切分都可以，我们不妨从节点 0 开始
        inMST[0] = true;
        cut(0);
        // 不断进⾏切分，向最⼩⽣成树中添加边
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int to = edge[1];
            int weight = edge[2];
            if (inMST[to]) {
                // 节点 to 已经在最⼩⽣成树中，跳过
                // 否则这条边会产⽣环
                continue;
            }
            // 将边 edge 加⼊最⼩⽣成树
            weightSum += weight;
            inMST[to] = true;
            // 节点 to 加⼊后，进⾏新⼀轮切分，会产⽣更多横切边
            cut(to);
        }
    }
    // 将 s 的横切边加⼊优先队列
    private void cut(int s) {
        // 遍历 s 的邻边
        for (int[] edge : graph[s]) {
            int to = edge[1];
            if (inMST[to]) {
                // 相邻接点 to 已经在最⼩⽣成树中，跳过
                // 否则这条边会产⽣环
                continue;
            }
            // 加⼊横切边队列
            pq.offer(edge);
        }
    }
    // 最⼩⽣成树的权重和
    public int weightSum() {
        return weightSum;
    }
    // 判断最⼩⽣成树是否包含图中的所有节点
    public boolean allConnected() {
        for (int i = 0; i < inMST.length; i++) {
            if (!inMST[i]) {
                return false;
            }
        }
        return true;
    }
}
/**
 *Kruskal 算法是在⼀开始的时候就把所有的边排序，然后从权重最⼩的边开始挑选属于最⼩⽣成树的边，组建 最⼩⽣成树。
 *  Prim 算法是从⼀个起点的切分（⼀组横切边）开始执⾏类似 BFS 算法的逻辑，借助切分定理和优先级队列动 态排序的特性，
 *  从这个起点「⽣⻓」出⼀棵最⼩⽣成树。 说到这⾥，Prim 算法的时间复杂度是多少呢？ 这个不难分析，复杂度主要在优先级队列 pq 的操作上，
 *  由于 pq ⾥⾯装的是图中的「边」，假设⼀幅图边的 条数为 E，那么最多操作 O(E) 次 pq。每次操作优先级队列的时间复杂度取决于队列中的元素个数，
 *  取最坏 情况就是 O(logE)。 所以这种 Prim 算法实现的总时间复杂度是 O(ElogE)。回想⼀下 Kruskal 算法，
 *  它的时间复杂度主要是给所 有边按照权重排序，也是 O(ElogE)。 不过话说回来，和后⽂ Dijkstra 算法 类似，
 *  Prim 算法的时间复杂度也是可以优化的，但优化点在于优先级队 列的实现上.
 */
