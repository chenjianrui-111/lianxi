package com.cjr.paixu;

public class 希尔排序数组 {
    public int[] sortArray(int[] nums) {
        shellSort(nums);
        return nums;
    }
    public void shellSort(int[]nums){
        //增量序列
        for (int gap=nums.length/2;gap>0;gap=gap/2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i <nums.length ; i++) {
              //从currentNumber开始寻找位置
                int currentNumber=nums[i];
                //寻找该组前一个数字的索引
                int preIndex=i-gap;
                while (preIndex>=0 &&currentNumber<nums[preIndex]){
                    //向后挪位置
                    nums[preIndex+gap]=nums[preIndex];
                    preIndex-=gap;
                }
                //currentNumber的正确位置
                nums[preIndex+gap]=currentNumber;
            }
        }
    }

}
