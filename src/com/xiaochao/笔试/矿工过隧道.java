package com.xiaochao.笔试;

import java.util.Arrays;

public class 矿工过隧道 {
    /**
     * 这是一个经典的算法问题，可以使用贪心算法来解决。具体思路如下：
     * 首先将矿工按过隧道的时间从小到大排序，即 a->1, b->3, c->6, d->8, e->12。
     * 接下来考虑如何安排每一次通过隧道的人员组合，使得总时间最短。
     * 假设当前有a、b、c、d、e五名矿工待在此侧，为了让时间最短，需要考虑选择用时最短的两人去对岸，因为手电筒的时间限制是30秒，所以需要选择两人中过隧道时间较短的人先过去，这样可以保证如果其中一个人还没走到对面就必须返回，另一个人也只需要等待较短时间，这样才能保证总时间最短。
     * 具体实现方法如下，假设矿工对应的时间已经排序：
     * 让a和b先过去，花费时间为3，现在此侧为c、d、e。
     * 让a回来，花费时间为1，现在此侧为a、c、d、e。
     * 让e和d先过去，花费时间为12，现在此侧为a、c。
     * 让b回来，花费时间为3，现在此侧为a、b、c。
     * 让a和c先过去，花费时间为6，现在此侧为b。
     * 让a回来，花费时间为1，现在此侧为a、b。
     * 让a和b先过去，花费时间为3，完成。
     * @param miners
     * @return
     */
    public static int shortestTime(int[] miners) {
        Arrays.sort(miners); // 按照时间从小到大排序

        int time = 0; // 总时间
        int n = miners.length;
        // 贪心策略：每次选择两个耗时最短的人过去，直到所有人都通过隧道。
        while (n > 3) {
            int t1 = miners[0] + miners[1] + miners[n - 1] + miners[n - 2]; // 选择a和b回来，e和d先过去
            int t2 = miners[0] + miners[2] + miners[1] + miners[n - 1]; // 选择a和c回来，b和e先过去
            time += Math.min(t1, t2); // 取两种方案中耗时最短的一种
            n -= 2; // 两个人过去，总人数减少2人
        }
        // 剩下三个人时只有一种方案，直接计算总时间并返回
        return time + miners[2] + miners[0] + miners[1];
    }

    public static void main(String[] args) {
        int[] miners = {1, 3, 6, 8, 12};
        int shortestTime = shortestTime2(miners);
        System.out.println("矿工们通过隧道的最短时间为：" + shortestTime + "秒");
    }

    /**
     * 由于该问题是一个NP完全问题，因此没有一种算法可以在多项式时间内解决该问题的最优解。但是，可以使用动态规划来计算出该问题的近似解。
     * 动态规划算法的基本思路是将问题分解为子问题来解决，然后将子问题的解合并起来，得到原问题的解。在这个问题中，我们可以考虑使用动态规划来解决。
     * 具体地，令dp[i][j]表示前i个矿工中选j个人通过隧道所需的最短时间。则有以下的状态转移方程：
     * dp[i][j] = min(dp[i-1][j-k] + 2 * miners[i-1] + max(miners[i-2k], miners[i-2k-1])), 其中 k=0,1,...,min(j,i/2)
     * 上述状态转移方程的含义是：对于第i个矿工，如果选择了其中的k个人和另外（j-k）个人一起过去，则需要计算两部分时间：第一部分是前(k+1)个人都到达对岸所需的时间（即dp[i-1][j-k]），第二部分则是这些人中耗时最长的时间（即max(miners[i-2k], miners[i-2k-1])）。由于这个问题涉及到的矿工数量比较少，因此可以直接使用动态规划的方法来求解。
     * @param miners
     * @return
     */
    public static int shortestTime2(int[] miners) {
        int n = miners.length;
        int[][] dp = new int[n + 2][n / 2 + 2]; // 初始化dp数组

        // 对dp数组进行赋初值
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE); // 将所有元素初始化为正无穷大
        }
        dp[0][0] = 0;

        // 使用动态规划求解最短时间
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= (n + 1) / 2 && j <= i; j++) {
                if (i == 1 && j == 1) continue;
                for (int k = 1; k <= j && 2 * k < i; k++) { // 将k的取值限制为1到j之间的整数，并且保证2k小于i
                    dp[i][j] = Math.min(dp[i][j], dp[i - 2 * k][j - k] + 2 * miners[i - 2 - k] + Math.max(miners[i - 2 * k - 1], miners[i - 2 * k - 2]));
                }
            }
        }

        return dp[n + 1][(n + 1) / 2];
    }

    /**
     * 除了动态规划算法，还有一种更有效的策略是使用贪心加二分查找的方法来求解。该算法的时间复杂度为O(n log n)，比动态规划算法快一些。
     * 具体思路如下：
     * 将矿工按通过隧道的时间从小到大排序。设排序后的数组为miners。
     * 首先将miners中耗时最少的两个人a和b送到对岸，并返回一个长度为2的数组res，表示这两个人所需的时间和。
     * 接着，在剩余的矿工中使用二分查找找到一个位置k，使得miners[k]恰好大于等于res[1]，也就是说，后面所有的矿工都需要和a或b中的一个人一起过河。
     * 然后，将剩余的矿工中耗时最少的两个人c和d送到对岸，并返回一个长度为2的数组res2，表示这两个人所需的时间和。
     * 重复步骤3和4，直到所有矿工都通过了隧道。
     * 累加每次送人所需的时间，即为总时间。
     */
    public static int shortestTime3(int[] miners) {
        Arrays.sort(miners); // 按通过隧道的时间从小到大排序

        int n = miners.length;
        int time = 0;

        while (n > 3) {
            // 计算前两个人通过隧道所需的时间
            int[] res = getShortestTime(miners, 0, 1);
            time += res[1];

            // 找到第一个大于等于res[1]的位置
            int k = binarySearch(miners, res[1]);

            // 将剩余的矿工中耗时最短的两个人送到对岸
            int[] res2 = getShortestTime(miners, 2, k);
            time += res2[1];
            n -= 2;
        }

        if (n == 3) {
            // 剩余三个人直接计算时间
            time += miners[0] + miners[1] + miners[2];
        } else if (n == 2) {
            // 剩余两个人直接计算时间
            time += miners[1];
        } else {
            // 只有一个人直接计算时间
            time += miners[0];
        }

        return time;
    }

    // 计算miners[l]和miners[r]中哪一个耗时最短
    private static int[] getShortestTime(int[] miners, int l, int r) {
        int a = miners[l], b = miners[r];
        return new int[]{Math.min(a, b), Math.max(a, b)};
    }

    // 在miners数组中使用二分查找找到第一个大于等于x的位置
    private static int binarySearch(int[] miners, int x) {
        int left = 0, right = miners.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (miners[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
