package com.xiaochao.DP.树形DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1  条无向边的 edges 列表（每一个边都是一对标签），
 * 其中 edges[i] = [ai,bi] 表示树中节点 ai  和 bi 之间存在一条无向边。
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 *     1
 *   /| \
 *  0 2 3
 * 输入：n = 4, edges = [[1,0],[1,2],[1,3]]
 * 输出：[1]
 * 解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。
 */

/**
 * 树形DP
 * 树形 DP 问题通常将问题根据「方向」进行划分。
 * 对于当前处理到的节点 u 而言，我们根据是否考虑「从 fa 到 u 的出边」将其分为「往上」和「往下」两个方向。
 * 假设我们可以通过 DFS 预处理出 f 数组和 g 数组，其中 f[u] 代表在以 0 号点为根节点的树中，以 u 节点为子树根节点时，往下的最大高度；
 * g[u] 代表在以 0 号点为根节点的树中，以 u 节点为子节点时，往上的最大高度。那么最终以 u 为根节点的最大高度为 max(f[u],g[u])。
 * 其中 f[u] 只需要简单的 DFS 即可处理出来，但对于 g[u] 而言，其同样包含「往上」和「往下」两部分，对于往上的部分 max(g[u],g[fa]+1) ；
 * 而对于往下的部分，我们需要考虑「fa 节点往下的最大值f[fa] 」是否由 u 节点参与而来进行分情况讨论，
 * 如果f[fa]  本身不由 u 参与，那么 g[u] 应当是 fa 节点往下的最大值 +1 而来
 * （+1 代表加上 fa 到 u 的边）；如果本身 fa 往下的最大值由 u 节点参与，此时应当使用 fa 往下的次大值 +1 来更新 。
 * 因此我们需要对 f 数组进行拆分，拆分为记录「最大值的 f1 数组」和记录「次大值的 f2 数组（注意这里的次大值是非严格的次大值）」，
 * 同时使用 p 数组记录下取得 f1[u] 时 u 的子节点 j 为何值。
 * 另外实现上，在处理「往上」方向的 DFS 时，为避免对 fa 节点为空的处理，我们可以将「用 fa 来更新 u」调整为「用 u 来更新 j」。
 */
public class 最小高度树 {
    int N = 20010 ,M = N * 2,idx = 0;
    int[] he = new int[N],e = new int[M],ne = new int[M];
    int[] f1 = new int[N] ,f2 = new int[N],g = new int[N], p = new int[N];
    void add(int a,int b){
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Arrays.fill(he,-1);
        for (int[] e : edges){
            int a = e[0] , b = e[1];
            add(a,b);add(b,a);
        }
        dfs1(0,-1);
        dfs2(0,-1);
        List<Integer> ans = new ArrayList<>();
        int min = n;
        for (int i = 0; i < n ; i++) {
            int cur = Math.max(f1[i],g[i]);
            if (cur < min){
                min = cur;
                ans.clear();
                ans.add(i);
            }else if (cur == min){
                ans.add(i);
            }
        }
        return ans;
    }
    int dfs1(int u,int fa){
        for (int i = he[u]; i != -1 ; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            int sub = dfs1(j,u) + 1;
            if (sub > f1[u]){
                f2[u] = f1[u];
                f1[u] = sub;
                p[u] = j;
            }else if (sub > f2[u]){
                f2[u] = sub;
            }
        }
        return f1[u];
    }
    void dfs2(int u,int fa){
        for (int i = he[u]; i != -1 ; i=ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            if (p[u] != j) g[j] = Math.max(g[j],f1[u]+1);
            else g[j] = Math.max(g[j],g[u]+1);
            dfs2(j,u);
        }
    }
}
//时间复杂度：O(N)
//空间复杂度：O(N)
