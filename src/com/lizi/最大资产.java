package com.lizi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 最大资产 {
    public static void main(String[] args) throws IOException {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String[] nums=br.readLine().split(" ");
            int n=Integer.parseInt(nums[0]);
            int m=Integer.parseInt(nums[1]);
            String[] num=br.readLine().split(" ");
            int[] arr=new int[n];
            for (int i = 0; i <n ; i++) {
                arr[i] = Integer.parseInt(num[i]);
            }
            int[][] dp = new int[n][2];
            for (int i = 0; i < n; i++) {
                if (i - 1 == -1) {
                    // base case
                    dp[i][0] = 0;
                    dp[i][1] = -arr[i];
                    continue;
                }
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -arr[i]);
            }
            System.out.println( dp[n - 1][0]);
    }
}
