package com.xiaochao.DP.数位dp;

/**
 * 如果一个正整数每一个数位都是 互不相同 的，我们称它是 特殊整数 。
 * 给你一个 正 整数 n ，请你返回区间 [1, n] 之间特殊整数的数目。
 * 示例 1：
 * 输入：n = 20
 * 输出：19
 * 解释：1 到 20 之间所有整数除了 11 以外都是特殊整数。所以总共有 19 个特殊整数。
 */

import java.util.Arrays;

/**
 * 提供一个一般化的数位 DP 模板。
 * 将 n 转换成字符串 s，定义 f(i,mask, isLimit,isNum) 表示构造从高到低第 i 位及其之后数位的合法方案数，其余参数的含义为：
 * mask 表示前面选过的数字集合，换句话说，第 i 位要选的数字不能在mask 中。
 * isLimit 表示当前是否受到了 n 的约束。若为真，则第 i 位填入的数字至多为 s[i]，否则可以是 9。如果在受到约束的情况下填了 s[i]，那么后续填入的数字仍会受到 n 的约束。
 * isNum 表示 i 前面的数位是否填了数字。若为假，则当前位可以跳过（不填数字），或者要填入的数字至少为 1；若为真，则要填入的数字可以从 0 开始
 * log(n) * 1024 * 10
 */
public class 统计特殊整数 {
    char s[];
    int dp[][];

    //数位 dp
    public int countSpecialNumbers(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        dp = new int[m][1 << 10];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        return f(0, 0, true, false);
    }

    int f(int i, int mask, boolean isLimit, boolean isNum) {
        if (i == s.length) return isNum ? 1 : 0;
        if (!isLimit && isNum && dp[i][mask] >= 0) return dp[i][mask];
        int res = 0;
        if (!isNum) res = f(i + 1, mask, false, false); // 可以跳过当前数位
        for (int d = isNum ? 0 : 1, up = isLimit ? s[i] - '0' : 9; d <= up; ++d) // 枚举要填入的数字 d
            if ((mask >> d & 1) == 0) // d 不在 mask 中
                res += f(i + 1, mask | (1 << d), isLimit && d == up, true);
        if (!isLimit && isNum) dp[i][mask] = res;
        return res;
    }
}
