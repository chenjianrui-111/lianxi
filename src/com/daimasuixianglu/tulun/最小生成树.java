package com.daimasuixianglu.tulun;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 一个有 n 户人家的村庄，有 m 条路相互连接着。村里现在要修路，每条路都有一个成本价格，现在请你帮忙计算下，最少需要花费多少钱，就能让这 n 户人家连接起来。
 *  为一个二维数组，每个元素是一个长度为 3 的一维数组 a ， a[0] 和 a[1] 表示村庄 a[0] 和村庄 a[1] 有一条路，修这条路的成本价格为 a[2] .
 * 每户之间可能有多条道路连接，但不可能自己与自己相连
 * 数据范围: 1<=n<=5*10^3 ，1<=m<=5*10^3  ，1<=a[2]<=10^4
 * 进阶： 时间复杂度 O(N+MlogM) ， 空间复杂度 O(n)
 * 示例1
 * 输入
 * 3,3,[[1,3,3],[1,2,1],[2,3,1]]
 * 输出
 * 2
 * 示例2
 * 输入
 * 2,1,[[1,2,1]]
 * 输出
 * 1
 */
public class 最小生成树 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 返回最小的花费代价使得这n户人家连接起来
     * @param n int n户人家的村庄
     * @param m int m条路
     * @param cost int二维数组 一维3个参数，表示连接1个村庄到另外1个村庄的花费的代价
     * @return int
     */

    class Edge implements Comparable<Edge>{
        public int weight;
        public int from;
        public int to;
        public Edge(int weight,int from,int to){
            this.weight=weight;
            this.from=from;
            this.to=to;
        }
        @Override
        public int compareTo(Edge e) {
            return weight-e.weight;
        }
    }

    public int miniSpanningTree (int n, int m, int[][] cost) {
        // write code here
        int res=0;
        HashSet<Edge>[] graph=new HashSet[n+1];
        for (int i = 0; i <=n ; i++) {
            graph[i]=new HashSet<>();
        }
        for (int[] arr:cost){
            Edge e=new Edge(arr[0],arr[1],arr[2]);
            graph[arr[0]].add(e);
            Edge e1=new Edge(arr[1],arr[0],arr[2]);
            graph[arr[1]].add(e1);
        }
        //构建优先队列
        PriorityQueue<Edge> queue=new PriorityQueue<>();
        for (Edge e:graph[1]){
            queue.add(e);
        }
        boolean visited[]=new boolean[n+1];
        visited[1]=true;
        while (!queue.isEmpty()){
            Edge cur=queue.poll();
            if (visited[cur.to] && visited[cur.from]) continue;
            //visited
            int v=visited[cur.to] ?cur.from :cur.to;
            visited[v]=true;
            res+=cur.weight;
            for (Edge e:graph[v]){
                if (!visited[e.from]){
                    queue.add(e);
                }
            }
        }
        return res;
    }
}
