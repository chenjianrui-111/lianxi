package com.daimasuixianglu.paixu;

public class Code01_SelectionSort {
    public static void selectionSort(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }
        for (int i = 0; i <arr.length ; i++) {//i~N-1
            int minIndex=i;
            for (int j = i+1; j <arr.length ; j++) {//i~N-1上找最小值下标
                minIndex=arr[j] <arr[minIndex] ? j :minIndex;
            }
            swap(arr,i,minIndex);
        }
    }
    public static void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
//时间复杂度O(n^2)
//空间复杂度O(1)
