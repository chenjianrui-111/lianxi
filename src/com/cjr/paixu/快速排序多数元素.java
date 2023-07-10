package com.cjr.paixu;

import java.util.Arrays;

public class 快速排序多数元素 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return  nums[nums.length/2];
    }
}
