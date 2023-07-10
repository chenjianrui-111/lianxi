package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给你一个n代表有n个数字，然后你需要使用计数排序将这些数字从小到大排好。
 * 输入描述:
 * 第一行输入一个n，代表有n个数字
 * 第二行输入n个数
 * 输出描述:
 * 输出排序好后的n个数
 * 示例1
 * 输入
 * 4
 * 4 3 2 1
 * 输出
 * 1 2 3 4
 */
public class 计数排序 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] strs = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }
        radixSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void radixSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        radixSort(arr,0,arr.length - 1,maxbits(arr));
    }

    /**
     * 获取数组中最大值的位数
     * @param arr
     * @return
     */
    public static int maxbits(int[] arr){

        //取最小值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int res = 0;
        while (max != 0){
            res ++;
            max /= 10;
        }
        return res;
    }

    /**
     * 基数排序
     * @param arr 对于这个数值的（L，R）位置进行排序
     * @param L
     * @param R
     * @param digit 数组中最大的数字有多少位
     */
    public static void radixSort(int[] arr,int L,int R,int digit){

        final int radix = 10;
        int i = 0,j = 0;
        //对于辅助空间，有多少数准备多少空间
        int[] bucket = new int[R - L + 1];
        //对个十百位进行放桶排序操作
        for (int d = 1; d <= digit; d++) {
            //定义桶数组[0,1,2,...,9]
            int[] count = new int[radix];
            for (i = L; i <= R ; i++) {
                j = getDigit(arr[i],d);
                count[j]++;
            }
            //统计小于等于的数目
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            //放入桶中
            for (i = R; i >= L; i--){
                j = getDigit(arr[i],d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            //将这一轮排好序的放回原数组
            for (i = L, j = 0; i <= R;i++,j++){
                arr[i] = bucket[j];
            }
        }
    }
    /**
     * 取出个，十，百位上的数
     * @param x
     * @param d 1：取个位，2：取百位 。。。
     * @return 个，十，百位上的数
     */
    public static int getDigit(int x,int d){
        //pow(10,d-1):10^d-1
        return ((x / ((int)Math.pow(10,d - 1))) % 10);
    }
}
