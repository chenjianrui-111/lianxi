package com.xiaochao.图.并查集算法;

/**
 * 给你输⼊⼀个包含 n 个节点的图，⽤⼀个整数 n 和⼀个数组 edges 表示，其中 edges[i] = [ai, bi] 表 示图中节点 ai 和 bi 之间有⼀条边。
 * 请你计算这幅图的连通分量个数。
 */
public class 无向图中连通分量的数目 {
    int countComponents(int n, int[][] edges){
        UF uf = new UF(n);
        //将每个节点进行联通
        for (int []e :edges){
            uf.union(e[0],e[1]);
        }
        return uf.count();
    }
}
