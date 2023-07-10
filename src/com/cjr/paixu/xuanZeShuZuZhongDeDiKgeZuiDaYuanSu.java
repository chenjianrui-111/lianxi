package com.cjr.paixu;

public class xuanZeShuZuZhongDeDiKgeZuiDaYuanSu {
    public int findKthLargest(int[] nums, int k) {
        int maxIndex=0;
        for (int i = 0; i <k ; i++) {
            maxIndex=i;
            for (int j = i+1; j <nums.length ; j++) {
                if (nums[maxIndex]<nums[j]){
                    //更换最大值的下标
                    maxIndex=j;
                }
            }
            //将最大元素放到首位
            swap(nums,i,maxIndex);
        }
        return nums[k-1];
    }
    public void swap(int []nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
