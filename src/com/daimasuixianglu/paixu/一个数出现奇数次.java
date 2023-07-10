package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到这一个数？
 * 输入描述:
 * 第一行输入一个n代表，有个n个长度大小的数组
 * 第二行输入一个长度为n的数组
 * 输出描述:
 * 输出这个数组中出现奇数次的数
 * 示例1
 * 输入
 * 5
 * 1 1 1 2 1
 * 输出
 * 2
 */
public class 一个数出现奇数次 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(bf.readLine());
        String[] nums=bf.readLine().split(" ");
        int [] arr=new int[n];
        for (int i = 0; i <n ; i++) {
            arr[i]= Integer.parseInt(nums[i]);
        }
        int res=printOddTimesNum1(arr);
        System.out.println(res);
    }
    public static int printOddTimesNum1(int[] arr){
        int eor=0;
        for (int cur:arr){
            eor ^= cur;
        }
       return eor;
    }
}
