package com.xiaochao.DP.数位dp;

import java.util.Arrays;

/**
 * 给你一个 互不相同 的整数数组，其中 locations[i] 表示第 i 个城市的位置。同时给你 start，finish 和 fuel 分别表示出发城市、目的地城市和你初始拥有的汽油总量
 * 每一步中，如果你在城市 i ，你可以选择任意一个城市 j ，满足  j != i 且 0 <= j < locations.length ，并移动到城市 j 。从城市 i 移动到 j 消耗的汽油量为 |locations[i] - locations[j]|，|x| 表示 x 的绝对值。
 * 请注意， fuel 任何时刻都 不能 为负，且你 可以 经过任意城市超过一次（包括 start 和 finish ）。
 * 请你返回从 start 到 finish 所有可能路径的数目。
 * 由于答案可能很大， 请将它对 10^9 + 7 取余后返回。
 * 示例 1：
 * 输入：locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
 * 输出：4
 * 解释：以下为所有可能路径，每一条都用了 5 单位的汽油：
 * 1 -> 3
 * 1 -> 2 -> 3
 * 1 -> 4 -> 3
 * 1 -> 4 -> 2 -> 3
 */
public class 统计所有可行路径 {

    int mod = 1000000007;
    // 缓存器：用于记录「特定状态」下的结果
    // cache[i][fuel] 代表从位置 i 出发，当前剩余的油量为 fuel 的前提下，到达目标位置的「路径数量」
    int[][] cache;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        // 初始化缓存器
        // 之所以要初始化为 -1
        // 是为了区分「某个状态下路径数量为 0」和「某个状态尚未没计算过」两种情况
        cache = new int[n][fuel + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1);
        }
        return dfs(locations, start, finish, fuel);
    }

    /**
     * 计算「路径数量」
     * @param ls 入参 locations
     * @param u 当前所在位置（ls 的下标）
     * @param end 目标哦位置（ls 的下标）
     * @param fuel 剩余油量
     * @return 在位置 u 出发，油量为 fuel 的前提下，到达 end 的「路径数量」
     */
    int dfs(int[] ls, int u, int end, int fuel) {
        // 如果缓存器中已经有答案，直接返回
        if (cache[u][fuel] != -1) {
            return cache[u][fuel];
        }

        int n = ls.length;
        // base case 1：如果油量为 0，且不在目标位置
        // 将结果 0 写入缓存器并返回
        if (fuel == 0 && u != end) {
            cache[u][fuel] = 0;
            return 0;
        }

        // base case 2：油量不为 0，且无法到达任何位置
        // 将结果 0 写入缓存器并返回
        boolean hasNext = false;
        for (int i = 0; i < n; i++) {
            if (i != u) {
                int need = Math.abs(ls[u] - ls[end]);
                if (fuel >= need) {
                    hasNext = true;
                    break;
                }
            }
        }
        if (fuel != 0 && !hasNext) {
            cache[u][fuel] = 0;
            return 0;
        }

        // 计算油量为 fuel，从位置 u 到 end 的路径数量
        // 由于每个点都可以经过多次，如果 u = end，那么本身就算一条路径
        int sum = u == end ? 1 : 0;
        for (int i = 0; i < n; i++) {
            if (i != u) {
                int need = Math.abs(ls[i] - ls[u]);
                if (fuel >= need) {
                    sum += dfs(ls, i, end, fuel - need);
                    sum %= mod;
                }
            }
        }
        cache[u][fuel] = sum;
        return sum;
    }
}
//时间复杂度：最坏情况下共有 n * fuel 个状态需要计算（填满整个 cache 数组）。每计算一个状态需要遍历一次 locations 数组，复杂度为 O(n)。整体复杂度为 O(n^2 * fuel)O
//空间复杂度：O(n^2 * fuel)
/**
 * 简化 Base Case
 * 考虑一个问题：如果我们从某个位置 ii 出发，不能一步到达目标位置的话，有可能使用多步到达目标位置吗？
 * 也就是一步不行的话，多步可以吗？
 * 答案是不可以。
 */
class Solution {
    int mod = 1000000007;

    // 缓存器：用于记录「特定状态」下的结果
    // cache[i][fuel] 代表从位置 i 出发，当前剩余的油量为 fuel 的前提下，到达目标位置的「路径数量」
    int[][] cache;

    public int countRoutes(int[] ls, int start, int end, int fuel) {
        int n = ls.length;

        // 初始化缓存器
        // 之所以要初始化为 -1
        // 是为了区分「某个状态下路径数量为 0」和「某个状态尚未没计算过」两种情况
        cache = new int[n][fuel + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1);
        }

        return dfs(ls, start, end, fuel);
    }

    /**
     * 计算「路径数量」
     * @param ls 入参 locations
     * @param u 当前所在位置（ls 的下标）
     * @param end 目标哦位置（ls 的下标）
     * @param fuel 剩余油量
     * @return 在位置 u 出发，油量为 fuel 的前提下，到达 end 的「路径数量」
     */
    int dfs(int[] ls, int u, int end, int fuel) {
        // 如果缓存中已经有答案，直接返回
        if (cache[u][fuel] != -1) {
            return cache[u][fuel];
        }

        // 如果一步到达不了，说明从位置 u 不能到达 end 位置
        // 将结果 0 写入缓存器并返回
        int need = Math.abs(ls[u] - ls[end]);
        if (need > fuel) {
            cache[u][fuel] = 0;
            return 0;
        }

        int n = ls.length;
        // 计算油量为 fuel，从位置 u 到 end 的路径数量
        // 由于每个点都可以经过多次，如果 u = end，那么本身就算一条路径
        int sum = u == end ? 1 : 0;
        for (int i = 0; i < n; i++) {
            if (i != u) {
                need = Math.abs(ls[i] - ls[u]);
                if (fuel >= need) {
                    sum += dfs(ls, i, end, fuel - need);
                    sum %= mod;
                }
            }
        }
        cache[u][fuel] = sum;
        return sum;
    }
}
/**
 * 改为动态 DP
 * int dfs(int[] ls, int u, int end, int fuel) {}
 * 其中，ls 参数和 end 参数分别代表源输入的 locations 和 finish，在整个 DFS 过程都不会变化，属于不变参数。
 *
 * 而 u 参数和 fuelfuel 参数则是代表了 DFS 过程中的当前位置和当前油量，属于变化参数。
 * 因此我们可以定一个 f[][]二维数组，来分别表示两个可变参数。
 * 第一维代表当前位置（对应 locations 数组的下标），第二维代表当前剩余油量。
 * 二维数组中存储的就是我们的 DFS 方法的返回值（路径数量）。
 * 同时结合题意，不难得知维度的取值范围：
 * 第一维的取值范围为 [0, locations.length)
 * 第二维的取值范围为 [0, fuel]
 * 做完这一步的”翻译“工作，我们就得到了「动态规划」的「状态定义」了。
 * f[i][j] 代表从位置 i 出发，当前剩余油量为 j 的前提下，到达目的地的路径数量。
 * 不知道你是否发现，这个「状态定义」和我们「记忆化搜索」中的缓存器的定义是一致的。
 * 接下来我们要从 DFS 中”翻译“出「状态转移方程」。
 * 所谓的「状态转移方程」其实就是指如何从一个状态转移到另外一个状态。
 * 而我们的 DFS 主逻辑就是完成这个转移的。
 * DFS 中的主逻辑很简单：枚举所有的位置，看从当前位置 uu 出发，可以到达的位置有哪些。
 * 于是我们很容易就可以得出状态转移方程：
 * f[i][fuel]=f[i][fuel]+f[k][fuel-need]
 * k 代表计算位置 i 油量 fuel 的状态时枚举的「下一位置」，need 代表从 i 到达 k 需要的油量。
 * 从状态转移方程可以发现，在计算 f[i][fuel] 的时候依赖于 f[k][fuel-need]。
 * 其中 i和 k 并无严格的大小关系，而 fuel 和 fuel-need 具有严格的大小关系（fuel≥fuel−need）。
 * 因此我们需要先从小到大枚举油量这一维。
 */
class Solution2{
    int mod = 1000000007;
    public int countRoutes(int[] ls, int start, int end, int fuel) {
        int n = ls.length;
        // f[i][j] 代表从位置 i 出发，当前油量为 j 时，到达目的地的路径数
        int[][] f = new int[n][fuel + 1];

        // 对于本身位置就在目的地的状态，路径数为 1
        for (int i = 0; i <= fuel; i++) f[end][i] = 1;
       //从状态转移方程可以发现 f[i][fuel]=f[i][fuel]+f[k][fuel-need]
        // 在计算 f[i][fuel] 的时候依赖于 f[k][fuel-need]
        // 其中 i 和 k 并无严格的大小关系
        // 而 fuel 和 fuel-need 具有严格大小关系：fuel >= fuel-need
        // 因此需要先从小到大枚举油量
        for (int cur = 0; cur <= fuel; cur++) {
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    if (i != k) {
                        int need = Math.abs(ls[i] - ls[k]);
                        if (cur >= need) {
                            f[i][cur] += f[k][cur-need];
                            f[i][cur] %= mod;
                        }
                    }
                }
            }
        }
        return f[start][fuel];
    }
}

















