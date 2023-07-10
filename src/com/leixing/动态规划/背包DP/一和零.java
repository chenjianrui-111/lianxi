package com.leixing.动态规划.背包DP;

import com.sun.org.apache.regexp.internal.RE;

/**
 *给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 * 多维）01 背包
 * 通常与「背包问题」相关的题考察的是 将原问题转换为「背包问题」的能力。
 * 要将原问题转换为「背包问题」，往往需要从题目中抽象出「价值」与「成本」的概念。
 * 这道题如果抽象成「背包问题」的话，应该是：
 * 每个字符串的价值都是 1（对答案的贡献都是 1），选择的成本是该字符串中 1 的数量和 0 的数量。
 * 问我们在 1 的数量不超过 m，0 的数量不超过 n 的条件下，最大价值是多少。
 * 由于每个字符串只能被选一次，且每个字符串的选与否对应了「价值」和「成本」，求解的问题也是「最大价值」是多少。
 * 因此可以直接套用 01 背包的「状态定义」来做：
 * f[k][i][j] 代表考虑前 k 件物品，在数字 1 容量不超过 i，数字 0 容量不超过 j 的条件下的「最大价值」（每个字符串的价值均为 1）。
 * 有了「状态定义」之后，「转移方程」也很好推导：
 * f[k][i][j]=max(f[k−1][i][j],f[k−1][i−cnt[k][0]][j−cnt[k][1]]+1)
 * 其中 cnt数组记录的是字符串中出现的 01 数量。
 * 代码（为了方便理解，P1 将第一件物品的处理单独抽了出来，也可以不抽出来，只需要将让物品下标从 1 开始即可，见 P2）：
 */
public class 一和零 {

    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        // 预处理每一个字符包含 0 和 1 的数量
        int[][] cnt = new int[len][2];
        for (int i = 0; i < len; i++) {
            String str = strs[i];
            int zero = 0, one = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            cnt[i] = new int[]{zero, one};
        }
        // 处理只考虑第一件物品的情况
        int[][][] f = new int[len][m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                f[0][i][j] = (i >= cnt[0][0] && j >= cnt[0][1]) ? 1 : 0;
            }
        }
        // 处理考虑其余物品的情况
        for (int k = 1; k < len; k++) {
            int zero = cnt[k][0], one = cnt[k][1];
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    // 不选择第 k 件物品
                    int a = f[k - 1][i][j];
                    // 选择第 k 件物品（前提是有足够的 m 和 n 额度可使用）
                    int b = (i >= zero && j >= one) ? f[k - 1][i - zero][j - one] + 1 : 0;
                    f[k][i][j] = Math.max(a, b);
                }
            }
        }
        return f[len-1][m][n];
    }
}
//时间复杂度：预处理字符串的复杂度为 O(\sum_{i = 0}^{k - 1}len(strs[i]))
// 处理状态转移的 O(k * m * n)。整体复杂度为：O(k * m * n + \sum_{i = 0}^{k - 1}len(strs[i]))
//空间复杂度：O(k * m * n)
