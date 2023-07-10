package com.xiaochao.笔试;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 小红拿到一颗有根树，根节点号为1号节点。已知i节点权值为ai.小红定义每个节点为根的子节点权值为该子树所有节点权值
 * 乘积的因子数量。小红想知道，所有子树权值之和是多少？
 * 答案请对10^9+7取模（注意为权值取模）
 * 输入描述:
 * 第一行输入一个正整数n,代表节点的数量
 * 第二行的输入n个正整数ai,代表节点的权值
 * 接下来的n-1行，每行输入两个正整数u和v，代表u和v有一条边相连
 * 1<=n,ai<=10^5
 * 输出
 * 所有子树的权值之和
 * 实例1
 * 3
 * 1 2 3
 * 1 2
 * 1 3
 * 输出
 * 8
 * 说明
 * 1为根的子树，所有节点乘积为6，因子数量是4
 * 2为根的子树，所有节点乘积为2，因子数量为2
 * 3为根的子树，所有节点乘积为3，因子数量为2
 */
class 小红的子树权值之和 {
    static long[] inv;
    static ArrayList<Integer>[] v;
    static int[] sz, son, c, res, a, pp, minprime;
    static long ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        v = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            v[i] = new ArrayList<>();
        }
        sz = new int[n + 1];
        son = new int[n + 1];
        c = new int[200005];
        res = new int[n + 1];
        a = new int[n + 1];
        pp = new int[200005];
        minprime = new int[200005];
        euler(200000);
        inv = new long[200005];
        inv[0] = 1;
        for (int i = 1; i <= 200000; i++) {
            inv[i] = qpow(i, mod - 2);
        }

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 1; i <= n - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            v[x].add(y);
            v[y].add(x);
        }

        dfs(1, 1);
        dfs(1, 1, 0);

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = (ans + res[i]) % mod;
        }
        System.out.println(ans);
    }

    static void euler(int n) {
        int c = 0;
        for (int i = 2; i <= n; i++) {
            if (minprime[i] == 0) {
                pp[++c] = i;
                minprime[i] = i;
            }
            for (int j = 1; j <= c && i * pp[j] <= n; j++) {
                minprime[i * pp[j]] = pp[j];
                if (i % pp[j] == 0)
                    break;
            }
        }
    }

    static long qpow(long a, int b) {
        long ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans * a) % mod;
            }
            b >>= 1;
            a = (a * a) % mod;
        }
        return ans;
    }

    static void work(int x, int f) {
        while (x != 1) {
            int t = minprime[x];
            ans = (ans * inv[c[t] + 1]) % mod;
            c[t] += f;
            ans = (ans * (c[t] + 1)) % mod;
            x /= t;
        }
    }

    static void dfs(int u, int fa) {
        sz[u] = 1;
        for (int e : v[u]) {
            if (e == fa)
                continue;
            dfs(e, u);
            sz[u] += sz[e];
            if (sz[son[u]] < sz[e])
                son[u] = e;
        }
    }

    static void cal(int u, int fa, int f) {
        work(a[u], f);
        for (int e : v[u]) {
            if (e == fa)
                continue;
            cal(e, u, f);
        }
    }

    static void dfs(int u, int fa, int del) {
        for (int e : v[u]) {
            if (e == fa || e == son[u])
                continue;
            dfs(e, u, 1);
        }
        if (son[u] != 0)
            dfs(son[u], u, 0);
        work(a[u], 1);
        for (int e : v[u]) {
            if (e == fa || e == son[u])
                continue;
            cal(e, u, 1);
        }
        res[u] = (int) ans;
        if (del != 0)
            cal(u, fa, -1);
    }

    static final int mod = 1000000007;
}
