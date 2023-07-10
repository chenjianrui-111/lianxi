package com.leixing.递归迭代.DFS;

import java.util.HashMap;
import java.util.Map;

/**
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
 * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
 * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
 DFS（TLE）
 根据题意，我们可以使用 DFS 来模拟/爆搜一遍，检查所有的可能性中是否有能到达最后一块石子的。
 通常设计 DFS 函数时，我们只需要不失一般性的考虑完成第 i块石子的跳跃需要些什么信息即可：
 需要知道当前所在位置在哪，也就是需要知道当前石子所在列表中的下标 u。
 需要知道当前所在位置是经过多少步而来的，也就是需要知道上一步的跳跃步长 k。
 */
public class 青蛙过河 {
    Map<Integer,Integer> map=new HashMap<>();
    public boolean canCross(int[] stones) {
        int n=stones.length;
        // 将石子信息存入哈希表
        // 为了快速判断是否存在某块石子，以及快速查找某块石子所在下标
        for (int i=0;i<n;i++){
            map.put(stones[i],i);
        }
        // check first step
        // 根据题意，第一步是固定经过步长 1 到达第一块石子（下标为 1）
        if (!map.containsKey(1)) {
            return false;
        }
        return dfs(stones, stones.length, 1, 1);
    }

    /**
     * @param stones 石子列表不變
     * @param n 石子列表長度不變
     *@param u  当前所在的石子的下标
     * @param k  上一次是经过多少步跳到当前位置的
     * @return 是否能跳到最后一块石子
     */

    boolean dfs(int [] stones,int n,int u,int k){
        if (u == n-1) return true;
        for (int i=-1;i<= 1;i++){
            // 如果是原地踏步的话，直接跳过
            if (k + i == 0) continue;
            // 下一步的石子理论编号
            int next = stones[u] + k + i;
            // 如果存在下一步的石子，则跳转到下一步石子，并 DFS 下去
            if (map.containsKey(next)) {
                boolean cur = dfs(stones, n, map.get(next), k + i);
                if (cur) return true;
            }
        }
        return false;
    }
}
/**
 *时间复杂度：O(3^n)
 * 空间复杂度：O(3^n)
 * 但数据范围为 10^3
 * 直接使用 DFS 肯定会超时。
 */
