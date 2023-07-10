package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 你需要输入一个n，一个数k，然后输入一个长度为n个大小的数组arr，然后你需要在arr上找满足>=K的最左位置，并且输出这个位置，如果不存在这个位置就输出-1。
 * 输入描述:
 * 第一行输入一个n，k
 * 第二行输入长度为n个大小的数组arr
 * 输出描述:
 * 输出>=K的最左位置
 * 示例1
 * 输入
 * 5 1
 * 0 0 2 4 6
 * 输出
 * 2
 * 示例2
 * 输入
 * 5 9
 * 2 4 6 7 8
 * 输出
 * -1
 */
public class 查找某个位置 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        String arr[] = br.readLine().split(" ");
        int nums[] = new int[n];
        for (int i = 0; i <n ; i++) {
            nums[i]= Integer.parseInt(arr[i]);
        }
        int res = getLtK(nums, k);
        System.out.println(res);
    }
    private static int getLtK(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (k <= arr[mid]) {
                if (mid == 0 || arr[mid - 1] < k) {
                    return mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return arr[left] >= k ? left : -1;
    }
}
