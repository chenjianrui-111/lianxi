package com.xiaochao.前缀和;

/**
 * 给定一个整数数组  nums，处理以下类型的多个查询:
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 * 示例 1：
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 * 提示：
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= i <= j < nums.length
 * 最多调用 104 次 sumRange 方法
 */
public class 区域和检索数组不可变 {
    int[] sum;

    public 区域和检索数组不可变(int[] nums) {
        int n = nums.length;
// 前缀和数组下标从 1 开始，因此设定长度为 n + 1（模板部分）
        sum = new int[n + 1];
// 预处理除前缀和数组（模板部分）
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
    }

    public int sumRange(int i, int j) {
// 求某一段区域和 [i, j] 的模板是 sum[j] - sum[i - 1]（模板部分）
// 但由于我们源数组下标从 0 开始，因此要在模板的基础上进行 + 1
        // 计算 [i, j] 结果
        i++;
        j++;
        return sum[j] - sum[i - 1];
    }
}
//• 时间复杂度：预处理前缀和数组需要对原数组进行线性扫描，复杂度为 O(n)，计 算结果复杂度为 O(1)。
// 整体复杂度为 O(n) • 空间复杂度：O(n)
