package com.xiaochao.图.最短路;

import java.util.*;

/**
 * Tag : 「图论最短路」、「线性 DP」 现有一个加权无向连通图。给你一个正整数 n ，表示图中有 n 个节点，并按从 1 到 n 给节点编
 * 号；另给你一个数组 edges ，其中每个 edges[i] = [ui, vi, weighti] 表示存在一条位于节点 ui 和
 * vi 之间的边，这条边的权重为 weighti 。 从节点 start 出发到节点 end 的路径是一个形如 [z0, z1, z2, …, zk] 的节点序列，满足 z0 = start
 * 、zk = end 且在所有符合 0 <= i <= k-1 的节点 zi 和 zi+1 之间存在一条边。
 * 路径的距离定义为这条路径上所有边的权重总和。用 distanceToLastNode(x) 表示节点 n 和 x
 * 之间路径的最短距离。受限路径 为满足 distanceToLastNode(zi) > distanceToLastNode(zi+1)
 * 的一条路径，其中 0 <= i <= k-1 。
 * 返回从节点 1 出发到节点 n 的 受限路径数 。由于数字可能很大，请返回对 109 + 7 取余 的结果。
 * 输入：n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
 * 输出：3
 * 解释：每个圆包含黑色的节点编号和蓝色的 distanceToLastNode 值。三条受限路径分别是：
 * 1) 1 --> 2 --> 5
 * 2) 1 --> 2 --> 3 --> 5
 * 3) 1 --> 3 --> 5
 * 提示：
 * 1 <= n <= 2 * 104
 * n - 1 <= edges.length <= 4 * 104
 * edges[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 1 <= weighti <= 105
 * 任意两个节点之间至多存在一条边
 * 任意两个节点之间至少存在一条路径
 */
public class 从第一个节点出发到最后一个节点的受限路径数 {
    int MOD = (int) (1e9 + 7);
    public int countRestrictedPaths(int n, int[][] edges) {
        //预处理所有的边权
        // 预处理所有的边权。 a b w -> a : { b : w } + b : { a : w }
        Map<Integer,Map<Integer,Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0] , b = edge[1] ,w = edge[2];
            Map<Integer,Integer> am =map.getOrDefault(a,new HashMap<Integer, Integer>());
            am.put(b,w);
            map.put(a,am);
            Map<Integer, Integer> bm = map.getOrDefault(b, new HashMap<Integer, Integer>());
            bm.put(a, w);
            map.put(b, bm);
        }
        // 堆优化 Dijkstra：求 每个点 到 第n个点 的最短路
        int[] dist =new int[n+1];
        boolean[] st =new boolean[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[n] = 0;
        Queue<int[]> q = new PriorityQueue<int[]>((a,b) -> a[1] - b[1]);// 点编号，点距离。根据
        q.add(new int[]{n,0});
        while (!q.isEmpty()){
            int[] e = q.poll();
            int idx = e[0],cur = e[1];
            if (st[idx]) continue;
            st[idx] = true;
            Map<Integer,Integer> mm = map.get(idx);
            if (mm == null) continue;
            for (Integer i : mm.keySet()) {
                dist[i] = Math.min(dist[i],dist[idx] + mm.get(i));
                q.add(new int[]{i,dist[i]});
            }
        }
        //dp过程
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) arr[i] = new int[]{i + 1, dist[i + 1]}; // 点编号，点距离
        Arrays.sort(arr, (a, b) -> a[1] - b[1]); // 根据点距离从小到大排序
        // 定义 f(i) 为从第 i 个点到结尾的受限路径数量
        // 从 f[n] 递推到 f[1]
        int[] f = new int[n + 1];
        f[n] = 1;
        for (int i = 0; i < n; i++) {
            int idx = arr[i][0], cur = arr[i][1];
            Map<Integer, Integer> mm = map.get(idx);
            if (mm == null) continue;
            for (int next : mm.keySet()) {
                if (cur > dist[next]) {
                    f[idx] += f[next];
                    f[idx] %= MOD;
                }
            }
           // 第 1 个节点不一定是距离第 n 个节点最远的点，但我们只需要 f[1]，可以直接跳出循环
            if (idx == 1) break;
        }
        return f[1];
    }
}
//• 时间复杂度：求最短路的复杂度为 O(m log n)，DP 过程坏情况下要扫完所有的
//边，复杂度为 O(m)。整体复杂度为 O(m log n) • 空间复杂度：O(n + m)
/**
 bfs算最短距离+优先队列计算总数
 1.计算最短距离，这个不多赘述，用bfs遍历一遍解决
 2.得出所有最短路径后，对最短路径进行排序，从最短路径短的到最短路径长的依次计算，到终点的受限距离。
 因为最短路径较大的节点到达终点可以通过两种方式：
 （1）直接到达终点
 （2）到达最短路径较小的节点再到终点
 */
class Solution4{
    private static int MOD = (int)Math.pow(10, 9)+7;
    public int countRestrictedPaths(int n, int[][] edges) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.putIfAbsent(edge[0], new HashMap<>());
            map.putIfAbsent(edge[1], new HashMap<>());
            map.get(edge[0]).put(edge[1], edge[2]);
            map.get(edge[1]).put(edge[0], edge[2]);
        }
        int[] minDis = new int[n+1];
        Arrays.fill(minDis, Integer.MAX_VALUE);
        minDis[n] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] current = queue.poll();
                int currentId = current[0];
                int currentDis = current[1];
                Map<Integer, Integer> next = map.get(currentId);
                for (Map.Entry<Integer, Integer> entry : next.entrySet()) {
                    int nextId = entry.getKey();
                    int currentToNext = entry.getValue();
                    int nextDis = currentDis+currentToNext;
                    if (nextDis < minDis[nextId]) {
                        minDis[nextId] = nextDis;
                        queue.add(new int[]{nextId, nextDis});
                    }
                }
            }
        }
        int[] result = new int[n+1];
        result[n] = 1;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for (int i = 1; i < minDis.length; i++) {
            priorityQueue.add(new int[]{i, minDis[i]});
        }
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentId = current[0];
            int currentMinDis = minDis[currentId];
            Map<Integer, Integer> next = map.get(currentId);
            for (Map.Entry<Integer, Integer> entry : next.entrySet()) {
                int nextId = entry.getKey();
                int nextMinDis = minDis[nextId];
                if (nextMinDis < currentMinDis) {
                    result[currentId] += result[nextId];
                    result[currentId] %= MOD;
                }
            }
        }
        return result[1];
    }
}
