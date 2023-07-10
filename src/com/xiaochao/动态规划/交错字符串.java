package com.xiaochao.动态规划;

import java.util.Arrays;

/**
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 */
public class 交错字符串 {
        public boolean isInterleave(String s1, String s2, String s3) {
            int m = s1.length(), n = s2.length();
            // 如果长度对不上，必然不可能
            if (m + n != s3.length()) {
                return false;
            }
            // 备忘录，其中 -1 代表未计算，0 代表 false，1 代表 true
            memo = new int[m + 1][n + 1];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return dp(s1, 0, s2, 0, s3);
        }

        int[][] memo;

        // 定义：计算 s1[i..] 和 s2[j..] 是否能组合出 s3[i+j..]
        boolean dp(String s1, int i, String s2, int j, String s3) {
            int k = i + j;
            // base case，s3 构造完成
            if (k == s3.length()) {
                return true;
            }
            // 查备忘录，如果已经计算过，直接返回
            if (memo[i][j] != -1) {
                return memo[i][j] == 1 ? true : false;
            }

            boolean res = false;
            // 如果，s1[i] 可以匹配 s3[k]，那么填入 s1[i] 试一下
            if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
                res = dp(s1, i + 1, s2, j, s3);
            }
            // 如果，s1[i] 匹配不了，s2[j] 可以匹配，那么填入 s2[j] 试一下
            if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
                res = res || dp(s1, i, s2, j + 1, s3);
            }
            // 如果 s1[i] 和 s2[j] 都匹配不了，则返回 false
            // 将结果存入备忘录
            memo[i][j] = res == true ? 1 : 0;

            return res;
        }

}
