package com.xiaochao.笔试;

/**
 * 四个人拿电筒过河，1,2,5,10分钟 只有一个手电筒 每次只能两个人过河 求过河时间最短
 * 解题思路：
 * 首先将四个人的过河时间按照从小到大的顺序进行排序。这样可以方便我们处理最快和次快的人。
 * 对于剩余的人数大于3的情况，我们可以使用贪心策略进行选择。每次迭代中，我们都会考虑两种可能的方案：
 * a. 最快的两个人分别带最慢的两个人过河，然后最快的人返回，再次快的人带次慢的人过河。
 * b. 最快的人带最慢的人过河，然后最快的人返回，再带次慢的人过河，最后最快的人再次返回。
 * 我们会选择这两种方案中总时间较短的那个。并将累计时间加上这个最短时间。
 * 每次迭代后，我们需要移除已经过河的最慢的两个人。然后继续下一次迭代。
 * 当剩下的人数为3时，我们可以直接计算最后三个人的过河时间：最快的两个人过河，次快的人返回，最后最慢的人过河。将这个时间加到累计时间上。
 * 迭代结束后，累计时间就是四个人过河的最短时间。
 */
public class 过河时间 {

    public static void main(String[] args) {
        int[] crossingTimes = {1, 2, 5, 10};
        System.out.println("最短过河时间: " + getShortestCrossingTime(crossingTimes));
    }

    public static int getShortestCrossingTime(int[] crossingTimes) {
        int totalTime = 0;

        // 当只剩下最后两个人时，直接将时间相加即可
        if (crossingTimes.length == 2) {
            return crossingTimes[1];
        }

        // 当只剩下最后三个人时，时间为最快的两个人过河和次快的人返回
        if (crossingTimes.length == 3) {
            return crossingTimes[0] + crossingTimes[1] + crossingTimes[2];
        }

        // 按时间从小到大排序
        int[] sortedTimes = crossingTimes.clone();
        java.util.Arrays.sort(sortedTimes);

        // 使用贪心算法求解
        while (sortedTimes.length > 3) {
            int totalTime1 = sortedTimes[1] * 2 + sortedTimes[0] + sortedTimes[sortedTimes.length - 1];
            int totalTime2 = sortedTimes[sortedTimes.length - 1] + sortedTimes[0] * 2 + sortedTimes[sortedTimes.length - 2];

            int minTime = Math.min(totalTime1, totalTime2);
            totalTime += minTime;

            // 移除已过河的最慢的两个人
            sortedTimes = java.util.Arrays.copyOf(sortedTimes, sortedTimes.length - 2);
        }

        // 计算最后三个人过河的时间
        totalTime += getShortestCrossingTime(sortedTimes);

        return totalTime;
    }


}
