package com.cjr.paixu;

public class 排序数组插入排序 {
    public int[] sortArray(int[] nums) {
     insertSort(nums);
     return nums;
    }
    public void insertSort(int[]nums){
        //从第二个数开始，往前插入数字
        for (int i = 1; i <nums.length ; i++) {
            int currentNumber=nums[i];
            int j=i-1;
            //寻找插入的过程中，不断将比currentNumber大的数字后移
            while (j>=0 &&currentNumber<nums[j]){
                nums[j+1]=nums[j];
                j--;
            }
            // 两种情况会跳出循环：1. 遇到一个小于或等于 currentNumber 的数字，跳出循环，currentNumber 就   坐到它后面。
            // 2. 已经走到数列头部，仍然没有遇到小于或等于 currentNumber 的数字，也会跳出循环，此时 j 等于 -1，currentNumber 就坐到数列头部。
            nums[j + 1] = currentNumber;
        }
    }
}
