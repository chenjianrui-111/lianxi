package com.xiaochao.前缀和;

/**
 * 给你一个 下标从 0 开始 的正整数数组 w ，其中 w[i] 代表第 i 个下标的权重。
 * 请你实现一个函数 pickIndex ，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）选出并返回一个下标。选取下标 i 的 概率 为 w[i] / sum(w) 。
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 * 示例 1：
 * 输入：
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * 输出：
 * [null,0]
 * 解释：
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
 * 示例 2：
 * 输入：
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * 输出：
 * [null,1,1,1,1,0]
 * 解释：
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 1
 * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * 诸若此类。
 * 提示：
 * 1 <= w.length <= 104
 * 1 <= w[i] <= 105
 * pickIndex 将被调用不超过 104 次
 */

/**
 * 前缀和 + 二分
 * 根据题意，权重值 w[i] 可以作为 pickIndex 调用总次数为 ∑i=0 w.length−1 w[i] 时，下标 i 的返回次数。
 * 随机数的产生可以直接使用语言自带的 API，剩下的我们需要构造一个分布符合权重的序列。
 * 由于 1 <= w[i] <= 105，且 w 长度为 104，因此直接使用构造一个有 w[i] 个的 i 的数字会
 * MLE。 我们可以使用「前缀和」数组来作为权重分布序列，权重序列的基本单位为 1。
 * 一个长度为 n 的构造好的「前缀和」数组可以看是一个基本单位为 1 的 [1, sum[n − 1]] 数 轴。
 * 使用随机函数参数产生 [1, sum[n − 1]] 范围内的随机数，通过「二分」前缀和数组即可找到
 * 分布位置对应的原始下标值
 */
public class 按权重随机选择 {
    int[] sum;
    public 按权重随机选择(int[] w) {
        int n = w.length;
        sum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            sum[i] = sum[i - 1] + w[i -1];
        }
    }

    public int pickIndex() {
        int n = sum.length;
        int t = (int) ((Math.random() * sum[n - 1]) + 1);
        int l = 1, r =n - 1;
        while (l < r){
            int mid = l + r >> 1;
            if (sum[mid] >= t) r = mid;
            else l = mid + 1;
        }
        return r - 1;
    }
}
//• 时间复杂度： Solution 类的构造方法整体复杂度为 O(n)； pickIndex 的复杂度 为 O(log n) • 空间复杂度：O(n)
