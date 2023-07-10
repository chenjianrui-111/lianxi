package com.xiaochao.动态规划;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 有 n 个城市通过⼀些航班连接。给你⼀个数组 flights，其中 flights[i] = [fromi, toi,
 * pricei]，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。 现在给定所有的城市和航班，以及出发城市 src 和⽬的地 dst，你的任务是找到出⼀条最多经过 k 站中转的 路线，使得从 src 到 dst 的价格最便宜，并返回该价格。如果不存在这样的路线，则输出 -1。 示例 1：输⼊：
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出：200
 * 解释： 城市航班图如下 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红⾊所示。
 */
public class K站中转内最便宜的航班 {

    HashMap<Integer, List<int[]>> indegree;
    int src,dst;
    int[][] memo;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst,
                                 int K) {
        // 将中转站个数转化成边的条数
        K++;
        this.src = src;
        this.dst = dst;
        memo = new int[n][K+1];
        for (int[] row :memo){
            Arrays.fill(row,-888);
        }
        indegree = new HashMap<>();
        for (int[] f: flights){
            int from = f[0];
            int to = f[1];
            int price = f[2];
            //记录谁指向该节点，以及之间的权重
            indegree.putIfAbsent(to,new LinkedList<>());
            indegree.get(to).add(new int[]{from,price});
        }
        return dp(dst,K);
    }
    // 定义：从 src 出发，k 步之内到达 s 的最短路径权重
    int dp(int dst ,int K){
        //base case
        if (dst == src) return 0;
        if (K == 0) return -1;
        if(memo[dst][K] != -888){
            return memo[dst][K];
        }
        int res = Integer.MAX_VALUE;
        if (indegree.containsKey(dst)){
            // 当 dst 有⼊度节点时，分解为⼦问题
            for (int[] v : indegree.get(dst)) {
                int from = v[0];
                int price =v[1];
                //从src到达相邻的入度节点所需的最短路径权重
                int subProblem = dp(from,K-1);
                //无解跳过
                if (subProblem != -1){
                    res = Math.min(res,price + subProblem);
                }
            }
        }
        memo[dst][K] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[dst][K];
    }
}
