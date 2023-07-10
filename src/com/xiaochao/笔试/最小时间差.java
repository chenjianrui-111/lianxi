package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 最小时间差 {

//    public static int findMinDifference(String[] timePoints) {
//
//        int n = timePoints.length * 2;
//        int[] nums = new int[n];
//        List<String[]> timePointss = new LinkedList<>();
//        for (String timePoint : timePoints) {
//            String[] split = timePoint.split(":");
//            timePointss.add(split);
//        }
//        for (int i = 0, idx = 0; i < n / 2; i++, idx += 2) {
//            String[] ss = timePointss.get(i);
//            int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
//            nums[idx] = h * 60 + m;
//            nums[idx + 1] = nums[idx] + 1440;
//        }
//        Arrays.sort(nums);
//        int ans = nums[1] - nums[0];
//        for (int i = 0; i < n - 1; i++) ans = Math.min(ans, nums[i + 1] - nums[i]);
//        return ans;
//    }

    /**
     * 根据题意，我们需要找出「时钟盘」中的夹角最小的两个时间点，其中包括了分布在 00:00 左右两侧（横跨了一天）的时间点。
     * 因此，一种简单的方式是对于每个 timePoints[i]timePoints[i]，我们不仅记录「当天该时间点」对应的的偏移量，还记录「下一天该时间点」对应的偏移量。
     * 处理所有的 timePoints[i]timePoints[i] 后，对偏移量进行排序，遍历找到所有相邻元素之间的最小值。
     * @param timePoints
     * @return
     */
    public  int findMinDifference(List<String> timePoints) {
    int n = timePoints.size() * 2;
    int[] nums = new int[n];
    for (int i = 0, idx = 0; i < n / 2; i++, idx += 2) {
        String[] ss = timePoints.get(i).split(":");
        int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
        nums[idx] = h * 60 + m;
        nums[idx + 1] = nums[idx] + 1440;
    }
    Arrays.sort(nums);
    int ans = nums[1] - nums[0];
    for (int i = 0; i < n - 1; i++) ans = Math.min(ans, nums[i + 1] - nums[i]);
    return ans;
}
//不用两倍空间，最后用 1440-(nums[n - 1] - nums[0]) 数组首尾两个元素也要减一次
    public int findMinDifference2(List<String> timePoints) {
        int[] time = new int[timePoints.size()];
        for(int i = 0; i < timePoints.size(); i++) {
            String[] strs = timePoints.get(i).split(":");
            time[i] = Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
        }
        Arrays.sort(time);
        int minDiff = Integer.MAX_VALUE;
        for(int i = 1; i < time.length; i++) {
            minDiff = Math.min(time[i] - time[i - 1], minDiff);
        }
        minDiff = Math.min(24 * 60 - time[time.length - 1] + time[0], minDiff);
        return minDiff;
    }
}
