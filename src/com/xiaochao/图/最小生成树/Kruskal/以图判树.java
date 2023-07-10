package com.xiaochao.图.最小生成树.Kruskal;

//给你输⼊编号从 0 到 n - 1 的 n 个结点，和⼀个⽆向边列表 edges（每条边⽤节点⼆元组表示），请你判 断输⼊的这些边组成的结构是否是⼀棵树。
public class 以图判树 {
    boolean validTree(int n, int[][] edges) {
        //初始化 0..n-1个节点
        UF2 uf = new UF2(n);
        //遍历所有的边，将组成边的两个节点进行链接
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // 若两个节点已经在同⼀连通分量中，会产⽣环
            if (uf.connected(u, v)) {
                return false;
            }
            //若两条边不会生成环，可以说是树的一部分
            uf.union(u, v);
        }
        // 要保证最后只形成了⼀棵树，即只有⼀个连通分量
        return uf.count() == 1;
    }
}
class UF2{
    private int count;
    private int[] parent;
    public UF2(int n){
        this.count =n;
        for (int i = 0; i <n ; i++) {
            parent[i] = i;
        }
    }
    public boolean connected(int p ,int q){
        int rootQ =find(q);
        int rootP = find(p);
        return rootP ==rootQ;
    }
    int find(int x){
        if (parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    void union(int p ,int q){
        int rootQ =find(q);
        int rootP = find(p);
        if (rootP == rootQ){
            return;
        }
        parent[rootP] = rootQ;
        count--;
    }
    int count(){
        return count;
    }
}
/**
 *假设一幅图的节点个数为 V，边的条数为 E，首先需要 O(E) 的空间装所有边，而且 Union-Find 算法也需要 O(V) 的空间，所以 Kruskal 算法总的空间复杂度就是 O(V + E)。
 * 时间复杂度主要耗费在排序，需要 O(ElogE) 的时间，Union-Find 算法所有操作的复杂度都是 O(1)，套一个 for 循环也不过是 O(E)，所以总的时间复杂度为 O(ElogE)。
 */
