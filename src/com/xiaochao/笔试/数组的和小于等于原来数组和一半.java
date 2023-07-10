package com.xiaochao.笔试;

import java.util.PriorityQueue;

/**
 * // 第一题：将A中的数字减少一半，最少需要多少次后，数组的和小于等于原来数组和的一半
 * // 思路：贪心，大根堆
 */
public class 数组的和小于等于原来数组和一半 {

    public int solution(int[] A) {
        PriorityQueue<Double> heap = new PriorityQueue<>((a, b) -> {
            if (a.equals(b)) return 0;
            else return a > b ? -1 : 1;
        });
        int sum = 0;
        for (int n : A) {
            sum += n;
            heap.offer((double) n);
        }
        int res = 0;
        double reduce = 0;
        while (reduce * 2 < sum) {
            double cur = heap.poll();
            cur /= 2;
            reduce += cur;
            heap.offer(cur);
            res++;
        }
        return res;
    }
}
