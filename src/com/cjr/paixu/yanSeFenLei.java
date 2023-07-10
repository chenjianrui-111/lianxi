package com.cjr.paixu;

public class yanSeFenLei {
    public void sortColors(int[] nums) {
        int left=0,right=nums.length-1;
        for (int i = 0; i <=right ; i++) {
            if (nums[i]==0){
                swap(nums,i,left);
                left++;
            }
            if (nums[i]==2){
                swap(nums,i,right);
                right--;
                i--;
            }
        }
    }
    public void swap(int[]nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
