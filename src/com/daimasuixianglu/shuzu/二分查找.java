package com.daimasuixianglu.shuzu;

/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * 提示：
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 *
 * 思路
 * 这道题目的前提是数组为有序数组，同时题目还强调数组中无重复元素，因为一旦有重复元素，使用二分查找法返回的元素下标可能不是唯一的，这些都是使用二分法的前提条件，当大家看到题目描述满足如上条件的时候，可要想一想是不是可以用二分法了。
 * 二分查找涉及的很多的边界条件，逻辑比较简单，但就是写不好。例如到底是 while(left < right) 还是 while(left <= right)，到底是right = middle呢，还是要right = middle - 1呢？
 * 大家写二分法经常写乱，主要是因为对区间的定义没有想清楚，区间的定义就是不变量。要在二分查找的过程中，保持不变量，就是在while寻找中每一次边界的处理都要坚持根据区间的定义来操作，这就是循环不变量规则。
 * 写二分法，区间的定义一般为两种，左闭右闭即[left, right]，或者左闭右开即[left, right)。
 * 下面我用这两种区间的定义分别讲解两种不同的二分写法。
 *解法一
 * 第一种写法，我们定义 target 是在一个在左闭右闭的区间里，也就是[left, right] （这个很重要非常重要）。
 *
 * 区间的定义这就决定了二分法的代码应该如何写，因为定义target在[left, right]区间，所以有如下两点：
 *
 * while (left <= right) 要使用 <= ，因为left == right是有意义的，所以使用 <=
 * if (nums[middle] > target) right 要赋值为 middle - 1，因为当前这个nums[middle]一定不是target，那么接下来要查找的左区间结束下标位置就是 middle - 1
 */
public class 二分查找 {
    public static int search(int []nums,int target){
        int left=0;
        int right=nums.length-1;// 定义target在左闭右闭的区间里，[left, right]
        while (left<=right){// 当left==right，区间[left, right]依然有效，所以用 <=
            int middle=left+(right-left)/2;// 防止溢出 等同于(left + right)/2
            if (nums[middle] > target){
                right=middle-1; // target 在左区间，所以[left, middle - 1]
            }else if (nums[middle] < target){
                left=middle+1;// target 在右区间，所以[middle + 1, right]
            }else {// nums[middle] == target
                return middle;// 数组中找到目标值，直接返回下标
            }
        }
        // 未找到目标值
        return -1;
    }

}
