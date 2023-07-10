package com.daimasuixianglu.shuzu;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 $O(1)$ 额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2, 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 解法一
 * 这个题目暴力的解法就是两层for循环，一个for循环遍历数组元素 ，第二个for循环更新数组。
 * 很明显暴力解法的时间复杂度是$O(n^2)
 */
public class 移除元素 {
    public int removeElement(int[]nums,int val){
        int size=nums.length;
        for (int i = 0; i <size ; i++) {
            if (nums[i]==val){
                for (int j=i+1;j<size;j++){
                    nums[j-1]=nums[j];
                }
                i--;
                size--;
            }
        }
        return size;
    }
}
