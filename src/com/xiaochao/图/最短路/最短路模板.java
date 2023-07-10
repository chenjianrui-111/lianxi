package com.xiaochao.图.最短路;

/**
 * 邻接矩阵
 * 这是一种使用二维矩阵来进行存图的方式。
 * 适用于边数较多的「稠密图」使用，当边数量接近点的数量的平方，即  时，可定义为「稠密图」。
 * // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
 * int[][] w = new int[N][N];
 * // 加边操作
 * void add(int a, int b, int c) {
 *     w[a][b] = c;
 * }
 */

/**
 * 邻接表
 * 这也是一种在图论中十分常见的存图方式，与数组存储单链表的实现一致（头插法）。
 * 这种存图方式又叫「链式前向星存图」。
 * 适用于边数较少的「稀疏图」使用，当边数量接近点的数量，即  时，可定义为「稀疏图」。
 * int[] he = new int[N], e = new int[M], ne = new int[M], w = new int[M];
 * int idx;
 * void add(int a, int b, int c) {
 *     e[idx] = b;
 *     ne[idx] = he[a];
 *     he[a] = idx;
 *     w[idx] = c;
 *     idx++;
 * }
 *首先 idx 是用来对边进行编号的，然后对存图用到的几个数组作简单解释：
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

import java.util.*;

/**
 * Floyd（邻接矩阵）
 * 根据「基本分析」，我们可以使用复杂度为 O(N^3) 的「多源汇最短路」算法 Floyd 算法进行求解，同时使用「邻接矩阵」来进行存图。
 * 此时计算量约为 10^6，可以过。
 * 跑一遍 Floyd，可以得到「从任意起点出发，到达任意起点的最短距离」。
 * 然后从所有 W[K][X] 中取 max 即是「从  k 点出发，到其他点 x 的最短距离的最大值」。
 */
public class 最短路模板 {
    int N = 110, M = 6010;
    // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
    int[][] w = new int[N][N];
    int INF = 0x3f3f3f3f;
    int n, k;
    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n = _n; k = _k;
        // 初始化邻接矩阵
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                w[i][j] = w[j][i] = i == j ? 0 : INF;
            }
        }
        // 存图
        for (int[] t : ts) {
            int u = t[0], v = t[1], c = t[2];
            w[u][v] = c;
        }
        // 最短路
        floyd();
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, w[k][i]);
        }
        return ans >= INF / 2 ? -1 : ans;
    }
    void floyd() {
        // floyd 基本流程为三层循环：
        // 枚举中转点 - 枚举起点 - 枚举终点 - 松弛操作
        for (int p = 1; p <= n; p++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    w[i][j] = Math.min(w[i][j], w[i][p] + w[p][j]);
                }
            }
        }
    }
}
//时间复杂度： O(N^3)
//空间复杂度： O(N^2)
/**
 *朴素 Dijkstra（邻接矩阵）
 * 同理，我们可以使用复杂度为 O(n^2) 的「单源最短路」算法朴素 Dijkstra 算法进行求解，同时使用「邻接矩阵」来进行存图。
 * 根据题意，k 点作为源点，跑一遍 Dijkstra 我们可以得到从源点 k 到其他点 x 的最短距离。
 * 再从所有最短路中取 max 即是「从  k 点出发，到其他点  x 的最短距离的最大值」。
 * 朴素 Dijkstra 复杂度为 O(n^2)，可以过。
 */
class Dijkstra{
    int N = 110, M = 6010;
    // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
    int[][] w = new int[N][N];
    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    int[] dist = new int[N];
    // 记录哪些点已经被更新过
    boolean[] vis = new boolean[N];
    int INF = 0x3f3f3f3f;
    int n, k;
    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n = _n; k = _k;
        // 初始化邻接矩阵
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                w[i][j] = w[j][i] = i == j ? 0 : INF;
            }
        }
        // 存图
        for (int[] t : ts) {
            int u = t[0], v = t[1], c = t[2];
            w[u][v] = c;
        }
        // 最短路
        dijkstra();
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        return ans > INF / 2 ? -1 : ans;
    }
    void dijkstra() {
        // 起始先将所有的点标记为「未更新」和「距离为正无穷」
        Arrays.fill(vis, false);
        Arrays.fill(dist, INF);
        // 只有起点最短距离为 0
        dist[k] = 0;
        // 迭代 n 次
        for (int p = 1; p <= n; p++) {
            // 每次找到「最短距离最小」且「未被更新」的点 t
            int t = -1;
            for (int i = 1; i <= n; i++) {
                if (!vis[i] && (t == -1 || dist[i] < dist[t])) t = i;
            }
            // 标记点 t 为已更新
            vis[t] = true;
            // 用点 t 的「最小距离」更新其他点
            for (int i = 1; i <= n; i++) {
                dist[i] = Math.min(dist[i], dist[t] + w[t][i]);
            }
        }
    }
}
//时间复杂度： O(n^2)
//空间复杂度： O(n^2)

/**
 *堆优化 Dijkstra（邻接表）
 * 由于边数据范围不算大，我们还可以使用复杂度为 O(mlogn) 的堆优化 Dijkstra 算法进行求解。
 * 堆优化 Dijkstra 算法与朴素 Dijkstra 都是「单源最短路」算法。
 * 跑一遍堆优化 Dijkstra 算法求最短路，再从所有最短路中取  即是「从  点出发，到其他点  的最短距离的最大值」。
 * 此时算法复杂度为 O(mlogn)，可以过
 **/
class DuiDijkstra{
    int N = 110, M = 6010;
    // 邻接表
    int[] he = new int[N], e = new int[M], ne = new int[M], w = new int[M];
    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    int[] dist = new int[N];
    // 记录哪些点已经被更新过
    boolean[] vis = new boolean[N];
    int n, k, idx;
    int INF = 0x3f3f3f3f;
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
        dijkstra();
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        return ans > INF / 2 ? -1 : ans;
    }
    void dijkstra() {
        // 起始先将所有的点标记为「未更新」和「距离为正无穷」
        Arrays.fill(vis, false);
        Arrays.fill(dist, INF);
        // 只有起点最短距离为 0
        dist[k] = 0;
        // 使用「优先队列」存储所有可用于更新的点
        // 以 (点编号, 到起点的距离) 进行存储，优先弹出「最短距离」较小的点
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->a[1]-b[1]);
        q.add(new int[]{k, 0});
        while (!q.isEmpty()) {
            // 每次从「优先队列」中弹出
            int[] poll = q.poll();
            int id = poll[0], step = poll[1];
            // 如果弹出的点被标记「已更新」，则跳过
            if (vis[id]) continue;
            // 标记该点「已更新」，并使用该点更新其他点的「最短距离」
            vis[id] = true;
            for (int i = he[id]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[id] + w[i]) {
                    dist[j] = dist[id] + w[i];
                    q.add(new int[]{j, dist[j]});
                }
            }
        }
    }
}
//时间复杂度：O(mlogn + n)
//空间复杂度：O(m)

/**
 *Bellman Ford（类 & 邻接表）
 * 虽然题目规定了不存在「负权边」，但我们仍然可以使用可以在「负权图中求最短路」的 Bellman Ford 进行求解，该算法也是「单源最短路」算法，复杂度为 O(n * m) 。
 * 通常为了确保 O(n * m) ，可以单独建一个类代表边，将所有边存入集合中，在 n 次松弛操作中直接对边集合进行遍历（代码见 P1）。
 **/
class BellmanFord{
    class Edge {
        int a, b, c;
        Edge(int _a, int _b, int _c) {
            a = _a; b = _b; c = _c;
        }
    };
    int N = 110, M = 6010;
    // dist[x] = y 代表从「源点/起点」到 x 的最短距离为 y
    int[] dist = new int[N];
    int INF = 0x3f3f3f3f;
    int n, m, k;
    // 使用类进行存边
    List<Edge> es = new ArrayList<>();
    public int networkDelayTime(int[][] ts, int _n, int _k) {
        n = _n; k = _k;
        m = ts.length;
        // 存图
        for (int[] t : ts) {
            int u = t[0], v = t[1], c = t[2];
            es.add(new Edge(u, v, c));
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
            for (Edge e : es) {
                int a = e.a, b = e.b, c = e.c;
                dist[b] = Math.min(dist[b], prev[a] + c);
            }
        }
    }
}
class LinJieBiao{
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
//时间复杂度：O(n * m)
//空间复杂度：O(m)
/**
 *SPFA（邻接表）
 * SPFA 是对 Bellman Ford 的优化实现，可以使用队列进行优化，也可以使用栈进行优化。
 * 通常情况下复杂度为 O(k * m)， 一般为 4 到 5 ，最坏情况下仍为 O(n * m) ，当数据为网格图时，复杂度会从 O(k * m)  退化为 O(n * m)
 */
class SPFA{
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
//时间复杂度：O(n * m)
//空间复杂度：O(m)
