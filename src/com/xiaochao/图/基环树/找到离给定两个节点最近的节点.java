package com.xiaochao.图.基环树;

import java.util.Arrays;

/**
 * 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，每个节点 至多 有一条出边。
 * 有向图用大小为 n 下标从 0 开始的数组 edges 表示，表示节点 i 有一条有向边指向 edges[i] 。如果节点 i 没有出边，那么 edges[i] == -1 。
 * 同时给你两个节点 node1 和 node2 。
 * 请你返回一个从 node1 和 node2 都能到达节点的编号，使节点 node1 和节点 node2 到这个节点的距离 较大值最小化。如果有多个答案，请返回 最小 的节点编号。如果答案不存在，返回 -1 。
 * 注意 edges 可能包含环。
 * 输入：edges = [2,2,3,-1], node1 = 0, node2 = 1
 * 输出：2
 * 解释：从节点 0 到节点 2 的距离为 1 ，从节点 1 到节点 2 的距离为 1 。
 * 两个距离的较大值为 1 。我们无法得到一个比 1 更小的较大值，所以我们返回节点 2 。
 */
public class 找到离给定两个节点最近的节点 {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n =edges.length;
        int res = -1;
        int[] d1 = calcDis(edges,node1);
        int[] d2= calcDis(edges,node2);
        for (int i = 0,minDis = n; i < n ; i++) {
            int d = Math.max(d1[i],d2[i]);
            if (d < minDis){
                minDis = d;
                res = i;
            }
        }
        return res;
    }
    int[] calcDis(int[] edges,int x){
        int n =edges.length;
        int[] dis = new int[n];
        //初始化这个距离为最大值
        Arrays.fill(dis,n);
        // x = edges[x] x走到下一个节点
        for (int i = 0; x >= 0 && dis[x] == n;x = edges[x]) {
            dis[x] = i++;
        }
        return dis;
    }
}
/**
 * 1.如果输入的是 queries 询问数组，每个询问包含两个节点node1 和 node2
 * 要你回答 closestMeetingNode(edges, node1, node2) 的答案，要怎么做呢？
 * 如果都在树上 :LCA ,基环，A->B 取 B
 * 2.如果输入的不止两个节点 node1 和 node2
 * 而是一个很长的 nodes 列表，要怎么做呢？
 * 如果都在树上 :LCA ,基环 转换成若干区间是否有交集
 */
