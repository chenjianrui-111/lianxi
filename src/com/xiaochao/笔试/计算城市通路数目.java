package com.xiaochao.笔试;

import java.util.*;

/**
 * 顶点0 到 1 2 3  [1,2,3]
 * 节点1到3        [3]
 * 节点2到3        [3]
 * 节点3到4        [4]
 * 节点4无         []
 * 输入[[1,2,3],[3],[3],[4],[]]
 * 输出 3
 */
//思路：深度优先搜索方式求出做有可能的路径，从0出发，栈记录路径上的点，每次遍历到点n-1，就将栈记录的路径加到答案中
//本题为有向无环图，不会遍历到重复的点，无需遍历当前点是否遍历过
public class 计算城市通路数目 {
    public int DagaPathNum(int[][] nodes){
        //存放二维节点
        List<List<Integer>> ans = new ArrayList<>();
        //堆栈 Deque
        Deque<Integer> stack = new ArrayDeque<>();
        //将指定的元素插入此双端队列的末尾
        stack.offerLast(0);
        dfs(nodes,0,nodes.length-1,ans,stack);
        return ans.size();
    }
    //深度优先探索
    public void dfs(int[][] graph,int x,int n,List<List<Integer>> ans,Deque<Integer> stack){
        if (x == n){
            ans.add(new ArrayList<>(stack));
            return;
        }
        for (int y : graph[x]) {
            stack.offerLast(y);
            dfs(graph, y, n, ans, stack);
            stack.pollLast();
        }
    }
}
//class Solution:
//
//        def DagPathNum(self,nodes :List[List[ int] ]) -> int:
//        n= len( nodes )
//        indegree =[0]*n
//        path_ num =[1] + [0]* (n - 1)
//        for i in range(n) :
//            for j in nodes[i] :
//                indegree[j] += 1
//        s=[0]
//        while s:
//        node = s. pop( )
//        for nxt in nodes[node] :
//        indegree[nxt] -= 1
//        path_ num nxt] +=path_ num[ node ]
//        if indegree [nxt ]== 0:
//              s. append( nxt )
//        return path_ num[ -1]

//class Solution {
//    public:
//    /**
//     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
//     *
//     * 计算dag 路径上起始到目的节点的路径数目
//     * @param nodes int整型vector<vector<>> 第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些结点（译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a ），若为空，就是没有下一个节点了。
//     * @return int整型
//     */
//    int DagPathNum(vector<vector<int> >& nodes) {
//        // write code here
//        int ans = 0;
//        int n = nodes.size();
//        vector<vector<int>> dp(n, vector<int>(n, 0));
//        for (int i = 0; i < n; ++i){
//            for (int d : nodes[i]){
//                dp[i][d] = 1;
//            }
//        }
//
//        for (int i = n - 2; i >= 0; --i){
//            for (int j = i + 1; j < n; ++j){
//                for (int k : nodes[i]){
//                    dp[i][j] += dp[k][j];
//                }
//
//            }
//        }
//        return dp[0][n - 1];
//    }
//};
