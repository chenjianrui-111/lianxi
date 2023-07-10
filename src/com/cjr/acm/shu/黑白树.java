package com.cjr.acm.shu;

import java.util.Scanner;

/**
 * 一棵n个点的有根树，1号点为根，相邻的两个节点之间的距离为1。树上每个节点i对应一个值k[i]。每个点都有一个颜色，初始的时候所有点都是白色的。
 * 你需要通过一系列操作使得最终每个点变成黑色。每次操作需要选择一个节点i，i必须是白色的，然后i到根的链上（包括节点i与根）所有与节点i距离小于k[i]的点都会变黑，已经是黑的点保持为黑。问最少使用几次操作能把整棵树变黑。
 * 输入描述:
 * 第一行一个整数n (1 ≤ n ≤ 10^5)
 * 接下来n-1行，每行一个整数，依次为2号点到n号点父亲的编号。
 * 最后一行n个整数为k[i] (1 ≤ k[i] ≤ 10^5)
 * 样例解释:
 * 对节点3操作，导致节点2与节点3变黑
 * 对节点4操作，导致节点4变黑
 * 对节点1操作，导致节点1变黑
 * 输出描述:
 * 一个数表示最少操作次数
 * 示例1
 * 输入
 * 复制
 * 4
 * 1
 * 2
 * 1
 * 1 2 2 1
 * 输出
 * 复制
 * 3
 */
public class 黑白树 {
    static int n, cnt, ans;
    static int[] head;
    static edge[] e;
    static int[] k;
    static int[] f;

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = new int[n + 1];
        f = new int[n + 1];
        head = new int[n + 1];
        e = new edge[n + 1];
        for (int i = 0; i <= n; i++) {
            head[i] = -1;
            e[i] = new edge();
        }
        for (int i = 2; i <= n; i++) {
            int u = sc.nextInt();
            add_edge(u, i);
        }
        for (int i = 1; i <= n; i++) {
            k[i] = sc.nextInt();
        }
        dfs(1);
        System.out.println(ans);
    }

    public static void add_edge(int u, int v) {
        e[cnt].next = head[u];
        e[cnt].v = v;
        head[u] = cnt++;
    }
    public static void dfs(int u){
        for(int i=head[u];i!=-1;i=e[i].next){
            int v=e[i].v;
            dfs(v);
            k[u]=Math.max(k[u],k[v]-1);
            f[u]=Math.max(f[u],f[v]-1);
        }
        if(f[u]==0){
            ans++;
            f[u]=k[u];
        }
    }

    static class edge {
        int v;
        int next;
    }
}
