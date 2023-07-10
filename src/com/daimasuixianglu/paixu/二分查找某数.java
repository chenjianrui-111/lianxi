package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给你一个n代表有一个长度为n的有序数组，然后给你一个k，你需要判断这个k是否在数组里面，
 * 如果存在就返回这个数从左往右第一次出现位置的下标，如果不存在输出-1
 * 输入描述:
 * 第一行输入一个n，k，其中n代表有n个数字，k代表你需要查找的元素
 * 第二行输入n个数
 * 输出描述:
 * 如果在数组里面找到了k，输出k所在的下标（注：如果数组中k出现了多次，请输出最小的下标！），如果k不在，就输出-1
 * 示例1
 * 输入
 * 7 0
 * 0 1 2 3 4 5 6
 * 输出
 * 0
 * 示例2
 * 输入
 * 8 9
 * 0 1 2 3 4 5 6 7
 * 输出
 * -1
 */
public class 二分查找某数 {
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
        int ans=binarySearch(nums,n,k);
        System.out.println(ans);
    }
    public static int binarySearch(int[] arr,int size,int k){
        int left=0;
        int right=arr.length-1;
        int mid;
        int ans=-1;
        while (left< right){
            mid=(right - left) / 2+ left;
            if (arr[mid] == k){
                ans=mid;
                right=mid;
            }else if (arr[mid] < k){
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return ans;
    }
}
