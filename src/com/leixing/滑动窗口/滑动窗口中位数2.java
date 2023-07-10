package com.leixing.滑动窗口;

import java.util.PriorityQueue;

/**
 * 优先队列（堆）解法
 * 从朴素解法中我们可以发现，其实我们需要的就是滑动窗口中的第 k / 2 小的值和第 (k - 1) / 2 小的值。
 * 我们知道滑动窗口求最值的问题，可以使用优先队列来做。
 * 但这里我们求的是第 k 小的数，而且是需要两个值。还能不能使用优先队列来做呢？
 * 我们可以维护两个堆：
 * 一个大根堆维护着滑动窗口中一半较小的值（此时堆顶元素为滑动窗口中的第 (k - 1) / 2 小的值）
 * 一个小根堆维护着滑动窗口中一半较大的值（此时堆顶元素为滑动窗口中的第 k / 2 小的值）
 * 滑动窗口的中位数就是两个堆的堆顶元素的平均值。
 * 实现细节：
 * 初始化时，先让 k 个元素直接入 right，再从 right 中倒出 k / 2 个到 left 中。这时候可以根据 left 和 right 得到第一个滑动窗口的中位值。
 * 开始滑动窗口，每次滑动都有一个待添加和待移除的数：
 * 2.1 根据与右堆的堆顶元素比较，决定是插入哪个堆和从哪个堆移除
 * 2.2 之后调整两堆的大小（确保只会出现 left.size() == right.size() 或 right.size() - left.size() == 1，对应了窗口长度为偶数或者奇数的情况）
 * 2.3 根据 left 堆 和 right 堆得到当前滑动窗口的中位值
 */
public class 滑动窗口中位数2 {

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int cnt = n - k + 1;
        double[] ans = new double[cnt];
        // 如果是奇数滑动窗口，让 right 的数量比 left 多一个
        PriorityQueue<Integer> left  = new PriorityQueue<>((a, b)->Integer.compare(b,a)); // 滑动窗口的左半部分
        PriorityQueue<Integer> right = new PriorityQueue<>((a,b)->Integer.compare(a,b)); // 滑动窗口的右半部分
        for (int i = 0; i < k; i++) right.add(nums[i]);
        for (int i = 0; i < k / 2; i++) left.add(right.poll());
        ans[0] = getMid(left, right);
        for (int i = k; i < n; i++) {
            // 人为确保了 right 会比 left 多，因此，删除和添加都与 right 比较（left 可能为空）
            int add = nums[i], del = nums[i - k];
            if (add >= right.peek()) {
                right.add(add);
            } else {
                left.add(add);
            }
            if (del >= right.peek()) {
                right.remove(del);
            } else {
                left.remove(del);
            }
            adjust(left, right);
            ans[i - k + 1] = getMid(left, right);
        }
        return ans;
    }
    void adjust(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        while (left.size() > right.size()) right.add(left.poll());
        while (right.size() - left.size() > 1) left.add(right.poll());
    }
    double getMid(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() == right.size()) {
            return (left.peek() / 2.0) + (right.peek() / 2.0);
        } else {
            return right.peek() * 1.0;
        }
    }
}
/**
 *时间复杂度：调整过程中堆大小最大为 k，堆操作中的指定元素删除复杂度为 O(k)；窗口数量最多为 n。
 * 整体复杂度为 O(n * k)
 * 空间复杂度：最多有 n 个元素在堆内。复杂度为 O(n)
 */
