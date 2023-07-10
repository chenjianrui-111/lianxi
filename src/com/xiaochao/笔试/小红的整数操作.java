package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 小红拿到了一个数组，他每次可以进行如下操作：
 * 任选一个数，使其加k
 * 小红可以进行任意次这样的操作，他想知道最终数组有多少个数相同？
 * 第一行输入两个正整数n和k,分别代表着数组的长度，每次加的数值
 * 第二行输入 n 个正整数ai,代表小红拿到的数组
 * 1<=n<=105
 * 1<=k,ai<=109
 */
public class 小红的整数操作 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k =sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n ; i++) {
            arr[i] = sc.nextInt();
        }
        int[] ans = new int[n];
        for (int i = 0; i <arr.length ; i++) {
            ans[i] = arr[i%k]+1;
        }
        int max = 0;
        int count = 0;
        for (int i = 0; i <ans.length ; i++) {
            if (ans[i] >= max){
                count++;
                max = ans[i];
            }
        }
        System.out.println(count);
    }
}
//n, k = [int(x) for x in input().split()]
//a = [int(x) for x in input().split()]
//f = [0] * k
//for x in a:
//    f[x % k] += 1
//print(max(f))
