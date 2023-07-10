package com.daimasuixianglu.paixu;

public class Code09_MergeSort {
    public static void mergeSort(int[] arr){
        if (arr==null || arr.length <2){
            return;
        }
        process(arr,0,arr.length-1);
    }
    //请把arr[L...R]上排好序
    public static void process(int[] arr,int left,int right){
        if (left == right){
            return;
        }
        int mid=left+((right-left)>>1);
        process(arr, left, mid);
        process(arr,mid+1,right);
        merge(arr,left,mid,right);
    }
    public static void merge(int[] arr,int left,int mid,int right){
        int[] help=new int[right - left + 1];
        int i=0;//help下标
        int p1=left;
        int p2=mid+1;
        while (p1 <=mid && p2<=right){
            help[i++]=arr[p1] <=arr[p2] ?arr[p1++] : arr[p2++];
        }
        while (p1 <= mid){
            help[i++]=arr[p1++];
        }
        while (p2 <= right){
            help[i++] = arr[p2++];
        }
        for (int j = 0; j <help.length ; j++) {
            arr[left + i] =help[i];
        }
    }
}
