package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.Scanner;

// 5 6
// 1 2 3
// 2 3 4
// 3 4 5
// 4 5 6
// 1 5 1
// 2 4 2
// 0 98 49 25 114
// 最短路径输出
// 0 3 7 5 1
public class 黄金国历险 {
    static int N = 1010, M = 200020;
    // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
    static int[][] w= new int[N][N];
    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    static int[] dist =new int[N];
    //记录哪些点被更新过
    static boolean[] visited = new boolean[N];
    static int INF = 0x3f3f3f3f;
    static int n,k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        //初始化邻接矩阵
        for (int i = 1; i <= n ; i++) {
            int c = sc.nextInt();
            for (int j = 1; j <= n ; j++) {
                int d = sc.nextInt();
                w[c][d] = w[d][c] = sc.nextInt();
            }
        }
        //最短路
        黄金国历险.dijkstra();
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        if (ans > INF / 2){
            System.out.println("no");
        }else {
            System.out.println(ans);
        }
    }


        //存图
//        for (int[] t : ts) {
//            int u = t[0], v = t[1], c = t[2];
//            w[u][v] = c;
//        }

    static void dijkstra(){
        // 起始先将所有的点标记为「未更新」和「距离为正无穷」
        Arrays.fill(visited,false);
        Arrays.fill(dist,INF);
        //迭代n次
        for (int p = 1; p <= n ; p++) {
            // 每次找到「最短距离最小」且「未被更新」的点
            int t = -1;
            for (int i = 1; i <= n; i++) {
                if (!visited[i] && (t == -1 || dist[i] < dist[t])) t = i;
            }
            // 标记点 t 为已更新
            visited[t] = true;
            // 用点 t 的「最小距离」更新其他点
            for (int i = 1; i <= n; i++) {
                dist[i] = Math.min(dist[i], dist[t] + w[t][i]);
            }
        }
    }
}
