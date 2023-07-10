package com.xiaochao.图.拓扑排序;

import java.util.*;

/**
 * 有一个有 n 个节点的有向图，节点按 0 到 n - 1 编号。图由一个 索引从 0 开始 的 2D 整数数组 graph表示， graph[i]是与节点 i 相邻的节点的整数数组，这意味着从节点 i 到 graph[i]中的每个节点都有一条边。
 * 如果一个节点没有连出的有向边，则它是 终端节点 。如果没有出边，则节点为终端节点。如果从该节点开始的所有可能路径都通向 终端节点 ，则该节点为 安全节点 。
 * 返回一个由图中所有 安全节点 组成的数组作为答案。答案数组中的元素应当按 升序 排列。
 * 入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * 输出：[2,4,5,6]
 * 解释：示意图如上。
 * 节点 5 和节点 6 是终端节点，因为它们都没有出边。
 * 从节点 2、4、5 和 6 开始的所有路径都指向节点 5 或 6 。
 * 提示：
 * n == graph.length
 * 1 <= n <= 10^4 • 0 <= graph[i].length <= n
 * graph[i] 按严格递增顺序排列。
 * 图中可能包含自环。
 * 图中边的数目在范围 [1, 4 * 10^4] 内。
 */

/**
 * 拓扑排序
 * 因此，对于有向图的拓扑排序，我们可以使用如下思路输出拓扑序（ BFS 方式）：
 * 1. 起始时，将所有入度为 0 的节点进行入队（入度为 0，说明没有边指向这些节点，
 * 将它们放到拓扑排序的首部，不会违反拓扑序定义）；
 * 2. 从队列中进行节点出队操作，出队序列就是对应我们输出的拓扑序。
 * 对于当前弹出的节点 x，遍历 x 的所有出度，即遍历所有由 x 直接指向的节点 y， 对 y 做入度减一操作（因为 x 节点已经从队列中弹出，被添加到拓扑序中，等价于
 * 从 x 节点从有向图中被移除，相应的由 x 发出的边也应当被删除，带来的影响是与
 * x 相连的节点 y 的入度减一）；
 * 3. 对 y 进行入度减一之后，检查 y 的入度是否为 0，如果为 0 则将 y 入队（当 y 的
 * 入度为 0，说明有向图中在 y 前面的所有的节点均被添加到拓扑序中，此时 y 可以
 * 作为拓扑序的某个片段的首部被添加，而不是违反拓扑序的定义）；
 * 4. 循环流程 2、3 直到队列为空
 */

/**
 * he 数组：存储是某个节点所对应的边的集合（链表）的头结点；
 * e  数组：由于访问某一条边指向的节点；
 * ne 数组：由于是以链表的形式进行存边，该数组就是用于找到下一条边；
 * w  数组：用于记录某条边的权重为多少。
 */
//反向拓扑排序
public class 找到最终的安全状态 {
    int N = (int) (1e4+10), M = 4 * N;
    int idx;
    int[] he = new int[N] , e = new int[M],ne = new int[M];
    int[] cnts = new int[N];
    void add(int a,int b){
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n =graph.length;
        //存反向图
        Arrays.fill(he,-1);
        for (int i = 0; i < n ; i++) {
            for (int j : graph[i]) {
                add(j,i);
                cnts[i]++;
            }
        }
        //BFS求反向图，拓扑排序
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n ; i++) {
            if (cnts[i] == 0) d.addLast(i);
        }
        while (!d.isEmpty()){
            int poll = d.pollFirst();
            for (int i = he[poll]; i != -1 ; i = ne[i]) {
                int j = e[i];
                if (--cnts[j] == 0) d.addLast(j);
            }
        }
        // 遍历答案：如果某个节点出现在拓扑序列，说明其进入过队列，说明其入度为 0
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (cnts[i] == 0)
                ans.add(i);
        }
        return ans;
    }
}
//时间复杂度：O(n + m)
//空间复杂度：O(n + m)
