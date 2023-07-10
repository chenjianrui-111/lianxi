package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.Scanner;

public class 老张修路 {
    public int minDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // dp[i][j]表示道路穿过(i,j)格子时距离占用土地的距离之和最小值
        int[][] dp = new int[n][m];

        // 初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // 从左上角开始遍历，计算dp数组
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    if (i > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }
                }
            }
        }

        // 从右下角开始遍历，计算答案
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    if (i < n - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                    }
                    if (j < m - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                    }
                    ans += dp[i][j];
                }
            }
        }

        return ans;
    }
}

class KruskalMST {

    static int[] pre;
    static int n, m;

    static class Edge implements Comparable<Edge> {
        int s;
        int t;
        int mon;

        public Edge(int s, int t, int mon) {
            this.s = s;
            this.t = t;
            this.mon = mon;
        }

        public int compareTo(Edge e) {
            return Integer.compare(this.mon, e.mon);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        pre = new int[m + 100];
        for (int i = 0; i < m + 100; i++) {
            pre[i] = i;
        }

        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(edges);

        int ans = 0;
        int res = 0;

        for (int i = 0; i < m; i++) {
            int x = edges[i].s;
            int y = edges[i].t;
            if (cun(x, y) != 0) {
                ans += edges[i].mon;
                res++;
            }
        }

        System.out.println(ans);
    }

    static int Find(int x) {
        if (pre[x] == x) {
            return x;
        }
        return pre[x] = Find(pre[x]);
    }

    static int cun(int a, int b) {
        int fa = Find(a);
        int fb = Find(b);
        if (fa != fb) {
            pre[fa] = fb;
            return 1;
        }
        return 0;
    }
}
/*
3 3
1 1 2
2 3 3
885 513 817

*/
