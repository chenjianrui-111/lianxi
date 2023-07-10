package com.cjr.paixu;

import java.util.Arrays;

public class jishupaixu数组拆分I {
    public int arrayPairSum(int[] nums) {
        int ans=0;
        Arrays.sort(nums);
        for (int i = 0; i <nums.length-1 ; i+=2) {
            ans+=nums[i];
        }
        return ans;
    }
}
