package com.xiaochao.队列和栈;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 设计一个敲击计数器，使它可以统计在过去 5 分钟内被敲击次数。（即过去 300 秒）
 * 您的系统应该接受一个时间戳参数 timestamp (单位为秒)，并且您可以假定对系统的调用是按时间顺序进行的 (即 timestamp 是单调递增的)。几次撞击可能同时发生。
 * 实现 HitCounter 类:
 * HitCounter() 初始化命中计数器系统。
 * void hit(int timestamp) 记录在 timestamp (单位为秒) 发生的一次命中。在同一个 timestamp 中可能会出现几个点击。
 * int getHits(int timestamp) 返回 timestamp 在过去 5 分钟内 (即过去 300 秒) 的命中次数。
 * 示例 1:
 * 输入：
 * ["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
 * [[], [1], [2], [3], [4], [300], [300], [301]]
 * 输出：
 * [null, null, null, null, 3, null, 4, 3]
 * 解释：
 * HitCounter counter = new HitCounter();
 * counter.hit(1);// 在时刻 1 敲击一次。
 * counter.hit(2);// 在时刻 2 敲击一次。
 * counter.hit(3);// 在时刻 3 敲击一次。
 * counter.getHits(4);// 在时刻 4 统计过去 5 分钟内的敲击次数，函数返回 3。
 * counter.hit(300);// 在时刻 300 敲击一次。
 * counter.getHits(300); // 在时刻 300 统计过去 5 分钟内的敲击次数，函数返回 4。
 * counter.getHits(301); // 在时刻 301 统计过去 5 分钟内的敲击次数，函数返回 3。
 */
public class 敲击计数器 {
    Queue<Integer> q = new LinkedList<>();

    public void hit(int timestamp) {
        q.offer(timestamp);
    }

    public int getHits(int timestamp) {
        // 留队列中最近 300 秒的数据即可
        while (!q.isEmpty() && timestamp - q.peek() >= 300) {
            q.poll();
        }
        return q.size();
    }
}
