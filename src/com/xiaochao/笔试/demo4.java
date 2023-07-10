package com.xiaochao.笔试;

import java.util.Arrays;

public class demo4 {


    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        int[] output = processArray(input);
        System.out.println("输出结果: " + Arrays.toString(output));
    }

    public static int[] processArray(int[] arr) {
        for (int i = 1; i < arr.length; i++) { // 修改循环的初始值
            if (arr[i] - i == 3) {
                return new int[]{i - 1, arr[i]}; // 返回正确的索引和元素值
            }
        }

        if (arr.length < 2) {
            return arr;
        } else {
            return new int[]{arr[0] - 1, arr[1] + 1};
        }
    }

}
