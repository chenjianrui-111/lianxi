package com.cjr.paixu;

public class 移动零 {
    public void moveZeroes(int[] nums) {
        int i=0;
        for (int j = 0; j <nums.length ; j++) {
            if (j!=0){
                int temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
                i++;
            }
        }
    }
}
