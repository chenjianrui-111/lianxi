package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给你一个n代表有n个数字，给出这n个数字，然后你需要使用冒泡排序将这些数字从小到大排好。
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
public class 冒泡排序 {
    public static void main(String[] args) throws IOException {
        //input
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(bf.readLine());
        String[] nums=bf.readLine().split(" ");
        int []arr=new int[size];
        for (int i = 0; i <size ; i++) {
            arr[i]= Integer.parseInt(nums[i]);
        }

        //0~N-1 0~N-2 0~N-3
        for (int i =arr.length-1 ;i>0; i--) {//0~i
            for (int j = 0; j <i ; j++) {
                if (arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
        //output
        StringBuilder sb=new StringBuilder();
        for (int i:arr){
            sb.append(i+" ");
        }
        System.out.println(sb.toString());
    }
    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
