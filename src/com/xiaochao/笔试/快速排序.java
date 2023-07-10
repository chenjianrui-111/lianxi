package com.xiaochao.笔试;

import java.util.Arrays;

public class 快速排序 {
    static int[] arr = new int[]{1,4,6,3};
    public static void main(String[] args) {
        quickSort(arr,0,3);
    }
    public  static void quickSort(int[] arr,int left,int right){
        if (left < right){
            //把数据分块
            int pivot = partintion(arr,left,right);
            System.out.println(Arrays.toString(arr));
            quickSort(arr,left,pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }
    public static int partintion(int[]arr,int left,int right){
        int pivot=arr[left];
        while (left < right){
            while (left < right && arr[right] >= pivot){
              right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }
}
