package com.leixing.递归迭代.DFS;

import java.util.*;

/**
 * 给你一个 n 个节点的树（也就是一个无环连通无向图），节点编号从 0 到 n - 1 ，且恰好有 n - 1 条边，每个节点有一个值。树的 根节点 为 0 号点。
 * 给你一个整数数组 nums 和一个二维数组 edges 来表示这棵树。nums[i] 表示第 i 个点的值，edges[j] = [uj, vj] 表示节点 uj 和节点 vj 在树中有一条边。
 * 当 gcd(x, y) == 1 ，我们称两个数 x 和 y 是 互质的 ，其中 gcd(x, y) 是 x 和 y 的 最大公约数 。
 * 从节点 i 到 根 最短路径上的点都是节点 i 的祖先节点。一个节点 不是 它自己的祖先节点。
 * 请你返回一个大小为 n 的数组 ans ，其中 ans[i]是离节点 i 最近的祖先节点且满足 nums[i] 和 nums[ans[i]] 是 互质的 ，如果不存在这样的祖先节点，ans[i] 为 -1 。
 * 基本思路
 * 题目描述很长，但其实就是说每个节点从下往上找，找到最近的「与其互质」的节点。
 * 数据范围是 10^5如果每个节点都直接往上找最近「互质」祖宗节点的话，当树为线性时，复杂度是 O(n^2)会超时。
 * 因此我们要利用 nums[i] 范围只有 50 的特性。
 * 我们可以先预处理除 [1, 50] 范围内的每个数，求出他们互质的数有哪些，存到一个字典里。
 * 那么对于某个节点而言，假设节点的值为 x ，所在层数为 y。
 * 那么问题转化为求与 x 互质的数有哪些，最近的在哪一层。
 * 用 dep[x] 表示距离值为 x 的节点最近的层是多少；pos[x] 代表具体的节点编号。
 */
public class 互质树 {
        int[] ans;
        Map<Integer, List<Integer>> map = new HashMap<>(); // 边映射
        Map<Integer, List<Integer>> val = new HashMap<>(); // 互质数字典
        int[] dep;
        int[] pos = new int[52];
        public int[] getCoprimes(int[] nums, int[][] edges) {
            int n = nums.length;
            ans = new int[n];
            dep = new int[n];
            Arrays.fill(ans, - 1);
            Arrays.fill(pos, -1);

            for (int[] edge : edges) {
                int a = edge[0], b = edge[1];
                List<Integer> alist = map.getOrDefault(a, new ArrayList<>());
                alist.add(b);
                map.put(a, alist);
                List<Integer> blist = map.getOrDefault(b, new ArrayList<>());
                blist.add(a);
                map.put(b, blist);
            }

            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    if (gcd(i, j) == 1) {
                        List<Integer> list = val.getOrDefault(i, new ArrayList<>());
                        list.add(j);
                        val.put(i, list);
                    }
                }
            }

            dfs(nums, 0, -1);
            return ans;
        }
        void dfs(int[] nums, int u, int form) {
            int t = nums[u];
            for (int v : val.get(t)) {
                if (pos[v] == -1) continue;
                if (ans[u] == -1 || dep[ans[u]] < dep[pos[v]]) ans[u] = pos[v];
            }
            int p = pos[t];
            pos[t] = u;

            for (int i : map.get(u)) {
                if (i == form) continue;
                dep[i] = dep[u] + 1;
                dfs(nums, i, u);
            }
            pos[t] = p;
        }
        int gcd(int a, int b) {
            if (b == 0) return a;
            if (a == 0) return b;
            return gcd(b, a % b);
        }
}
//时间复杂度：对于每个节点而言，会检查与其数值互质的数有哪些，在哪层。最坏情况下会检查 50 个互质数（当前数值为 1）。复杂度为 O(n)
//空间复杂度：O(n)
