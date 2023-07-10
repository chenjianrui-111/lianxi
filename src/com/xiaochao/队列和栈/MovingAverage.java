package com.xiaochao.队列和栈;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 * 实现 MovingAverage 类：
 * MovingAverage(int size) 用窗口大小 size 初始化对象。
 * double next(int val) 计算并返回数据流中最后 size 个值的移动平均值。
 * 示例：
 * 输入：
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * 输出：
 * [null, 1.0, 5.5, 4.66667, 6.0]
 * 解释：
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // 返回 1.0 = 1 / 1
 * movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
 */
public class MovingAverage {
    // 维护定长队列和队列中的元素和
    int maxSize, queueSum = 0;
    Queue<Integer> q = new LinkedList<>();

    public MovingAverage(int size) {
        maxSize = size;
    }

    public double next(int val) {
        if (q.size() == maxSize) {
            // 给新元素腾位置
            int deletedVal = q.poll();
            queueSum -= deletedVal;
        }
        // 加入新元素
        q.offer(val);
        queueSum += val;

        return (double) queueSum / q.size();
    }
}
