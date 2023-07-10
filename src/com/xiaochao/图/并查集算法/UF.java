package com.xiaochao.图.并查集算法;

/**
 *Union-Find 算法的复杂度可以这样分析：构造函数初始化数据结构需要 O(N) 的时间和空间复杂度；
 * 连通两 个节点 union、判断两个节点的连通性 connected、计算连通分量 count 所需的时间复杂度均为 O(1)。
 */
public class UF {
    //连通分量个数
    private int count ;
    //存储每个结点的父节点
    private int[] parent;

    //n 为图中节点个数
    public UF(int n){
        this.count = n;
        parent = new int[n];
        for (int i = 0; i <n ; i++) {
            parent[i] = i;
        }
    }
    //将节点 q 和节点 p 连通
    public void union(int p ,int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ){
            return;
        }
        parent[rootP] = rootQ;
        //两个连通分量合并为一个连通分量
        count--;
    }
    // 判断节点 p 和节点 q 是否连通
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    // 这段迭代代码⽅便你理解递归代码所做的事情
//    public int find(int x) {
//        // 先找到根节点
//        int root = x;
//        while (parent[root] != root) {
//            root = parent[root];
//        }
//        // 然后把 x 到根节点之间的所有节点直接接到根节点下⾯
//        int old_parent = parent[x];
//        while (x != root) {
//            parent[x] = root;
//            x = old_parent;
//            old_parent = parent[old_parent];
//        }
//        return root;
//    }
    // 返回图中的连通分量个数
    public int count() {
        return count;
    }
}
//1、⽤ parent 数组记录每个节点的⽗节点，相当于指向⽗节点的指针，所以 parent 数组内实际存储着⼀个 森林（若⼲棵多叉树）。
// 2、⽤ size 数组记录着每棵树的重量，⽬的是让 union 后树依然拥有平衡性，保证各个 API 时间复杂度为 O(logN)，⽽不会退化成链表影响操作效率。
// 3、在 find 函数中进⾏路径压缩，保证任意树的⾼度保持在常数，使得各个 API 时间复杂度为 O(1)。使⽤了 路径压缩之后，可以不使⽤ size 数组的平衡优化。
