package com.xiaochao.动态规划;

import java.util.Arrays;
import java.util.Comparator;

public class 俄罗斯套娃信封 {
    // envelopes = [[w, h], [w, h]...]
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 按宽度升序排列，如果宽度⼀样，则按⾼度降序排列
        Arrays.sort(envelopes, new Comparator<int[]>()
        {
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ?
                        b[1] - a[1] : a[0] - b[0];
            }
        });
        // 对⾼度数组寻找 LIS
        int[] height = new int[n];
        for (int i = 0; i < n; i++)
            height[i] = envelopes[i][1];
        return lengthOfLIS(height);
    }
    int lengthOfLIS(int[] height){
        int[] dp =new int[height.length];
        Arrays.fill(dp,1);
        for (int i = 0; i <height.length ; i++) {
            for (int j = 0; j < i ; j++) {
                if (height[i] > height[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
