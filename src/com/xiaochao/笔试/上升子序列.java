package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 小明刚刚学会用动态规划方法解决最长上升子序列（LIS）问题。LIS 的 O(n2) 动态规划解法是这样的：设 dp[i] 为以 i 结尾的最长上升子序列的长度，首先令所有的 dp[i] = 1，转移由 dp[j] 转移过来，要求 j ∈ [1, i − 1] 且 aj < ai。转移方程就是dp[i] = max(dp[i], dp[j] + 1)。最后 max dp[i] 就是答案。
 *            1≤j<i                                    1≤i≤n
 * 明现在想知道：有多少个长度为 n 的整数序列，每个整数都在 [1, m] 之内，且这个序列的最长上升子序列的长度恰好等于3？由于答案可能会很大，求得的结果对998244353 取模即可。
 * 输入描述
 * 输入仅一行两个正整数 n, m。
 * 3 ≤ n ≤ 500, 1 ≤ m ≤ 10。
 * 输出描述
 * 输出一个整数，表示答案对 998244353取模后的结果。
 * 样例输入
 * 4 3
 * 样例输出
 * 9
 */
public class 上升子序列 {
    static long ans = 0,mod = 998244353;
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n,m;
        n = sc.nextInt();
        m = sc.nextInt();
        dp = new int[n];
        dfs(new int[n],0,n,m);
        System.out.println(ans%mod);
    }
    public static void dfs( int[] nums,int index,int n,int m ) {
        int length = lis(nums,index);
        if ( length>3 )
            return;
        if ( n-index<3-length )
            return;
        if ( index==n ) {
            if ( length==3 )
                ans++;
            return;
        }

        for (int i = 1; i <= m; i++) {
            nums[index] = i;
            dfs(nums,index+1,n,m);
        }
    }
    // 求最长上升子序列
    public static int lis( int[] nums,int cnt ) {
        if ( cnt<2 )
            return cnt;
        int res = 1;
        Arrays.fill(dp,1);
        for (int i = 1; i < cnt; i++) {
            for (int j = 0; j < i; j++) {
                if ( nums[i]>nums[j] ) {
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
