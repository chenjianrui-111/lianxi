package com.daimasuixianglu.shuangzhizhen;

/**
 *
 */
public class 移除元素2 {
    public int removeElement(int[]nums,int val){
        int slowIndex=0;
        for (int fastIndex = 0; fastIndex <nums.length ; fastIndex++) {
            if (val!=nums[fastIndex]){
                nums[slowIndex++] = nums[fastIndex];
            }
        }
        return slowIndex;
    }
}
