package com.xiaochao.图.最小生成树;

/**
 * 在一个 n x n 的整数矩阵 grid 中，每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。
 * 当开始下雨时，在时间为 t 时，水池中的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
 * 你从坐标方格的左上平台 (0，0) 出发。返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。
 *输入: grid = [[0,2],[1,3]]
 * 输出: 3
 * 解释:
 * 时间为0时，你位于坐标方格的位置为 (0, 0)。
 * 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
 * 等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
 * 提示: • 2 <= N <= 50.
 * • grid[i][j] 是 [0, ..., N*N - 1] 的排列。
 */

import java.util.*;

/**
 * Kruskal
 * 由于在任意点可以往任意方向移动，所以相邻的点（四个方向）之间存在一条无向边。
 * 边的权重 w 是指两点节点中的最大高度。
 * 按照题意，我们需要找的是从左上角点到右下角点的最优路径，其中最优路径是指途径的边的最
 * 大权重值最小，然后输入最优路径中的最大权重值。
 * 我们可以先遍历所有的点，将所有的边加入集合，存储的格式为数组 [a, b, w] ，代表编号为 a
 * 的点和编号为 b 的点之间的权重为 w（按照题意，w 为两者的最大高度）。
 * 对集合进行排序，按照 w 进行从小到达排序。
 * 当我们有了所有排好序的候选边集合之后，我们可以对边从前往后处理，每次加入一条边之后，
 * 使用并查集来查询左上角的点和右下角的点是否连通。
 * 当我们的合并了某条边之后，判定左上角和右下角的点联通，那么该边的权重即是答案。
 * 这道题和前天的 1631. 最小体力消耗路径 几乎是完全一样的思路。
 * 你甚至可以将那题的代码拷贝过来，改一下对于 w 的定义即可。
 */
public class 水位上升的泳池中游泳 {
    int n;
    int[] p;
    void union(int a,int b){
        p[find(a)] = p[find(b)];
    }
    boolean query(int a,int b){
        return find(a) == find(b);
    }
    int find(int x){
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
    public int swimInWater(int[][] grid) {
        n = grid.length;
        //初始化并查集
        p = new int[n*n];
        if (n == 1) return grid[0][0];
        for (int i = 0; i < n*n ; i++) {
            p[i] = i;
        }
        // 预处理出所有的边
        // edge 存的是 [a, b, w]：代表从 a 到 b 所需要的时间为 w
        // 虽然我们可以往四个方向移动，但是只要对于每个点都添加「向右」和「向下」两条边的话，其实就已经覆盖
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n ; j++) {
                int idx = getIndex(i,j);
                p[idx] = idx;
                if (i + 1 < n){
                    int a = idx, b = getIndex(i+1,j);
                    int w = Math.max(grid[i][j],grid[i+1][j]);
                    edges.add(new int[]{a,b,w});
                }
                if (j + 1 < n){
                    int a = idx, b = getIndex(i,j+1);
                    int w = Math.max(grid[i][j],grid[i][j+1]);
                    edges.add(new int[]{a,b,w});
                }
            }
        }
        //根绝权重排序
        Collections.sort(edges,(a,b) ->a[2] - b[2]);
        // 从「小边」开始添加，当某一条边别应用之后，恰好使用得「起点」和「结点」联通
        // 那么代表找到了「最短路径」中的「权重最大的边」
        int start = getIndex(0,0),end = getIndex(n-1,n-1);
        for (int[] edge : edges) {
            int a = edge[0],b = edge[1],c =edge[2];
            union(a,b);
            if (query(start,end)){
                return c;
            }
        }
        return -1;
    }
    int getIndex(int i,int j){
        return i * n + j;
    }
}
//节点的数量为 n ∗ n，无向边的数量严格为 2 ∗ n ∗ (n − 1)，数量级上为 n2。 • 时间复杂度：获取所有的边复杂度为 O(n2)，排序复杂度为 O(n2log n)，遍历得
//到最终解复杂度为 O(n2)。整体复杂度为 O(n2log n)。 • 空间复杂度：使用了并查集数组。复杂度为 O(n2)。
//注意：假定 Collections.sort() 使用 Arrays.sort() 中的双轴快排实现。

/**
 * 二分 + BFS/DFS
 * 在与本题类型的 1631. 最小体力消耗路径中，有同学问到是否可以用「二分」。
 * 答案是可以的。
 * 题目给定了 grid[i][j] 的范围是 [0, n2 − 1]，所以答案必然落在此范围。
 * 假设最优解为 min 的话（恰好能到达右下角的时间）。那么小于 min 的时间无法到达右下
 * 角，大于 min 的时间能到达右下角。
 * 因此在以最优解 min 为分割点的数轴上具有两段性，可以通过「二分」来找到分割点 min。
 * 注意：「二分」的本质是两段性，并非单调性。只要一段满足某个性质，另外一段不满足
 * 某个性质，就可以用「二分」。其中 33. 搜索旋转排序数组 是一个很好的说明例子。
 * 接着分析，假设最优解为 min，我们在 [l, r] 范围内进行二分，当前二分到的时间为 mid 时：
 * 1. 能到达右下角：必然有 min ⩽ mid，让 r = mid
 * 2. 不能到达右下角：必然有 min > mid，让 l = mid + 1
 * 当确定了「二分」逻辑之后，我们需要考虑如何写 check 函数。
 * 显然 check 应该是一个判断给定 时间/步数 能否从「起点」到「终点」的函数。
 * 我们只需要按照规则走特定步数，边走边检查是否到达终点即可。
 * 实现 check 既可以使用 DFS 也可以使用 BFS。两者思路类似，这里就只以 BFS 为例。
 */
class Solution {
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int l = 0, r = n * n;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(grid, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    boolean check(int[][] grid, int time) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] pos = queue.pollFirst();
            int x = pos[0], y = pos[1];
            if (x == n - 1 && y == n - 1) return true;
            for (int[] dir : dirs) {
                int newX = x + dir[0], newY = y + dir[1];
                int[] to = new int[]{newX, newY};
                if ( inArea(n, newX, newY) && !visited[newX][newY] && canMove(grid, pos, to,time))
                        visited[newX][newY] = true;
                        queue.addLast(to);
            }
        }
        return false;
    }

    boolean inArea(int n, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    boolean canMove(int[][] grid, int[] from, int[] to, int time) {
        return time >= Math.max(grid[from[0]][from[1]], grid[to[0]][to[1]]);
    }
}
//• 时间复杂度：在 [0, n2] 范围内进行二分，复杂度为 O(log n)；每一次 BFS 最多
//有 n2 个节点入队，复杂度为 O(n2)。整体复杂度为 O(n2log n) • 空间复杂度：使用了 visited 数组。复杂度为 O(n2)
