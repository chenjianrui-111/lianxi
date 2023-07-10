package com.xiaochao.图.拓扑排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一棵 树（即一个连通、无向、无环图），根节点是节点 0 ，这棵树由编号从 0 到 n - 1 的 n 个节点组成。用下标从 0 开始、
 * 长度为 n 的数组 parent 来表示这棵树，其中 parent[i] 是节点 i 的父节点，由于节点 0 是根节点，所以 parent[0] == -1 。
 * 另给你一个字符串 s ，长度也是 n ，其中 s[i] 表示分配给节点 i 的字符。
 * 请你找出路径上任意一对相邻节点都没有分配到相同字符的 最长路径 ，并返回该路径的长度。
 * 输入：parent = [-1,0,0,1,1,2], s = "abacbe"
 * 输出：3
 * 解释：任意一对相邻节点字符都不同的最长路径是：0 -> 1 -> 3 。该路径的长度是 3 ，所以返回 3 。
 * 可以证明不存在满足上述条件且比 3 更长的路径。
 */

/**
 * 如果没有相邻节点的限制，那么本题求的就是树的直径上的点的个数，见 1245. 树的直径。
 * 考虑用树形 DP 求直径。枚举子树 x 的所有子树 y，维护从 xx 出发的最长路径 maxLen，那么可以更新答案为从 y 出发的最长路径加上 maxLen，再加上 1（边 x−y），即合并从 x 出发的两条路径。递归结束时返回 maxLen。
 * 对于本题的限制，我们可以在从子树 y 转移过来时，仅考虑从满足 s[x]
 *  !=s[y] 的子树 y 转移过来，所以对上述做法加个 if 判断就行了。
 * 由于本题求的是点的个数，所以答案为最长路径的长度加一。
 */
public class 相邻字符不同的最长路径 {
    List<Integer>[] g;
    String s;
    int ans;

    public int longestPath(int[] parent, String s) {
        this.s = s;
        int n = parent.length;
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int i = 1; i < n; i++) g[parent[i]].add(i);

        dfs(0);
        return ans + 1;
    }

    int dfs(int x) {
        int maxLen = 0;
        for (int y : g[x]) {
            int len = dfs(y) + 1;
            if (s.charAt(y) != s.charAt(x)) {
                ans = Math.max(ans, maxLen + len);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

}
class Soultion{
    // 与124题非常类似,这类题属于不过根节点的路径问题,采用求两边最优再进行合并的解法
    // 存储节点i的孩子节点,i为父节点
    List<Integer>[] children;
    // 节点个数
    int n;
    String s;
    // 根节点编号默认为0
    final int root = 0;
    // 结果
    int res = 0;

    public int longestPath(int[] parent, String _s) {
        // 初始化各类成员变量
        s = _s;
        n = s.length();
        children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            // parent数组的意义:索引i->子节点;parent[i]->i的父节点
            // 父节点
            int father = parent[i];
            // father的孩纸节点时此时的索引
            children[father].add(i);
        }
        // 开启dfs
        dfs(root);
        return res;
    }

    // 返回以编号root为起点符合条件的最长路径长度
    // 这个以root为起点的路径只向root的孩子节点方向进行延伸,而不考虑root父节点的方向的路径*
    // 因为*的情况在执行dfs(root)时已经进行计算,不用重复计算
    private int dfs(int root) {
        // 越过叶子返回0,空节点不组成任何路径
        if (root >= n) return 0;
        // max1和max2为root子节点为起点的路径中最长的两条,这两部分最后会直接影响res
        int max1 = 0, max2 = 0;
        // 遍历root的每个叶子节点并求出其符合条件的最长路径
        for (int child : children[root]) {
            // 当前子节点child的最长合法路径长度
            int childLen = dfs(child);
            // 更新max1与max2
            if (childLen > max1 && s.charAt(child) != s.charAt(root)) {
                // childLen最大且能与root拼接
                max2 = max1;
                max1 = childLen;
            } else if (childLen > max2 && s.charAt(child) != s.charAt(root)) {
                // childLen次大且能与root拼接
                max2 = childLen;
            }
        }
        // 将经过root的合法路径长度更新进去res->左边+root+右边=经过root的合法路径长度(root不一定为起点)
        res = Math.max(res, max1 + max2 + 1);
        // 最后返回以root为起点的合法路径最长长度,显然为最长的那一条长度max1+root本身长度1
        return max1 + 1;
    }

}
