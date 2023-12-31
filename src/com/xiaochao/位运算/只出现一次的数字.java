package com.xiaochao.位运算;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * 把所有数字进⾏异或，成对⼉的数字就会变成 0，落单的数字和 0 做异或还是它本 身，所以最后异或的结果就是只出现⼀次的元素。
 */
public class 只出现一次的数字 {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n :nums){
            res ^= n;
        }
        return res;
    }
}
