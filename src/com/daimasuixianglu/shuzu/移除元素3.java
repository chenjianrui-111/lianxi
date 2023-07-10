package com.daimasuixianglu.shuzu;

/**
 * 相向双指针方法，基于元素顺序可以改变的题目描述改变了元素相对位置，确保了移动最少元素
 * 时间复杂度：$O(n)$
 * 空间复杂度：$O(1)$
 */
public class 移除元素3 {
    public int removeElement(int[]nums,int val){
        int leftIndex=0;
        int rightIndex=nums.length-1;
        while (leftIndex<=rightIndex){
            //找到左边等于val的元素
            while (leftIndex<=rightIndex &&nums[leftIndex]!=val){
                ++leftIndex;
            }
            //找到右边不等于val的元素
            while (leftIndex<=rightIndex &&nums[rightIndex]==val){
                --rightIndex;
            }
            // 将右边不等于val的元素覆盖左边等于val的元素
            if (leftIndex < rightIndex) {
                nums[leftIndex++] = nums[rightIndex--];
            }
        }
        return leftIndex; // leftIndex一定指向了最终数组末尾的下一个元素
    }
}
