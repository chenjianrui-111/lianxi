package com.xiaochao.贪心;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给你 n 个任务和 m 个工人。每个任务需要一定的力量值才能完成，需要的力量值保存在下标从 0 开始的整数数组 tasks 中，第 i 个任务需要 tasks[i] 的力量才能完成。每个工人的力量值保存在下标从 0 开始的整数数组 workers 中，第 j 个工人的力量值为 workers[j] 。每个工人只能完成 一个 任务，且力量值需要 大于等于 该任务的力量要求值（即 workers[j] >= tasks[i] ）。
 * 除此以外，你还有 pills 个神奇药丸，可以给 一个工人的力量值 增加 strength 。你可以决定给哪些工人使用药丸，但每个工人 最多 只能使用 一片 药丸。
 * 给你下标从 0 开始的整数数组tasks 和 workers 以及两个整数 pills 和 strength ，请你返回 最多 有多少个任务可以被完成。
 * 示例 1：
 * 输入：tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
 * 输出：3
 * 解释：
 * 我们可以按照如下方案安排药丸：
 * - 给 0 号工人药丸。
 * - 0 号工人完成任务 2（0 + 1 >= 1）
 * - 1 号工人完成任务 1（3 >= 2）
 * - 2 号工人完成任务 0（3 >= 3）
 */
public class 你可以安排的最多任务数目 {

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int k = Math.min(tasks.length, workers.length);
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int left = 0;
        int right = k;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (check(tasks, workers, pills, strength, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] tasks, int[] workers, int pills, int strength, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        int idx = workers.length - 1;
        for (int i = k - 1; i >= 0; i--) {
            int t = tasks[i];
            while (idx >= 0 && workers[idx] + strength >= t) {
                list.offer(workers[idx]);
                idx--;
            }
            if (list.isEmpty()) {
                return false;
            }
            if (list.peek() >= t) {
                list.poll();
            } else {
                if (pills > 0) {
                    list.pollLast();
                    pills--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
