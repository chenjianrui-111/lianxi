package com.xiaochao.回溯算法;

/**
 * 在⼀条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。你有⼀辆油箱容量⽆限的的汽⻋，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的⼀个加油站出发，开始时油箱为空。 如果你可以绕环路⾏驶⼀周，则返回出发时加油站的编号，否则返回 -1（如果题⽬有解，该答案即为唯⼀答 案）。 示例 1: 输⼊：
 * gas = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出：3 解释： 从 3 号加油站（索引为 3 处）出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好⾜够你返回到 3 号加油站。 因此，3 可为起始索引。
 */
public class 加油站 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        //相当于图像中的坐标点和最低点
        int sum = 0,minSum = 0;
        int start =0;
        for (int i = 0; i < n ; i++) {
            sum += gas[i] - cost[i];
            if (sum < minSum){
                //经过第 i 个站点后，使 sum 到达新低
                //所以 起点变为 i+1
                start = i + 1;
                minSum = sum;
            }
        }
        if (sum < 0) {
            // 总油量小于总的消耗，无解
            return -1;
        }
        //环形数组特性
        return start == n ? 0 : start;
    }
    /**
     * 贪心解法
     * 发现从 i 出发无法走到 j，那么 i 以及 i, j 之间的所有站点都不可能作为起点。
     */
    int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
        }
        if (sum < 0) {
            // 总油量小于总的消耗，无解
            return -1;
        }
        // 记录油箱中的油量
        int tank = 0;
        // 记录起点
        int start = 0;
        for (int i = 0; i < n; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                // 无法从 start 到达 i + 1
                // 所以站点 i + 1 应该是起点
                tank = 0;
                start = i + 1;
            }
        }
        return start == n ? 0 : start;
    }
}
