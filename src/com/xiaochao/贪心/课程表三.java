package com.xiaochao.贪心;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
 * 返回你最多可以修读的课程数目。
 * 示例 1：
 * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 输出：3
 * 解释：
 * 这里一共有 4 门课程，但是你最多可以修 3 门：
 * 首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
 * 第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
 * 第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
 * 第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
 */

/**
 * 先按课程的截止时间由小到大排序；
 * 再遍历课程，依次考察是否可选：
 * 1）用一个变量维持当前时间，选择后更新当前时间；
 * 2）用一个大根堆维持已被选中的课程，大根堆根据课程的持续时间组织；
 * 课程可选的条件是：
 * 1）当前时间+该课程持续时间<=该课程截止时间 ：此时，直接选修该课程；
 * 2）当前时间+该课程持续时间 > 该课程截止时间 && 该课程持续时间 < 堆顶课程的持续时间 ： 此时选择该课程，淘汰堆顶课程；
 */
public class 课程表三 {
    public static int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, ((o1, o2) -> o1[1] - o2[1]));
        int curTime = 0;
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int[] course : courses) {
            if (curTime + course[0] <= course[1]) { // 满足条件1），直接选修该课程
                maxHeap.add(course);
                curTime += course[0];
            } else if (!maxHeap.isEmpty() && maxHeap.peek()[0] > course[0]) { // 满足条件2），剔除堆顶再选修该课程
                int[] c = maxHeap.poll();
                curTime -= c[0];
                maxHeap.add(course);
                curTime += course[0];
            }
        }
        return maxHeap.size();
    }
}
