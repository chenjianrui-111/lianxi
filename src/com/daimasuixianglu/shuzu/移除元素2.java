package com.daimasuixianglu.shuzu;

/**
 * 双指针法
 * 双指针法（快慢指针法）： 通过一个快指针和慢指针在一个for循环下完成两个for循环的工作。
 */
public class 移除元素2 {
    public int removeElement(int[]nums,int val){
        int slowIndex=0;
        for (int fastindex = 0; fastindex <nums.length ; fastindex++) {
            if (val !=nums[fastindex]){
                nums[slowIndex++]=nums[fastindex];
            }
        }
        return slowIndex;
    }
}
//时间复杂度：$O(n)
//空间复杂度：$O(1)
