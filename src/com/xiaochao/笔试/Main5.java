package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.Scanner;

public class Main5 {
   static int maxProfit_k_inf(int[] prices,int k) {
        if (prices.length == 0 ||k == 0) return 0;
        int[] buy = new int[k];
        int[] sell = new int[k];
       Arrays.fill(buy,-prices[0]);
       for (int i = 1; i <prices.length ; i++) {
           buy[0] = Math.max(buy[0],-prices[i]);
           sell[0] = Math.max(sell[0],buy[0] + prices[i]);
           for (int j = 1; j < k ; j++) {
               buy[j] = Math.max(buy[j],sell[j-1]-prices[i]);
               sell[j] =Math.max(sell[j],buy[j] + prices[i]);
           }
       }
       return sell[k-1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int k =sc.nextInt();
        int[] arr =new int[n];
        for (int i = 0; i < n ; i++) {
            arr[i] = sc.nextInt();
        }
        int ans = maxProfit_k_inf(arr,k);
        System.out.println(ans);
    }
}
