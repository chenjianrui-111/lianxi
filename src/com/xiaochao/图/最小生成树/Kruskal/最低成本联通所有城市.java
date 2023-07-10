package com.xiaochao.图.最小生成树.Kruskal;

import com.xiaochao.图.并查集算法.UF;

import java.util.Arrays;
/**
 *假设一幅图的节点个数为 V，边的条数为 E，首先需要 O(E) 的空间装所有边，而且 Union-Find 算法也需要 O(V) 的空间，所以 Kruskal 算法总的空间复杂度就是 O(V + E)。
 * 时间复杂度主要耗费在排序，需要 O(ElogE) 的时间，Union-Find 算法所有操作的复杂度都是 O(1)，套一个 for 循环也不过是 O(E)，所以总的时间复杂度为 O(ElogE)。
 */
public class 最低成本联通所有城市 {
    int minimumCost(int n, int[][] connections) {
        UF uf =new UF(n+1);
        //对所有边按照权重从小到大排序
        Arrays.sort(connections,(a,b) ->(a[2] - b[2]));
        //记录最小生成树的权重之和
        int mst = 0;
        for (int[] edge : connections){
            int u =edge[0];
            int v =edge[1];
            int weight =edge[2];
            //若这条边会产生环，则不能加入 mst
            if (uf.connected(u,v)){
                continue;
            }
            //若这条边不会产生环，则加入最小生成树
            mst += weight;
            uf.union(u,v);
        }
        // 保证所有节点都被连通
        // 按理说 uf.count() == 1 说明所有节点被连通
        // 但因为节点 0 没有被使用，所以 0 会额外占用一个连通分量
        return uf.count() == 2 ? mst : -1;
    }
}
