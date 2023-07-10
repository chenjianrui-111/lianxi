package com.cjr.paixu;

public class 计数排序排序数组 {
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        if (nums.length < 1) {
            return nums;
        }
        //求出最大值和最小值
        int max=nums[0];
        int min=nums[0];
        for (int x:nums){
            if (max<x)max=x;
            if (min>x)min=x;
        }
        //设置presum数组长度，然后求出我们的前缀和数组
        //这里我们可以把求次数数组和前缀和数组用一个数组处理
        int []presum=new int[max-min+1];
        for (int x:nums) {
            presum[x-min]++;
        }
        for (int i = 1; i <presum.length ; i++) {
            presum[i]=presum[i-1]+presum[i];
        }
        //临时数组
        int []temp=new int[len];
        //遍历数组，开始排序，注意偏移量
        for (int i = len-1; i >=0 ; i--) {
            //查找presum字典，然后将其放到临时数组，注意偏移度
            int index=presum[nums[i]-min]-1;
            temp[index]=nums[i];
            //相应位置减1
            presum[nums[i]-min]--;
        }
        //copy回原数组
        System.arraycopy(temp,0,nums,0,len);
        return nums;
    }
}
