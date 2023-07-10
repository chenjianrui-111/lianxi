package com.xiaochao.笔试;

public class 归并排序和插入排序 {

    public int[] mergeSort (int[] nums, int k) {
        // write code here
        sort(nums,0,nums.length - 1);
        swap(nums,0,nums.length - 1);
        return nums;
    }
    public static void sort(int[] arr,int left,int right){
        if (left == right){
            return;
        }
        int mid = left + (right - left) / 2;
        sort(arr,left,mid);
        sort(arr,mid + 1,right);
        merge(arr,left,mid+1,right);
    }
    static void merge(int[] arr,int leftPtr,int rightPtr,int rightBound){
        int mid = rightPtr - 1;
        int[] temp = new int[rightBound - leftPtr + 1];
        int i = leftPtr;
        int j= rightPtr;
        int k = 0;
        while (i <= mid && j<= rightBound){
            if (arr[i] <= arr[j]){
                temp[k] = arr[i];
                i++;
                k++;
            }else {
                temp[k] = arr[j];
                j++;
                k++;
            }
        }
        while ( i <= mid){
            temp[k++] = arr[i++];
        }
        while ( j <= rightBound){
            temp[k++] = arr[j++];
        }
        for (int m = 0; m < temp.length;m++) arr[leftPtr + m] = temp[m];
    }
    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
