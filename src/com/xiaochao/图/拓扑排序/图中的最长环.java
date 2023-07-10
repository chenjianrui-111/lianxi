package com.xiaochao.图.拓扑排序;

import java.util.*;

/**
 * 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，其中每个节点 至多 有一条出边。
 * 图用一个大小为 n 下标从 0 开始的数组 edges 表示，节点 i 到节点 edges[i] 之间有一条有向边。如果节点 i 没有出边，那么 edges[i] == -1 。
 * 请你返回图中的 最长 环，如果没有任何环，请返回 -1 。
 * 一个环指的是起点和终点是 同一个 节点的路径。
 * 输入：edges = [3,3,4,2,3]
 * 输出去：3
 * 解释：图中的最长环是：2 -> 4 -> 3 -> 2 。
 * 这个环的长度为 3 ，所以返回 3 。
 */
public class 图中的最长环 {
    public int longestCycle(int[] edges) {
        /*
        基环树+时间戳
        times[i]记录首次访问编号为i的节点时间点，clock维护当前的时间节点
        我们枚举每个出发点
        1.遇到之前访问过的节点times[i]>0，跳过
        2.首次访问节点i，从节点i出发向前走同时标记访问过的节点的时间戳
        如果前方出现过times[j]>0的情况说明这个时候节点已经被访问过，说明成环了
        注意:为了避免后面枚举的节点重复经过之前访问过的节点但是不成环的情况，要加一个判断条件times[j]>=start
        其中start为从i点出发的时间戳，若成环必然有times[j]>=start，重复经过旧节点但是不成环的情况是times[j]<start
        因为你这个times[j]之前一轮出发点中已经枚举过了，时间必定在该轮start之前
        维护这个环的长度最大值就是答案
        时间复杂度:O(N) 空间复杂度:O(N)
         */
        int res = -1;
        int n = edges.length;
        int[] times = new int[n];
        // 枚举每个出发点
        for (int i = 0, clock = 1; i < n; i++) {
            if (times[i] > 0) continue; // 这个出发点已经访问过了，跳过
            int start = clock;  // start维护当前出发点的出发时间
            // 枚举以节点i为出发点的路径上的点
            for (int x = i; x != -1; x = edges[x]) {
                if (times[x] > 0) { // 已经访问过该节点(不一定是该轮的)
                    // 若该节点是该轮才访问的说明成环了
                    if(times[x] >= start) res = Math.max(res, clock - times[x]);  // 维护最大环值
                    break;  // 已经访问过节点就退出，不论是该轮访问的还是之前
                }
                times[x] = clock++; // 标记访问该节点的最早时间戳
            }
        }
        return res;
    }
}
/***** 拓展：如果输出环上所有的节点，要怎么做 ****/
class  Solution2{
    public static int longestCycle(int[] edges) {
        /*
        基环树+时间戳
        times[i]记录首次访问编号为i的节点时间点，clock维护当前的时间节点
        我们枚举每个出发点
        1.遇到之前访问过的节点times[i]>0，跳过
        2.首次访问节点i，从节点i出发向前走同时标记访问过的节点的时间戳
        如果前方出现过times[j]>0的情况说明这个时候节点已经被访问过，说明成环了
        注意:为了避免后面枚举的节点重复经过之前访问过的节点但是不成环的情况，要加一个判断条件times[j]>=start
        其中start为从i点出发的时间戳，若成环必然有times[j]>=start，重复经过旧节点但是不成环的情况是times[j]<start
        因为你这个times[j]之前一轮出发点中已经枚举过了，时间必定在该轮start之前
        维护这个环的长度最大值就是答案
        时间复杂度:O(N) 空间复杂度:O(N)
         */
        int res = -1;
        int n = edges.length;
        int[] times = new int[n];
        // 枚举每个出发点
        for (int i = 0, clock = 1; i < n; i++) {
            if (times[i] > 0) continue; // 这个出发点已经访问过了，跳过
            int start = clock;  // start维护当前出发点的出发时间
            // 枚举以节点i为出发点的路径上的点
            for (int x = i; x != -1; x = edges[x]) {
                if (times[x] > 0) { // 已经访问过该节点(不一定是该轮的)
                    // 若该节点是该轮才访问的说明成环了
                    if(times[x] >= start) res = Math.max(res, clock - times[x]);  // 维护最大环值
                    /***** 拓展：如果输出环上所有的节点，要怎么做 ****/
//                    List list = new ArrayList();
//                    list.add(x);
//                    int y = edges[x];
//                    while (y != x){
//                        list.add(y);
//                        y =edges[y];
//                    }
//                    for (int j = 0; j <list.size() ; j++) {
//                        System.out.print(list.get(j));
//                    }
                    break;  // 已经访问过节点就退出，不论是该轮访问的还是之前
                }
                times[x] = clock++; // 标记访问该节点的最早时间戳
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        StringBuilder sb =new StringBuilder();
        int n =sc.nextInt();
        int[] arr =new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        longestCycle(arr);
    }
}
