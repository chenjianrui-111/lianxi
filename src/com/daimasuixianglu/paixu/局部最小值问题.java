package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 定义局部最小的概念。arr长度为1时，arr[0]是局部最小。arr的长度为N(N>1)时，如果arr[0] < arr[1]，那么arr[0]是局部最小；
 * 如果arr[N-1]<arr[N-2],那么arr[N-1]是局部最小；如果0<i<N-1，既有arr[i] < arr[i-1]，又有arr[i] < arr[i + 1]，那么arr[i]
 * 是局部最小。给定无序数组arr,已知arr中任意两个相邻的数都不相等，只需要返回arr中任意一个局部最小出现的位置即可，如果不存在这个位置就输出-1。
 * 输入描述:
 * 第一行输入一个n代表下面需要输入n个数
 * 第二行输入n个元素，任意两个相邻的数都不相等
 * 输出描述:
 * 返回arr中任意一个局部最小出现的位置
 * 示例1
 * 输入
 * 6
 * 6 2 3 1 5 6
 * 输出
 * 1
 * 示例2
 * 输入
 * 1
 * 1
 * 输出
 * 0
 */
public class 局部最小值问题 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int size= Integer.parseInt(bf.readLine());
        String[] nums=bf.readLine().split(" ");
        int[] arr=new int[size];
        for (int i = 0; i <size ; i++) {
            arr[i]= Integer.parseInt(nums[i]);
        }
        int targetIndex = localMininum(arr);
        System.out.print(targetIndex);
    }
    public static int localMininum(int[] arr){
        int n = arr.length;
        if(n <= 1){
            return 0;
        }
        if(arr[0] < arr[1]){
            return 0;
        }
        if(arr[n-1] < arr[n-2]){
            return n-1;
        }
        int l = 0;
        int r = n-1;
        int m = -1;
        while(l<=r){
            m = l + (r-l)/2;
            if(arr[m] > arr[m+1]){
                l = m + 1;
            }else if(arr[m] > arr[m-1]){
                r = m - 1;
            }else{
                return m;
            }
        }
        return -1;
    }
}
