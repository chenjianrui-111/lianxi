package com.daimasuixianglu.paixu;

public class Code08_GetMax {
    public static int getMax(int[] arr){
        if (arr == null || arr.length == 0){
            //给用户报错
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr,int L,int R){
        if (L == R){// arr[L...R]范围上只有一个数，直接返回base case
            return arr[L];
        }
        int mid=L + ((R - L) >> 1);//中点
        int leftMax=process(arr,L,mid);
        int rightMax=process(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }
}
