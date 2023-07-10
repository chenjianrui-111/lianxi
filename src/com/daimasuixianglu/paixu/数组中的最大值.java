package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 用递归方法找一个数组中的最大值
 * 输入描述:
 * 第一行输入一个n，代表数组的长度
 * 第二行，输入n个数
 * 输出描述:
 * 输出这个数组中的最大值
 * 示例1
 * 输入
 * 5
 * 1 2 3 4 5
 * 输出
 * 5
 */
public class 数组中的最大值 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(bf.readLine());
        String[] nums=bf.readLine().split(" ");
        int[] arr=new int[n];
        for (int i=0;i<nums.length;i++){
            arr[i]= Integer.parseInt(nums[i]);
        }
        int ans=process(arr,0,n-1);
        System.out.println(ans);
    }
    public static int process(int[] arr,int l,int r){
        if (l == r) return arr[l];
            int mid=l + ((r - l) >> 1);//中点
            int leftMax=process(arr,l,mid);
            int rightMax=process(arr,mid+1,r);
            return Math.max(leftMax,rightMax);
    }
}
