package com.daimasuixianglu.shuzu;

/**
 *给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
 * 将最终结果插入 nums 的前 k 个位置后返回 k 。
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 判题标准:
 * 系统会用下面的代码来测试你的题解:
 * int[] nums = [...]; // 输入数组
 * int[] expectedNums = [...]; // 长度正确的期望答案
 * int k = removeDuplicates(nums); // 调用
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * 如果所有断言都通过，那么您的题解将被 通过。
 * 思路
 * 首先注意数组是有序的，那么重复的元素一定会相邻。
 * 要求删除重复元素，实际上就是将不重复的元素移到数组的左侧。
 * 考虑用 2 个指针，一个在前记作 p，一个在后记作 q，算法流程如下：
 * 1.比较 p 和 q 位置的元素是否相等。
 * 如果相等，q 后移 1 位
 * 如果不相等，将 q 位置的元素复制到 p+1 位置上，p 后移一位，q 后移 1 位
 * 重复上述过程，直到 q 等于数组长度。
 * 返回 p + 1，即为新数组长度。
 */
public class 删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        if (nums.length==0 ||nums==null) return 0;
        int p=0;
        int q=1;
        while (q<nums.length){
            if (nums[p] !=nums[q]){
                nums[p+1]=nums[q];
                p++;
            }
            q++;
        }
        return p+1;
    }
}
