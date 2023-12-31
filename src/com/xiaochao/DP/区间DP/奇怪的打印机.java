package com.xiaochao.DP.区间DP;

/**
 * 有台奇怪的打印机有以下两个特殊要求：
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 * 示例 1：
 * 输入：s = "aaabbb"
 * 输出：2
 * 解释：首先打印 "aaa" 然后打印 "bbb"。
 * 示例 2：
 * 输入：s = "aba"
 * 输出：2
 * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 * 提示：
 * 1 <= s.length <= 100
 * s 由小写英文字母组成
 */

/**
 * 动态规划
 * 定义 f[l][r] 为将 [l, r] 这一段打印成目标结果所消耗的最小打印次数。
 * 不失一般性考虑 f[l][r] 该如何转移：
 * • 只染 l 这个位置，此时 f[l][r] = f[l + 1][r] + 1 • 不只染 l 这个位置，而是从 l 染到 k（需要确保首位相同 s[l] = s[k]）：
 * f[l][r] = f[l][k − 1] + f[k + 1][r], l < k <= r
 * 其中状态转移方程中的情况 2 需要说明一下：由于我们只确保 s[l] = s[k]，并不确保 [l, k] 之
 * 间的字符相同，根据我们基本分析可知，s[k] 这个点可由打印 s[l] 的时候一同打印，因此本身
 * s[k] 并不独立消耗打印次数，所以这时候 [l, k] 这一段的最小打印次数应该取 f[l][k − 1]，而
 * 不是 f[l][k]。 最终的 f[l][r] 为上述所有方案中取 min。
 */
public class 奇怪的打印机 {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] f= new int[n+1][n+1];
        for (int len = 1;len <= n;len++){
            for (int l = 0; l + len -1 < n; l++) {
                int r = l + len - 1;
                f[l][r] = f[l+1][r] + 1;
                for (int k = l + 1; k <= r ; k++) {
                    if (s.charAt(l) == s.charAt(k)){
                        f[l][r] = Math.min(f[l][r],f[l][k-1]+f[k+1][r]);
                    }
                }
            }
        }
        return f[0][n-1];
    }
}
//• 时间复杂度：O(n3) • 空间复杂度：O(n2)
