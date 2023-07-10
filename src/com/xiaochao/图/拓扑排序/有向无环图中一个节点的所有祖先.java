package com.xiaochao.图.拓扑排序;

import java.util.*;

/**
 * 给你一个正整数 n ，它表示一个 有向无环图 中节点的数目，节点编号为 0 到 n - 1 （包括两者）。
 * 给你一个二维整数数组 edges ，其中 edges[i] = [fromi, toi] 表示图中一条从 fromi 到 toi 的单向边。
 * 请你返回一个数组 answer，其中 answer[i]是第 i 个节点的所有 祖先 ，这些祖先节点 升序 排序。
 * 如果 u 通过一系列边，能够到达 v ，那么我们称节点 u 是节点 v 的 祖先 节点。
 * 输入：n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
 * 输出：[[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
 * 解释：
 * 上图为输入所对应的图。
 * - 节点 0 ，1 和 2 没有任何祖先。
 * - 节点 3 有 2 个祖先 0 和 1 。
 * - 节点 4 有 2 个祖先 0 和 2 。
 * - 节点 5 有 3 个祖先 0 ，1 和 3 。
 * - 节点 6 有 5 个祖先 0 ，1 ，2 ，3 和 4 。
 * - 节点 7 有 4 个祖先 0 ，1 ，2 和 3 。
 */

/**
 * 构建两个线性表，一个作为返回值，另一个使用TreeSet，可以在去重的同时进行元素的排序
 * 构建邻接矩阵(邻接表会更好)，以及入度数组
 * 遍历edges，分别构建图中的边的关系以及各个节点的入度
 * 将图中入度为0的节点入队列，同时“填充好”我们最初构建的两个线性表
 * 分别对整个入度数组进行遍历，若入度为0，则加入队列，同时将该父节点以及该父节点的所有父节点加入线性表中
 * 将最终答案汇整到返回值ans中
 */
public class 有向无环图中一个节点的所有祖先 {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        //构建两个线性表，一个作为返回值，另一个使用`TreeSet`，可以在去重的同时进行元素的排序
        List<List<Integer>> ans = new ArrayList<>();
        List<Set<Integer>> demo = new ArrayList<>();

        //构建邻阶矩阵(邻接表会更好)，以及入度数组
        int[] system = new int[n];
        int[][] grid = new int[n][n];

        //遍历`edges`，分别构建图中的边的关系以及各个节点的入度
        for (int[] edge : edges) {
            system[edge[1]]++;
            grid[edge[0]][edge[1]] = 1;
        }

        //将图中入度为0的节点入队列，同时“填充好”我们最初构建的两个线性表
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (system[i] == 0) {
                queue.offer(i);
                system[i]--;
            }
            ans.add(new ArrayList<>());
            demo.add(new TreeSet<>());
        }

        //分别对整个入度数组进行遍历，若入度为0，则加入队列，同时将该父节点以及该父节点的所有父节点加入线性表中
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int idx = queue.poll();
                for (int i = 0; i < n; i++) {
                    if (grid[idx][i] == 1) {
                        system[i]--;
                        demo.get(i).add(idx);
                        demo.get(i).addAll(demo.get(idx));
                    }
                    if (system[i] == 0) {
                        queue.offer(i);
                        system[i]--;
                    }
                }
            }
        }

        //将最终答案汇整到返回值`ans`中
        for (int i = 0; i < n; i++) {
            ans.get(i).addAll(demo.get(i));
        }

        return ans;
    }
}
