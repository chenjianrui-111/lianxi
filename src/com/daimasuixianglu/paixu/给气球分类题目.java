package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 牛牛今天带来了一排气球，气球有n个，然后每一个气球里面都包含一个数字，牛牛是一个善于思考的人,于是他就想到了一个问题，
 * 牛牛随便给你一个值K，这个值在这些气球中不一定存在，聪明的你需要把气球中包含的数字是小于K的放到这排气球的左边，大于K的放到气球的右边，
 * 等于K的放到这排气球的中间，最终返回一个整数数组，其中只有两个值，分别是气球中包含的数字等于K的部分的左右两个下标值,如果气球中没有K这个数字就输出-1，-1。
 * 输入描述:
 * 第一行的输入为n和K，n代表有多少个气球，K代表牛牛选的数
 * 第二行需要输入n个大小的数组a，a[i]代表每个气球中放的数字，
 * 其中1 <=N,K<=1000,1<=a[i]<=1000。
 * 输出描述:
 * 一行，输出返回数组中的那两个值。
 * 示例1
 * 输入
 * 10 3
 * 1 4 0 0 3 1 5 3 1 1
 * 输出
 * 6 7
 * 说明
 * 气球按照题意处理后变成下面的样子
 * 1 1 0 0 1 1 3 3 5 4
 * 你会看出3 3的位置一个在6 一个在7
 * 示例2
 * 输入
 * 2 3
 * 6 8
 * 输出
 * -1 -1
 * 说明
 * 气球按照题意处理后变成下面的样子
 * 6 8
 * 但是由于上面的数组没有包含3所以输出-1 -1
 */
public class 给气球分类题目 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        String[] nums=bf.readLine().split(" ");
        int n=Integer.parseInt(nums[0]);
        int k=Integer.parseInt(nums[1]);
        String[] nums2=bf.readLine().split(" ");
        int[] arr=new int[n];
        for (int i = 0; i <n ; i++) {
            arr[i]= Integer.parseInt(nums2[i]);
        }
        int[]ans=partition(arr,0,n-1,k);
        for (int i=0;i<n;i++){
            System.out.println(i +" ");
        }
    }
    public static int[] partition(int[] arr,int L,int R,int p){
        int less=L - 1;// <区的右边界
        int more=R + 1;//>区的左边界
        int index=L;
        while (index < more){//L是当前数的下标
            if (arr[index] < p){
                swap(arr,++less,L++);
            }else if (arr[index] > p){
                swap(arr,--more,index);
            }else {
                index++;
            }
        }
        if (less + 1 > more - 1 ) {
            return new int[] {-1,-1};
        }
        //标记等于区域范围
        return new int[]{less+1,more-1};
    }
    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
/**
 *public static void main(String[] args) throws IOException {
 *         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 *         String[] arr = br.readLine().split(" ");
 *         int n = Integer.parseInt(arr[0]);
 *         int k = Integer.parseInt(arr[1]);
 *         int[] strs = new int[n];
 *         arr = br.readLine().split(" ");
 *         for (int i = 0; i < n; i++) {
 *             strs[i] = Integer.parseInt(arr[i]);
 *         }
 *         int[] res = partition(strs, 0, n-1, k);
 *         for (int i = 0; i < res.length; i++) {
 *             System.out.print(res[i] + " ");
 *         }
 *     }
 *
 *     public static int[] partition(int[] arr, int l, int r, int k) {
 *         int less = l - 1;
 *         int more = r + 1;
 *
 *         while (l < more) {
 *             if (arr[l] < k) {
 *                 swap(arr, ++less, l++);
 *             } else if (arr[l] > k) {
 *                 swap(arr, --more, l);
 *             } else {
 *                 l++;
 *             }
 *         }
 *         if (less + 1 > more - 1 ) {
 *             return new int[] {-1,-1};
 *         }
 *         return new int[] {less+1, more-1};
 *     }
 *
 *     public static void swap(int[] arr, int i, int j) {
 *         int temp = arr[i];
 *         arr[i] = arr[j];
 *         arr[j] = temp;
 *     }
 */
