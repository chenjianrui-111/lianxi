package com.xiaochao.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
 * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃 1 个单位（即只能从单元格 1 跳至单元格 2 ）。
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
 * 示例 1：
 * 输入：stones = [0,1,3,5,6,8,12,17]
 * 输出：true
 * 解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
 * 示例 2：
 * 输入：stones = [0,1,2,3,4,8,9,11]
 * 输出：false
 * 解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
 * 提示：
 * 2 <= stones.length <= 2000
 * 0 <= stones[i] <= 231 - 1
 * stones[0] == 0
 * stones 按严格升序排列
 */
public class 青蛙过河 {
    Map<Integer,Integer> map = new HashMap<>();
    public boolean canCross(int[] stones) {
        int n = stones.length;
        //将石块信息放入哈希表
        //为了快速判断是否存在某块石子，以及迅速找到某块石子的下标
        for (int i = 0; i < n; i++) {
            map.put(stones[i],i);
        }
        //根绝题意，第一步是固定经过步长为 1 到达第一块石子（下标为1）
        if (!map.containsKey(1)) return false;
        return dfs(stones,n,1,1);
    }
    boolean dfs(int[] stones,int n,int u,int k){
        if (u == n - 1) return true;
        for (int i = -1; i <= 1 ; i++) {
            //如果是原地踏步的话，直接跳过
            if (k + i == 0) continue;
            //下一步的石子理论编号
            int next = stones[u] + k + i;
            // 如果存在下一步的石子，则跳转到下一步石子，并 DFS 下去
            if (map.containsKey(next)) {
                boolean cur = dfs(stones, n, map.get(next), k + i);
                if (cur) return true;
            }
        }
        return false;
    }
}
//• 时间复杂度：O(3n) • 空间复杂度：O(3n)
/**
 *记忆化搜索
 * 在考虑加入「记忆化」时，我们只需要将 DFS 方法签名中的【可变】参数作为维度， DFS 方
 * 法中的返回值作为存储值即可。
 * 通常我们会使用「数组」来作为我们缓存中间结果的容器，
 * 对应到本题，就是需要一个 boolean[石子列表下标][跳跃步数] 这样的数组，但使用布尔数组作
 * 为记忆化容器往往无法区分「状态尚未计算」和「状态已经计算，并且结果为 false 」两种情况。
 * 因此我们需要转为使用 int[石子列表下标][跳跃步数] ，默认值 0 代表状态尚未计算，−1 代表
 * 计算状态为 false ，1 代表计算状态为 true 。
 * 接下来需要估算数组的容量，可以从「数据范围」入手分析。
 * 根据 2 <= stones.length <= 2000 ，我们可以确定第一维（数组下标）的长度为 2009，而另
 * 外一维（跳跃步数）是与跳转过程相关的，无法直接确定一个精确边界，但是一个显而易见的事
 * 实是，跳到最后一块石子之后的位置是没有意义的，因此我们不会有「跳跃步长」大于「石子列
 * 表长度」的情况，因此也可以定为 2009（这里是利用了由下标为 i 的位置发起的跳跃不会超过i + 1 的性质）。
 * 至此，我们定下来了记忆化容器为 int[][] cache = new int[2009][2009] 。
 * 但是可以看出，上述确定容器大小的过程还是需要一点点分析 & 经验的。
 * 那么是否有思维难度再低点的方法呢？
 * 答案是有的，直接使用「哈希表」作为记忆化容器。「哈希表」本身属于非定长容器集合，我们
 * 不需要分析两个维度的上限到底是多少。
 * 另外，当容器维度较多且上界较大时（例如上述的 int[2009][2009] ），直接使用「哈希表」
 * 可以有效降低「爆空间/时间」的风险（不需要每跑一个样例都创建一个百万级的数组）。
 */
class Solution2{
    Map<Integer, Integer> map = new HashMap<>();
    // int[][] cache = new int[2009][2009];
    Map<String, Boolean> cache = new HashMap<>();

    public boolean canCross(int[] ss) {
        int n = ss.length;
        for (int i = 0; i < n; i++) {
            map.put(ss[i], i);
        }
// check first step
        if (!map.containsKey(1)) return false;
        return dfs(ss, ss.length, 1, 1);
    }

    boolean dfs(int[] ss, int n, int u, int k) {
        String key = u + "_" + k;
// if (cache[u][k] != 0) return cache[u][k] == 1;
        if (cache.containsKey(key)) return cache.get(key);
        if (u == n - 1) return true;
        for (int i = -1; i <= 1; i++) {
            if (k + i == 0) continue;
            int next = ss[u] + k + i;
            if (map.containsKey(next)) {
                boolean cur = dfs(ss, n, map.get(next), k + i);
// cache[u][k] = cur ? 1 : -1;
                cache.put(key, cur);
                if (cur) return true;
            }
        }
// cache[u][k] = -1;
        cache.put(key, false);
        return false;
    }
}
///• 时间复杂度：O(n2) • 空间复杂度：O(n2)
/**
 *动态规划
 * 有了「记忆化搜索」的基础，要写写出来动态规划就变得相对简单了。
 * 我们可以从 DFS 函数出发，写出「动态规划」解法。
 * 我们的 DFS 函数签名为：
 * boolean dfs(int[] ss, int n, int u, int k);
 * 其中前两个参数为不变参数，后两个为可变参数，返回值是我们的答案。
 * 因此可以设定为 f[][] 作为动规数组：
 * 1. 第一维为可变参数 u，代表石子列表的下标，范围为数组 stones 长度；
 * 2. 第二维为可变参数 k，代表上一步的的跳跃步长，前面也分析过了，最多不超过数
 * 组 stones 长度。
 * 这样的「状态定义」所代表的含义：当前在第 i 个位置，并且是以步长 k 跳到位置 i 时，是否
 * 到达最后一块石子。
 * 那么对于 f[i][k] 是否为真，则取决于上一位置 j 的状态值，结合每次步长的变化为 [-1,0,1]
 * 可知：
 * • 可从 f[j][k − 1] 状态而来：先是经过 k − 1 的跳跃到达位置 j，再在原步长的基
 * 础上 +1 ，跳到了位置 i。 • 可从 f[j][k] 状态而来：先是经过 k 的跳跃到达位置 j，维持原步长不变，跳到了
 * 位置 i。 • 可从 f[j][k + 1] 状态而来：先是经过 k + 1 的跳跃到达位置 j，再在原步长的基础上 -1 ，跳到了位置 i。
 * 只要上述三种情况其中一种为真，则 f[i][j] 为真。
 * 至此，我们解决了动态规划的「状态定义」&「状态转移方程」部分。但这就结束了吗？还没有。
 * 我们还缺少可让状态递推下去的「有效值」，或者说缺少初始化环节。 因为我们的 f[i][k] 依赖于之前的状态进行“或运算”而来，转移方程本身不会产生 true 值。因
 * 此为了让整个「递推」过程可滚动，我们需要先有一个为 true 的状态值。
 * 这时候再回看我们的状态定义：当前在第 i 个位置，并且是以步长 k 跳到位置 i 时，是否到达最后一块石子。
 * 显然，我们事先是不可能知道经过「多大的步长」跳到「哪些位置」，最终可以到达最后一块石子。
 * 这时候需要利用「对偶性」将跳跃过程「翻转」过来分析：
 * 我们知道起始状态是「经过步长为 1」的跳跃到达「位置 1」，如果从起始状态出发，存在一种
 * 方案到达最后一块石子的话，那么必然存在一条反向路径，它是以从「最后一块石子」开始，并
 * 以「某个步长 k」开始跳跃，最终以回到位置 1。
 * 因此我们可以设 f[1][1] = true，作为我们的起始值。
 * 这里本质是利用「路径可逆」的性质，将问题进行了「等效对偶」。表面上我们是进行「正向递
 * 推」，但事实上我们是在验证是否存在某条「反向路径」到达位置 1。
 */
class Solution3 {
    public boolean canCross(int[] ss) {
        int n = ss.length;
// check first step
        if (ss[1] != 1) return false;
        boolean[][] f = new boolean[n + 1][n + 1];
        f[1][1] = true;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                int k = ss[i] - ss[j];
// 我们知道从位置 j 到位置 i 是需要步长为 k 的跳跃
// 而从位置 j 发起的跳跃最多不超过 j + 1
// 因为每次跳跃，下标至少增加 1，而步长最多增加 1
                if (k <= j + 1) {
                    f[i][k] = f[j][k - 1] || f[j][k] || f[j][k + 1];
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (f[n - 1][i]) return true;
        }
        return false;
    }
}
//• 时间复杂度：O(n2)
//• 空间复杂度：O(n2)
