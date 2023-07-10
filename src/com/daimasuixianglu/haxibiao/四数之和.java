package com.daimasuixianglu.haxibiao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 
 * [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 0 <= a, b, c, d < n
 a、b、c 和 d 互不相同
 nums[a] + nums[b] + nums[c] + nums[d] == target
 * 四数之和，和15.三数之和是一个思路，都是使用双指针法, 基本解法就是在15.三数之和 的基础上再套一层for循环。
 * 但是有一些细节需要注意，例如：不要判断nums[k] > target 就返回了，三数之和 可以通过 nums[i] > 0 就返回了，因为 0 已经是确定的数了，四数之和这道题目 target是任意值。（大家亲自写代码就能感受出来）
 * 15.三数之和的双指针解法是一层for循环num[i]为确定值，然后循环内有left和right下表作为双指针，找到nums[i] + nums[left] + nums[right] == 0。
 * 四数之和的双指针解法是两层for循环nums[k] + nums[i]为确定值，依然是循环内有left和right下表作为双指针，找出nums[k] + nums[i] + nums[left] + nums[right] == target的情况，三数之和的时间复杂度是O(n^2)，四数之和的时间复杂度是O(n^3) 。
 */
public class 四数之和 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i <nums.length ; i++) {
            if (i>0 && nums[i]==nums[i-1]){
                continue;
            }
            for (int j = i+1; j <nums.length ; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left<right){
                    int sum=nums[i]+nums[j]+nums[left]+nums[right];
                    if (sum>target){
                        right--;
                    }
                    else if (sum<target){
                        left++;
                    }
                    else {
                        res.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));

                        while (left<right &&nums[right]==nums[right-1]) right--;
                        while (left<right &&nums[left]==nums[left+1]) left++;

                        left++;
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
