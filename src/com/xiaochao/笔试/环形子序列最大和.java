package com.xiaochao.笔试;

import java.util.Scanner;

public class 环形子序列最大和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n ; i++) {
            arr[i] = sc.nextInt();
        }
        int res = maxSubarrySumCircular(arr);
        System.out.println(res);
    }
//    public static int maxSubarrySumCircular(int[] Arr){
//        int N = Arr.length;
//        int ans = Arr[0],cur = Arr[0];
//        for (int i = 1; i < N ; i++) {
//            cur = Arr[i] + Math.max(cur,0);
//            ans = Math.max(ans,cur);
//        }
//        int[] rightsums = new int[N];
//        rightsums[N - 1] = Arr[N - 1];
//        for (int i = N - 2; i >= 0 ; --i) {
//            rightsums[i] = rightsums[i + 1] + Arr[i];
//        }
//        int[] maxRight = new int[N];
//        rightsums[N-1] = Arr[N - 1];
//        for (int i = N - 2; i >= 0 ; --i) {
//            maxRight[i] =Math.max(maxRight[i+1],rightsums[i]);
//        }
//        int leftSum = 0;
//        for (int i = 0; i <N - 2 ; ++i) {
//            leftSum += Arr[i];
//            ans = Math.max(ans,leftSum + maxRight[i + 2]);
//        }
//        return ans;
//    }

    public static int maxSubarrySumCircular(int[] Arr){
        int total = 0,maxSum = Arr[0],curMax = 0,minSum = Arr[0],curMin = 0;
        for (int a : Arr) {
            curMax = Math.max(curMax + a,a);
            maxSum = Math.max(maxSum,curMax);
            curMin = Math.min(curMin + a,a);
            minSum = Math.min(minSum,curMin);
            total += a;
        }
        return maxSum > 0 ? Math.max(maxSum,total - minSum): maxSum;
    }
}
