package com.xiaochao.图.最小生成树.Kruskal;

import com.xiaochao.图.并查集算法.UF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 */
public class 连接所有点的最小费用 {
    public int minCostConnectPoints(int[][] points) {
        int n =points.length;
        // 生成所有边及权重
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            for (int j = i+1; j <n ; j++) {
                int xi =points[i][0],yi=points[i][1];
                int xj=points[j][0],yj=points[j][1];
                // 用坐标点在 points 中的索引表示坐标点
                edges.add(new int[] {
                        i, j, Math.abs(xi - xj) + Math.abs(yi - yj)
                });
            }
        }
        //按照权重从小到大排序
        Collections.sort(edges,(a,b) -> {
            return a[2] - b[2];
        });
        //执行Krusal 算法
        int mst =0;
        UF uf =new UF(n);
        for (int[] edge:edges){
            int u =edge[0];
            int v =edge[1];
            int weight =edge[2];
            //若这条边产生环则不能加入mst
            if (uf.connected(u,v)){
                continue;
            }
            mst += weight;
            uf.union(u,v);
        }
        return mst;
    }
}
/**
 *假设一幅图的节点个数为 V，边的条数为 E，首先需要 O(E) 的空间装所有边，而且 Union-Find 算法也需要 O(V) 的空间，所以 Kruskal 算法总的空间复杂度就是 O(V + E)。
 * 时间复杂度主要耗费在排序，需要 O(ElogE) 的时间，Union-Find 算法所有操作的复杂度都是 O(1)，套一个 for 循环也不过是 O(E)，所以总的时间复杂度为 O(ElogE)。
 */
