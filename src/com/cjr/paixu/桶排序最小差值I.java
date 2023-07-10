package com.cjr.paixu;

public class 桶排序最小差值I {
    public int smallestRangeI(int[] nums, int k) {
        int max=nums[0],min=nums[0];
        if (nums==null &&nums.length==0)
            return 0;
        for (int i = 1; i <nums.length ; i++) {
            if (max<nums[i]){
                max=nums[i];
            }
            if (min>nums[i]){
                min=nums[i];
            }
        }
        int range=(max-k)+(min+k);
        return Math.max(range,0);
    }
}
