package com.leixing.动态规划.背包DP;

import java.util.Arrays;

/**
 * 完全背包（进阶）
 * 显然朴素版的完全背包进行求解复杂度有点高。
 * 这次我们还是按照同样的思路再进行一次推导，加强对这种「一维空间优化」方式的理解。
 * 从二维的状态转移方程入手进行分析（假设第 i 个数字为 t）：
 * 至此，我们得到了最终的状态转移方程：
 * f[j] = min(f[j], f[j - t] + 1)
 */
public class 完全平方數2 {
        public int numSquares(int n) {
            int[] f = new int[n + 1];
            Arrays.fill(f, 0x3f3f3f3f);
            f[0] = 0;
            for (int t = 1; t * t <= n; t++) {
                int x = t * t;
                for (int j = x; j <= n; j++) {
                    f[j] = Math.min(f[j], f[j - x] + 1);
                }
            }
            return f[n];
        }
}
//时间复杂度：共有 n * \sqrt{n}个状态需要转移，复杂度为 O(n * \sqrt{n})
//空间复杂度：O(n)
