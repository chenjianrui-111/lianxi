package com.xiaochao.前缀和;

/**
 * 给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。同时给你一个二维数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
 * 你按照如下规则进行一场游戏：
 * 你从第 0 天开始吃糖果。
 * 你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。
 * 在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。
 * 请你构建一个布尔型数组 answer ，用以给出 queries 中每一项的对应答案。此数组满足：
 * answer.length == queries.length 。answer[i] 是 queries[i] 的答案。
 * answer[i] 为 true 的条件是：在每天吃 不超过 dailyCapi 颗糖果的前提下，你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；否则 answer[i] 为 false 。
 * 注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
 * 请你返回得到的数组 answer 。
 * 示例 1：
 * 输入：candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
 * 输出：[true,false,true]
 * 提示：
 * 1- 在第 0 天吃 2 颗糖果(类型 0），第 1 天吃 2 颗糖果（类型 0），第 2 天你可以吃到类型 0 的糖果。
 * 2- 每天你最多吃 4 颗糖果。即使第 0 天吃 4 颗糖果（类型 0），第 1 天吃 4 颗糖果（类型 0 和类型 1），你也没办法在第 2 天吃到类型 4 的糖果。换言之，你没法在每天吃 4 颗糖果的限制下在第 2 天吃到第 4 类糖果。
 * 3- 如果你每天吃 1 颗糖果，你可以在第 13 天吃到类型 2 的糖果。
 * 提示：
 * 1 <= candiesCount.length <= 105
 * 1 <= candiesCount[i] <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 3
 * 0 <= favoriteTypei < candiesCount.length
 * 0 <= favoriteDayi <= 109
 * 1 <= dailyCapi <= 109
 */

/**
 * 基本分析
 * 根据题意，在处理某个询问时，每天的吃糖数量为 [1, queries[i][2]]，因此我们可以计算出
 * 「最早/最晚」吃到第 queries[i][0] 类糖果的时间，然后判断 queries[i][1] 是否落在范围
 * 内，若落在范围内返回则有 ans[i] 为 True ，否则为 False 。 前缀和
 * 问题转换为如何快速求得「最早/最晚」吃到第 queries[i][0] 类糖果的时间。
 * 我们需要先预处理出 candiesCount 的前缀和数组 sum（下标从 1 开始），方便快速求得第i 类糖果之前有多少糖果。
 * 为了方便，在处理某个询问时，我们令 t = queries[i][0]，d = queries[i][1] + 1，c = queries[i][2]。其中 d = queries[i][1] + 1 是因为题目天数是从 0 开始计算，而我们的计算是从 1 开始。
 * 然后计算「最早/最晚」吃到第 t 类糖果的时间：
 * • 最早时间（第一颗 t 类糖的最早时间）：当以最大速率 c 吃糖时，可以在最早时间
 * 内吃到糖。时间为吃掉第 t 类糖果 前面 的所有糖果的时间（下取整）加 1 ：[sum[t] / c] + 1
 * • 最晚时间（最后一颗 t 类糖的最晚时间）：当以最小速率 1 吃糖时，可以计算出最晚吃糖时间。时间为吃掉所有 t 类糖的时间： sum[t + 1]
 */
public class 你能在你最喜欢的那天吃到你最喜欢的糖果吗 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = queries.length, m = candiesCount.length;
        boolean[] ans = new boolean[n];
        long[] sum = new long[m + 1];
        for (int i = 1; i <= m ; i++) {
            sum[i] = sum[i  - 1] + candiesCount[i - 1];
        }
        for (int i = 0; i < n ; i++) {
            int t = queries[i][0] , d= queries[i][1] + 1, c = queries[i][2];
            long a = sum[t] / c + 1, b = sum[t + 1];
            ans[i] = a <= d && d <= b;
        }
        return ans;
    }
}
///• 时间复杂度： cs 数组的长度为 n ， qs 数组的长度为 m 。预处理前缀和的复杂 度为 O(n)；处理每个询问的复杂度为 O(1)，共有 m 个询问，复杂度为 O(m)。
//整体复杂度为 O(max(n, m))
//• 空间复杂度：O(n)
