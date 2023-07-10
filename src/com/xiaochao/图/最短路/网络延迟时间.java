package com.xiaochao.图.最短路;

/**
 * 有 n 个网络节点，标记为 1 到 n。 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节
 * 点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节
 * 点收到信号，返回 -1 。
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * 提示：
 * • 1 <= k <= n <= 100
 * • 1 <= times.length <= 6000
 * • times[i].length == 3
 * • 1 <= ui, vi <= n
 * • ui != vi
 * • 0 <= wi <= 100
 * • 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 */

/**
 * 分析
 * 根据题意，首先 n 的数据范围只有 100，m 的数据范围为 6000，使用「邻接表」或「邻接矩
 * 阵」来存图都可以。
 * 同时求的是「从 k 点出发，所有点都被访问到的最短时间」，将问题转换一下其实就是求「从 k 点出发，到其他点 x 的最短距离的最大值」。
 */

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 邻接表存图（链式向前星存图）
 * int[] he = new int[N], e = new int[M], ne = new int[M], w = new int[M];
 * int idx;
 * void add(int a, int b, int c) { e[idx] = b;
 * ne[idx] = he[a];
 * he[a] = idx; w[idx] = c;idx++; }
 * 首先 idx 是用来对边进行编号的，然后对存图用到的几个数组作简单解释： • he 数组：存储是某个节点所对应的边的集合（链表）的头结点；
 * • e 数组：由于访问某一条边指向的节点；
 * • ne 数组：由于是以链表的形式进行存边，该数组就是用于找到下一条边；
 * • w 数组：用于记录某条边的权重为多少。
 * 因此当我们想要遍历所有由 a 点发出的边时，可以使用如下方式：
 * for (int i = he[a]; i != -1; i = ne[i]) {
 * int b = e[i], c = w[i]; // 存在由 a 指向 b 的边，权重为 c
 * }
 */

public class 网络延迟时间 {
    int N = 110,M = 6010;
    int[][] w = new int[N][N];
    int[] dist =new int[N];
    boolean[] vis =new boolean[N];
    int INF = 0x3f3f3f3f;
    int n,k;
    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n = _n;k = _k;
        //初始化邻接矩阵
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n ; j++) {
                w[i][j] = w[j][i] = i == j ? 0 :INF;
            }
        }
        //存图
        for (int[] t : ts) {
            int u = t[0],v = t[1],c = t[2];
            w[u][v] = c;
        }
        //最短路
        dijkstra();
        //遍历答案
        int ans = 0;
        for (int i = 1; i <= n ; i++) {
            ans = Math.max(ans,dist[i]);
        }
        return ans > INF/2 ? -1 :ans;
    }
    void dijkstra(){
        //现将所有的点都标记为未更新和距离为正无穷
        Arrays.fill(vis,false);
        Arrays.fill(dist,INF);
        //只有起点最短距离为 0
        dist[k] = 0;
        //迭代n次
        for (int p = 0; p <= n ; p++) {
            //每次找到 最短距离最小 和 未被更新 的点 t
            int t = -1;
            for (int i = 1; i <= n ; i++) {
                if (!vis[i] && (t == -1 || dist[i] < dist[t]))
                    t = i;
            }
            //标记 t 为已经更新
            vis[t] = true;
            //用 t 的最小距离去更新其它点
            for (int i = 1; i <= n ; i++) {
                dist[i] = Math.min(dist[i],dist[t] + w[t][i]);
            }
        }
    }
}
//• 时间复杂度：O(n2) • 空间复杂度：O(n2)
/**
 * 堆优化 Dijkstra（邻接表）
 * 由于边数据范围不算大，我们还可以使用复杂度为 O(m log n) 的堆优化 Dijkstra 算法进行求解。
 * 堆优化 Dijkstra 算法与朴素 Dijkstra 都是「单源最短路」算法。
 * 跑一遍堆优化 Dijkstra 算法求最短路，再从所有最短路中取 max 即是「从 k 点出发，到其他
 * 点 x 的最短距离的最大值」。
 * 此时算法复杂度为 O(m log n)，可以过
 */
class Solution{
    int N =110 ,M = 6010;
    //邻接表
    int[] he = new int[N],e = new int[M],ne = new int[M],w = new int[M];
    //dist[x] = y 代表[源点/起点] 到 x 的最短距离 为 y
    int [] dist =new int[N];
    boolean[] vis = new boolean[N];
    int n,k,idx;
    int INF = 0x3f3f3f3f;
    void add(int a,int b,int c){
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx;
        w[idx] = c;
        idx++;
    }
    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n  =_n; k  =_k;
        Arrays.fill(he,-1);
        //存图
        for (int[] t : ts) {
            int u = t[0] , v = t[1], c =t[2];
            add(u,v,c);
        }
        //最短路
        dijkstra();
        //遍历答案
        int ans = 0;
        for (int i = 1; i <= n ; i++) {
            ans = Math.max(ans,dist[i]);
        }
        return ans > INF/2 ? -1 : ans;
    }
    void dijkstra(){
        Arrays.fill(vis,false);
        Arrays.fill(dist,INF);
        dist[k] = 0;
        // 使用「优先队列」存储所有可用于更新的点
        // 以 (点编号, 到起点的距离) 进行存储，优先弹出「最短距离」较小的点
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) ->a[1] - b[1]);
        q.add(new int[]{k,0});
        while (!q.isEmpty()){
            // 每次从「优先队列」中弹出
            int[] poll = q.poll();
            int id = poll[0] , step = poll[1];
            //如果弹出的点被标记为 已更新 则跳过
            if (vis[id]) continue;
            // 标记该点「已更新」，并使用该点更新其他点的「最短距离」
            vis[id] = true;
            for (int i = he[id]; i != -1 ; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[id] + w[i]){
                    dist[j] = dist[id] + w[i];
                    q.add(new int[]{j,dist[j]});
                }
            }
        }
    }
}
//• 时间复杂度：O(m log n + n) • 空间复杂度：O(m)
