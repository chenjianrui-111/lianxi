package com.xiaochao.图;

import java.util.*;

public class 网络延迟demo {
    /**
     * 邻接矩阵
     * 存储稠密图
     * 当边得数量接近点的数量的平方 m = n^2
     * // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
     * int[][] w = new int[N][N];
     *
     * // 加边操作
     * void add(int a, int b, int c) {
     *     w[a][b] = c;
     * }
     */
    /**
     * 邻接表
     * 适用于边数较少的「稀疏图」使用，当边数量接近点的数量，即 m = n 时，可定义为「稀疏图」。
     * int[] he = new int[N], e = new int[M], ne = new int[M], w = new int[M];
     * int idx;
     *
     * void add(int a, int b, int c) {
     *     e[idx] = b;
     *     ne[idx] = he[a];
     *     he[a] = idx;
     *     w[idx] = c;
     *     idx++;
     * }
     * 首先 idx 是用来对边进行编号的，然后对存图用到的几个数组作简单解释：
     *
     * he 数组：存储是某个节点所对应的边的集合（链表）的头结点；
     * e  数组：由于访问某一条边指向的节点；
     * ne 数组：由于是以链表的形式进行存边，该数组就是用于找到下一条边；
     * w  数组：用于记录某条边的权重为多少。
     * 因此当我们想要遍历所有由 a 点发出的边时，可以使用如下方式：
     * for (int i = he[a]; i != -1; i = ne[i]) {
     *     int b = e[i], c = w[i]; // 存在由 a 指向 b 的边，权重为 c
     * }
     */
    /**
     * 类
     * 这是一种最简单，但是相比上述两种存图方式，使用得较少的存图方式。
     * 只有当我们需要确保某个操作复杂度严格为  时，才会考虑使用。
     * 具体的，我们建立一个类来记录有向边信息：
     * class Edge {
     *     // 代表从 a 到 b 有一条权重为 c 的边
     *     int a, b, c;
     *     Edge(int _a, int _b, int _c) {
     *         a = _a; b = _b; c = _c;
     *     }
     * }
     * 通常我们会使用 List 存起所有的边对象，并在需要遍历所有边的时候，进行遍历：
     * List<Edge> es = new ArrayList<>();
     * ...
     * for (Edge e : es) {
     *     ...
     * }
     */

}

/**
 *  Floyd（邻接矩阵）
 *    根据「基本分析」，我们可以使用复杂度为 O(N3) 的「多源汇最短路」算法 Floyd 算法进行求解，同时使用「邻接矩阵」来进行存图。
 *    此时计算量约为 10^6 ，可以过。
 *    跑一遍 Floyd，可以得到「从任意起点出发，到达任意起点的最短距离」。
 *    然后从所有 w[k][x] 中取 max 即是「从 k 点出发，到其他点 x 的最短距离的最大值」。
 */
//有 n 个网络节点，标记为 1 到 n。
//给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
//现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
//提示：
//1 <= k <= n <= 100
//1 <= times.length <= 6000
//times[i].length == 3
//1 <= ui, vi <= n
//ui != vi
//0 <= wi <= 100
//所有 (ui, vi) 对都 互不相同（即，不含重复边）
class Solution{
    int N= 110 ,M = 6010;
    int[][] w = new int[N][N];
    int INF = 0x3f3f3f3f;
    int n, k;
    public int networkDelayTime(int[][] ts, int _n, int _k) {
        //初始化邻接矩阵
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=n ; j++) {
                w[i][j] = w[j][i] = i == j ? 0 :INF;
            }
        }
        //存图
        for (int[] t : ts) {
            int u = t[0], v =t[1], c =t[2];
            w[u][v] = c;
        }
        //最短路
        floyd();
        //遍历答案
        int ans = 0;
        for (int i = 0; i <= n ; i++) {
            ans = Math.max(ans,w[k][i]);
        }
        return ans >= INF / 2 ? -1 : ans;
    }
    void floyd(){
        // floyd 基本流程为三层循环：
        // 枚举中转点 - 枚举起点 - 枚举终点 - 松弛操作
        for (int p = 1; p <= n ; p++) {
            for (int i = 1; i <= n ; i++) {
                for (int j = 1; j <= n ; j++) {
                    w[i][j]  = Math.min(w[i][j],w[i][p] + w[p][j]);
                }
            }
        }
    }
}
//时间复杂度：O(N3)
//空间复杂度：O(N2)

/**
 朴素 Dijkstra（邻接矩阵）
 同理，我们可以使用复杂度为 O(N2) 的「单源最短路」算法朴素 Dijkstra 算法进行求解，同时使用「邻接矩阵」来进行存图。
 根据题意， k 点作为源点，跑一遍 Dijkstra 我们可以得到从源点 k 到其他点 x 的最短距离。
 再从所有最短路中取 max 即是「从 k 点出发，到其他点 x 的最短距离的最大值」。
 朴素 Dijkstra 复杂度为 O(N2)，可以过。
 */
class Solution2{
    int N = 110,M = 6010;
    // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
    int[][] w= new int[N][N];
    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    int[] dist =new int[N];
    //记录哪些点被更新过
    boolean[] visited = new boolean[N];
    int INF = 0x3f3f3f3f;
    int n,k;
    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n = _n; k = _k;
        //初始化邻接矩阵
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n ; j++) {
                w[i][j] = w[j][i] = i == j ? 0 :INF;
            }
        }
        //存图
        for (int[] t : ts) {
            int u = t[0], v = t[1], c = t[2];
            w[u][v] = c;
        }
        //最短路
        dijkstra();
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        return ans > INF / 2 ? -1 : ans;
    }
    void dijkstra(){
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
//时间复杂度：O(N2)
//空间复杂度：O(N2)

/**
 堆优化 Dijkstra（邻接表）
 由于边数据范围不算大，我们还可以使用复杂度为 mlogn 的堆优化 Dijkstra 算法进行求解。
 堆优化 Dijkstra 算法与朴素 Dijkstra 都是「单源最短路」算法。
 跑一遍堆优化 Dijkstra 算法求最短路，再从所有最短路中取 max  即是「从 k 点出发，到其他点 x 的最短距离的最大值」。
 此时算法复杂度为 mlogn ，可以过。
 */
class Solution3{
    int N =110,M = 6010;
    int[] he = new int[N],e = new int[M],ne = new int[M],w = new int[M];
    int[] dist =new int[N];
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
        n = _n;k =_k;
        Arrays.fill(he,-1);
        for (int[] t : ts) {
            int u = t[0], v =t[1],c = t[2];
            add(u,v,c);
        }
        dijkstra();
        int ans = 0;
        for (int i = 1; i <= n ; i++) {
            ans = Math.max(ans,dist[i]);
        }
        return ans > INF / 2 ? -1 :ans;
    }
    void dijkstra(){
        Arrays.fill(vis,false);
        Arrays.fill(dist,INF);
        dist[k] = 0;
        PriorityQueue<int[]> q= new PriorityQueue<>((a,b) ->a[1] - b[1]);
        q.add(new int[]{k,0});
        while (!q.isEmpty()){
            int[] poll = q.poll();
            int id = poll[0], step = poll[1];
            if (vis[id]) continue;
            vis[id] = true;
            for (int i = he[id]; i != -1 ; i=ne[i]) {
                int j = e[i];
                if (dist[j] > dist[id] + w[i]){
                    dist[j] = dist[id] + w[i];
                    q.add(new int[]{j,dist[j]});
                }
            }
        }
    }
}
//时间复杂度：O(mlogn + n)
//空间复杂度：O(m)
/**
 *
 Bellman Ford（类 & 邻接表）
 虽然题目规定了不存在「负权边」，但我们仍然可以使用可以在「负权图中求最短路」的 Bellman Ford 进行求解，该算法也是「单源最短路」算法，复杂度为 O(N*M)。
 通常为了确保O(N*M) ，可以单独建一个类代表边，将所有边存入集合中，在 N 次松弛操作中直接对边集合进行遍历（代码见 ）。
 由于本题边的数量级大于点的数量级，因此也能够继续使用「邻接表」的方式进行边的遍历，遍历所有边的复杂度的下界为 O(N)，上界可以确保不超过 O(M) （代码见 ）。
 */
class Solution4{
    class Edge{
        int a,b,c;
        Edge(int _a,int _b,int _c){
            a =_a;b =_b;c =_c;
        }
    }
    int N =110, M = 6010;
    int[] dist = new int[N];
    int INF =0x3f3f3f3f;
    int n,m,k;
    //使用类进行存边
    List<Edge> es = new ArrayList<>();
    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n = _n; k =_k;
        m = ts.length;
        //存图
        for (int[] t : ts) {
            int u =t[0],v =t[1],c = t[2];
            es.add(new Edge(u,v,c));
        }
        //最短路
        bfs();
        //遍历答案
        int ans = 0;
        for (int i = 1; i <= n ; i++) {
            ans = Math.max(ans,dist[i]);
        }
        return ans > INF / 2 ? -1 :ans;
    }
    void bfs(){
        // 起始先将所有的点标记为「距离为正无穷」
        Arrays.fill(dist, INF);
        // 只有起点最短距离为 0
        dist[k] = 0;
        // 迭代 n 次
        for (int p = 1; p <= n; p++) {
            int[] prev = dist.clone();
            // 每次都使用上一次迭代的结果，执行松弛操作
            for (Edge e : es) {
                int a = e.a, b = e.b, c = e.c;
                dist[b] = Math.min(dist[b], prev[a] + c);
            }
        }
    }
}
class Solution5 {
    int N = 110, M = 6010;
    // 邻接表
    int[] he = new int[N], e = new int[M], ne = new int[M], w = new int[M];
    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    int[] dist = new int[N];
    int INF = 0x3f3f3f3f;
    int n, m, k, idx;
    void add(int a, int b, int c) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx;
        w[idx] = c;
        idx++;
    }
    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n = _n; k = _k;
        m = ts.length;
        // 初始化链表头
        Arrays.fill(he, -1);
        // 存图
        for (int[] t : ts) {
            int u = t[0], v = t[1], c = t[2];
            add(u, v, c);
        }
        // 最短路
        bf();
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        return ans > INF / 2 ? -1 : ans;
    }
    void bf() {
        // 起始先将所有的点标记为「距离为正无穷」
        Arrays.fill(dist, INF);
        // 只有起点最短距离为 0
        dist[k] = 0;
        // 迭代 n 次
        for (int p = 1; p <= n; p++) {
            int[] prev = dist.clone();
            // 每次都使用上一次迭代的结果，执行松弛操作
            for (int a = 1; a <= n; a++) {
                for (int i = he[a]; i != -1; i = ne[i]) {
                    int b = e[i];
                    dist[b] = Math.min(dist[b], prev[a] + w[i]);
                }
            }
        }
    }
}
//时间复杂度：O(N*M)
//空间复杂度：O(M)
/**
 *
 SPFA（邻接表）
 SPFA 是对 Bellman Ford 的优化实现，可以使用队列进行优化，也可以使用栈进行优化。
 通常情况下复杂度为 O(K*M) ， 一般为 4 到 5，最坏情况下仍为O(K*M)，当数据为网格图时，复杂度会从 O(K*M) 退化为O(N*M) 。
 */
class Solution6 {
    int N = 110, M = 6010;
    // 邻接表
    int[] he = new int[N], e = new int[M], ne = new int[M], w = new int[M];
    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    int[] dist = new int[N];
    // 记录哪一个点「已在队列」中
    boolean[] vis = new boolean[N];
    int INF = 0x3f3f3f3f;
    int n, k, idx;
    void add(int a, int b, int c) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx;
        w[idx] = c;
        idx++;
    }
    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n = _n; k = _k;
        // 初始化链表头
        Arrays.fill(he, -1);
        // 存图
        for (int[] t : ts) {
            int u = t[0], v = t[1], c = t[2];
            add(u, v, c);
        }
        // 最短路
        spfa();
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        return ans > INF / 2 ? -1 : ans;
    }
    void spfa() {
        // 起始先将所有的点标记为「未入队」和「距离为正无穷」
        Arrays.fill(vis, false);
        Arrays.fill(dist, INF);
        // 只有起点最短距离为 0
        dist[k] = 0;
        // 使用「双端队列」存储，存储的是点编号
        Deque<Integer> d = new ArrayDeque<>();
        // 将「源点/起点」进行入队，并标记「已入队」
        d.addLast(k);
        vis[k] = true;
        while (!d.isEmpty()) {
            // 每次从「双端队列」中取出，并标记「未入队」
            int poll = d.pollFirst();
            vis[poll] = false;
            // 尝试使用该点，更新其他点的最短距离
            // 如果更新的点，本身「未入队」则加入队列中，并标记「已入队」
            for (int i = he[poll]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[poll] + w[i]) {
                    dist[j] = dist[poll] + w[i];
                    if (vis[j]) continue;
                    d.addLast(j);
                    vis[j] = true;
                }
            }
        }
    }
}
//时间复杂度：O(N*M)
//空间复杂度：O(M)
