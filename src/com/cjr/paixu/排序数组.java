package com.cjr.paixu;

public class 排序数组 {
    public int[] sortArray(int[] nums) {
        selectionSort2(nums);
        return nums;
    }
    public void selectionSort2(int[] nums){
        int minIndex,maxIndex;
        //i只需要遍历一半
        for (int i = 0; i <nums.length/2 ; i++) {
            minIndex=i;
            maxIndex=i;
            for (int j = i+1; j <nums.length-i ; j++) {
                if (nums[minIndex]>nums[j]){
                    minIndex=j;
                }
                if (nums[maxIndex]<nums[j]){
                    maxIndex=j;
                }
            }
            if (minIndex==maxIndex) break;
            swap(nums,i,minIndex);
            if (maxIndex==i) maxIndex=minIndex;
            swap(nums,nums.length-i-1,maxIndex);
        }
    }
    public void swap(int []nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
