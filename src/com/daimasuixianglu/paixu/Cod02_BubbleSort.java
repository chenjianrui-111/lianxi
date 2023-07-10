package com.daimasuixianglu.paixu;

public class Cod02_BubbleSort {
    public static void bubbleSort(int[] arr){
        if (arr==null ||arr.length<2){
            return;
        }
        //0~N-1 0~N-2 0~N-3
        for (int i =arr.length-1 ;i>0; i--) {//0~i
            for (int j = 0; j <i ; j++) {
                if (arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }
    public static void swap(int[] arr,int i,int j){
        arr[i]=arr[i] ^ arr[j];
        arr[j]=arr[i] ^ arr[j];
        arr[i]=arr[i] ^ arr[j];
    }
}
//时间复杂度O(n^2)
//空间复杂度O(1)
