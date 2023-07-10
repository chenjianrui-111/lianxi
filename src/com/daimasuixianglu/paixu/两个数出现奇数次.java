package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定一个数字arr，其中只有两个数字出现了奇数次，其它数字都出现了偶数次，按照从小到大顺序输出这两个数。
 * 输入描述:
 * 第一行输入一个n，
 * 第二行输入n个数
 * 输出描述:
 * 输出出现奇数次的两个数，按照从小到大的顺序。
 * 示例1
 * 输入
 * 4
 * 1 1 2 3
 * 输出
 * 2 3
 * 示例2
 * 输入
 * 6
 * 11 22 11 23 23 45
 * 输出
 * 22 45
 */
public class 两个数出现奇数次 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(bf.readLine());
        String[] nums=bf.readLine().split(" ");
        int [] arr=new int[n];
        for (int i = 0; i <n ; i++) {
            arr[i]= Integer.parseInt(nums[i]);
        }
        int eor=0;
        for (int i = 0; i <arr.length ; i++) {
            eor ^=arr[i];
        }
        //eor = a ^ b
        //eor ！= 0
        //eor必然有一个位置是1
        int rightOne=eor & (~eor + 1);//提取出最右侧的1 00001000
        //                                             10010000
        //只想要当前位置为1的数，放入onlyOne中
        int onlyOne = 0;//eor'
        for (int cur : arr) {
            if ((cur & rightOne) != 0){
                onlyOne ^=cur;
            }
        }
        int small=onlyOne > (eor ^ onlyOne) ?(eor ^ onlyOne) :onlyOne;
        int big=onlyOne < (eor ^ onlyOne) ? (eor ^ onlyOne) : onlyOne;
        System.out.println(small +" " +big);
    }
}
