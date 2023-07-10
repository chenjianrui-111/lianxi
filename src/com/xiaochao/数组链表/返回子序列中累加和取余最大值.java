package com.xiaochao.数组链表;

import java.util.HashSet;
import java.util.TreeSet;

public class 返回子序列中累加和取余最大值 {
    public static int max1(int[] arr,int m){
        HashSet<Integer> set = new HashSet<>();
        process(arr,0,0,set);
        int max = 0;
        for (Integer sum :set){
            max = Math.max(max,sum % m);
        }
        return max;
    }
    //arr[index...]能形成多少个不同的累加和，全部存到set中
    //index 要 不要
    //sum -> arr[0..index-1] 所做的决定已经形成的累加和是多少
    public static void process(int[] arr,int index ,int sum,HashSet<Integer> set){
        if (index == arr.length){
            set.add(sum);
        }else {
            process(arr, index + 1, sum, set);
            process(arr, index + 1, sum + arr[index], set);
        }
    }

    public static int max2(int[] arr,int m){
        int sum = 0;
        int N =arr.length;
        for (int i = 0; i < N ; i++) {
            sum += arr[i];
        }
        boolean[][] dp =new boolean[N][sum + 1];
        for (int i = 0; i < N ; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum ; j++) {
                //dp[i][j]  1)不用arr[i]的数 0..i-1  j
                dp[i][j] = dp[i-1][j];
                //dp[i][j]  2)用arr[i]的数 0..i-1  j - arr[i]
                if (j - arr[i] >= 0){
                    dp[i][j] = dp[i][j] | dp[i-1][j-arr[i]];
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= sum ; j++) {
            if (dp[N-1][j]){
                ans = Math.max(ans,j%m);
            }
        }
        return ans;
    }
    //数据量小 可以继续优化
    public static int max3(int[] arr,int m){
        int N =arr.length;
        boolean[][] dp = new boolean[N][m];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < N ; i++) {
            for (int j = 1; j < m ; j++) {
                dp[i][j] = dp[i-1][j];
                int cur = arr[i] % m;
                if (j - cur >= 0){
                    dp[i][j] = dp[i][j] | dp[i - 1][j - cur];
                }
                if (m + j -cur < m){
                    dp[i][j] = dp[i][j] | dp[i - 1][m + j - cur];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m ; i++) {
            if (dp[N - 1][i]){
                ans = i;
            }
        }
        return ans;
    }

    //如果arr的累加和很大，m也很大
    //但是arr的长度相对不大
    public static int max4(int[] arr,int m){
        if (arr.length == 1) {
            return arr[0] % m;
        }
            int mid = (arr.length - 1) / 2;
            TreeSet<Integer> sortSet1 = new TreeSet<>();
            process4(arr,0,0,mid,m,sortSet1);
            TreeSet<Integer> sortSet2 = new TreeSet<>();
            process4(arr,mid + 1,0,arr.length-1,m,sortSet2);
            int ans = 0;
            for (Integer left : sortSet1) {
                // <=m-1-left 最近的 logN
                ans = Math.max(ans,left + sortSet2.floor(m  - 1 - left));
            }
            return ans;
        }

        //只在 arr[index...end] 范围上自由选择，每一种选择出来的累加和 %m 的结果，放入有序表中
    private static void process4(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
        if (index == end + 1){
            sortSet.add(sum % m);
        }else {
            process4(arr, index + 1, sum, end, m, sortSet);
            process4(arr, index + 1, sum + arr[index], end, m, sortSet);
        }
    }
}
