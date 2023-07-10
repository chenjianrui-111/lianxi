package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 请实现有重复数字的有序数组的二分查找。
 * 输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加1。
 * 输入描述:
 * 第一行两个正整数n，v(1<=n<=100000，1<=v<=100000)，分别表示数组长度与查找值。
 * 第二行n个正整数a1,a2,...,an(1<=a1<=a2<=...<=an<=n)表示有序数组。
 * 输出描述:
 * 输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加一。
 * 示例1
 * 输入
 * 5 4
 * 1 2 4 4 5
 * 输出
 * 3
 * 示例2
 * 输入
 * 5 4
 * 1 2 3 3 5
 * 输出
 * 5
 * 示例3
 * 输入
 * 5 4
 * 1 2 2 3 3
 * 输出
 * 6
 */
public class 二分查找 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        String[] str=bf.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int target = Integer.parseInt(str[1]);
        String[] nums=bf.readLine().split(" ");
        int []arr=new int[n];
        for (int i = 0; i <n ; i++) {
            arr[i]=Integer.parseInt(nums[i]);
        }

        int left = 0;
        int right = n-1;
        int mid = -1;
        int targetIndex = n;
        while(left <= right){
            mid = left + ((right - left)>>1);

            if(arr[mid] < target) left = mid+1;
            if(arr[mid] >= target) {
                right = mid-1;
                targetIndex = mid;
            }
        }

        //targetIndex = (arr[mid] == target) ? mid : n +1

        System.out.print(targetIndex+1);
    }
}
