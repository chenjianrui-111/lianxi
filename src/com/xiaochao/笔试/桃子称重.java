package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 小昱家的桃园丰收了！小昱采摘下来n个桃子，并对这些桃子称重，其中第i个桃子的重量为ai。
 * 小昱想选择一些桃子装成一箱后送给朋友，但是小昱不希望一箱桃子中有一些太大的桃子而影响整体美观。
 * 于是他给装箱工人提出了一个要求：一箱桃子中最重的桃子重量不能超过平均重量的k倍。
 * 装箱工人想知道在满足小昱要求的情况下，一箱最多能装多少个桃子。
 *   第一题桃子装箱
 *   n个桃子，每个重ai，尽可能多的装入一个箱子，要求箱子内最终的桃子重量不能超过平均重量的k倍，问最多能装多少个桃子？
 *   输入：
 *   第一行n和k。
 *   第二行n个数，对应每个桃子的重量。
 *   输出：最多能装的数量
 *
 *   例子：
 *   输入：
 *   5 2
 *   3 10 5 4 2
 *   输出：
 *   4
 */
public class 桃子称重 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k =sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = sc.nextLong();
        }
        Arrays.sort(arr);
        int count = 0;
        int sum = 0;
        int left = 0;
        for (int i = 0; i < arr.length ; i++) {
            sum += arr[i];
            while (arr[i] > sum * 1.0 /(i - left + 1) * k){
                count = Math.max(i-left,count);
                sum -= arr[i];
                left++;
            }
        }
        System.out.println(count);
    }
}
