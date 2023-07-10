package com.cjr.paixu;

public class 快速排序排序数组 {
    public int[] sortArray(int[] nums) {
        quickSort(nums);
        return nums;
    }
    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
    public static void quickSort(int[]arr,int start,int end){
        int index=getIndex(arr,start,end);
        quickSort(arr,start,index-1);
        quickSort(arr,index+1,end);
    }
    public static int getIndex(int []arr,int start,int end){
        //基准数据
        int temp=arr[start];
        while (start<end){
            while (start<end &&arr[end]>=temp){
                end--;
            }
            arr[start]=arr[end];
            while (start<end &&arr[start]<=temp){
                start++;
            }
            arr[end]=arr[start];
        }
        arr[start]=temp;
        return start;
    }

}
