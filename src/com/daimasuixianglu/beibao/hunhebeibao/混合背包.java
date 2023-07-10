package com.daimasuixianglu.beibao.hunhebeibao;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里再回顾一下三种传统背包：
 * 01 背包：强调每件物品「只能选择一次」。对其进行「一维空间优化」并不能降低时间复杂度，进行「一维空间优化」时要求「容量维度“从大到小”进行遍历」。
 * 完全背包：强调每件物品「可以选择无限次」。对其进行「一维空间优化」具有数学意义，可以将时间复杂度从 O(N*C*C)  降低到 O(n*c)，进行「一维空间优化」时要求「容量维度“从小到大”进行遍历」。
 * 多重背包：强调每件物品「只能选择有限次」。对其无论是进行「一维空间优化」还是「普通扁平化」都不能降低时间复杂度，要应用额外的优化手段：二进制优化 或 单调队列优化 。
 * 三种背包问题的「难易程度」是「递增」，但「重要程度」则是「递减」 的。
 * 虽然「多重背包」的 二进制优化 和 单调队列优化 都比较 trick。
 * 但其实「多重背包」并没有这么常见，以至于在 LeetCode 上我都没找到与「多重背包」相关的题目。
 * 同时三种背包问题都有「不超过」和「恰好」两种状态定义。
 * 这两种状态定义只在「初始化」上有区别。
 * 至于该如何初始化则要抓住 什么样的状态是合法的 ：
 * 对于「不超过」的状态定义：F[0][X] 均为合法值 0。
 * 代表不考虑任何物品，背包容量「不超过 X」的所取得的最大价值为 0。
 * 对于「恰好」的状态定义： 中只有 F[0][X] 为合法值 0，其他值均为“无效值”。
 * 代表不考虑任何物品，只有背包容量「恰好为 0」时所取得的最大价值为0 ；其他容量「恰好为非0 」是无法取得有效价值的（因为不考虑任何物品）。
 * 总的来说，三种背包问题都很经典（本质上都是组合优化问题），以至于「背包问题」直接成为了一类的动态规划模型。
 */
//给定物品数量 n 和背包容量 c。第 i 件物品的体积是 v[i]，价值是 w[i]，可用数量为 s[i] ：
//当 s[i] 为 -1 代表是该物品只能用一次
//当 s[i] 为 0 代表该物品可以使用无限次
//当 s[i] 为任意正整数则代表可用 s[i]  次
//求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
public class 混合背包 {
    public int maxValue(int N, int C, int[] w, int[] v, int[] s) {
        // 构造出物品的「价值」和「体积」列表
        List<Integer> worth = new ArrayList<>();
        List<Integer> volume = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int type = s[i];
            // 多重背包：应用「二进制优化」转换为 0-1 背包问题
            if (type > 0) {
                for (int k = 1; k <= type; k *= 2) {
                    type -= k;
                    worth.add(w[i] * k);
                    volume.add(v[i] * k);
                }
                if (type > 0) {
                    worth.add(w[i] * type);
                    volume.add(v[i] * type);
                }
                // 01 背包：直接添加
            } else if (type == -1) {
                worth.add(w[i]);
                volume.add(v[i]);
                // 完全背包：对 worth 做翻转进行标记
            } else {
                worth.add(-w[i]);
                volume.add(v[i]);
            }
        }
        // 使用「一维空间优化」方式求解三种背包问题
        int[] dp = new int[C + 1];
        for (int i = 0; i < worth.size(); i++) {
            int wor = worth.get(i);
            int vol = volume.get(i);
            // 完全背包：容量「从小到大」进行遍历
            if (wor < 0) {
                for (int j = vol; j <= C; j++) {
                    // 同时记得将 worth 重新翻转为正整数
                    dp[j] = Math.max(dp[j], dp[j - vol] - wor);
                }
                // 01 背包：包括「原本的 01 背包」和「经过二进制优化的完全背包」
                // 容量「从大到小」进行遍历
            } else {
                for (int j = C; j >= vol; j--) {
                    dp[j] = Math.max(dp[j], dp[j - vol] + wor);
                }
            }
        }
        return dp[C];
    }
}
