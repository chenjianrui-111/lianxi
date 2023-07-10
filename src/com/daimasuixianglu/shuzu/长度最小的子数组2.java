package com.daimasuixianglu.shuzu;

/**
 *滑动窗口
 * 接下来就开始介绍数组操作中另一个重要的方法：滑动窗口。
 * 所谓滑动窗口，就是不断的调节子序列的起始位置和终止位置，从而得出我们要想的结果。
 * 在本题中实现滑动窗口，主要确定如下三点：
 *
 * 窗口内是什么？
 * 如何移动窗口的起始位置？
 * 如何移动窗口的结束位置？
 * 窗口就是 满足其和 ≥ s 的长度最小的 连续 子数组。
 * 窗口的起始位置如何移动：如果当前窗口的值大于s了，窗口就要向前移动了（也就是该缩小了）。
 * 窗口的结束位置如何移动：窗口的结束位置就是遍历数组的指针，窗口的起始位置设置为数组的起始位置就可以了。
 */
public class 长度最小的子数组2 {
    public int minSubArrayLen(int s,int[] nums){
        int result=Integer.MAX_VALUE;
        int size=nums.length;
        int i=0; // 滑动窗口起始位置
        int sum=0;// 滑动窗口数值之和
        int subLength=0;// 滑动窗口的长度
        for (int j = 0; j <size ; j++) {
            sum+=nums[j];
            // 注意这里使用while，每次更新 i（起始位置），并不断比较子序列是否符合条件
            while (sum>s){
                subLength=j-i+1;// 取子序列的长度
                result=result<subLength?result:subLength;
                sum-=nums[i++];// 这里体现出滑动窗口的精髓之处，不断变更i（子序列的起始位置）
            }
        }
        // 如果result没有被赋值的话，就返回0，说明没有符合条件的子序列
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
