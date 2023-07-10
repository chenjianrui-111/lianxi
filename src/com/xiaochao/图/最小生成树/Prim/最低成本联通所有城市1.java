package com.xiaochao.图.最小生成树.Prim;

import java.util.LinkedList;
import java.util.List;

public class 最低成本联通所有城市1 {
    int minimumCost(int n, int[][] connections){
        //转换成无向图邻接表的形式
        List<int[]>[] graph =buildGraph(n,connections);
        //执行prim 算法
        Prim prim =new Prim(graph);
        if (!prim.allConnected()){
            return -1;
        }
        return prim.weightSum();
    }

    private List<int[]>[] buildGraph(int n, int[][] connections) {
        List<int[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge:connections){
            // 题⽬给的节点编号是从 1 开始的，
            // 但我们实现的 Prim 算法需要从 0 开始编号
            int u =edge[0] - 1;
            int v = edge[1] - 1;
            int weight =edge[2];
            // 「⽆向图」其实就是「双向图」
            // ⼀条边表示为 int[]{from, to, weight}
            graph[u].add(new int[]{u,v,weight});
            graph[v].add(new int[]{v,u,weight});
        }
        return graph;
    }

}
