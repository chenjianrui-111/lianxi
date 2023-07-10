package com.daimasuixianglu.paixu;

public class Code03_InsertionSort {
    public static void insertionSort(int[] arr){
        if (arr == null || arr.length <2){
            return;
        }
        //0~0有序的
        //0~1想有序
        for (int i =1; i <arr.length ; i++) {//0~i做到有序
            for (int j = i-1; j >=0 && arr[j]>arr[j+1]; j--) {
                swap(arr,j,j+1);
            }
        }
    }
    public static void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
//时间复杂度O(n^2)
//常数项中比冒泡排序，选择排序优
