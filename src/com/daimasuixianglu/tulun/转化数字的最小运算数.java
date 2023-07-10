package com.daimasuixianglu.tulun;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个下标从 0 开始的整数数组 nums，该数组由 互不相同 的数字组成。
 * 另给你两个整数 start 和 goal 。
 * 整数 x 的值最开始设为 start ，你打算执行一些运算使 x 转化为 goal 。
 * 你可以对数字 x 重复执行下述运算：
 * 如果 0 <= x <= 1000，那么，对于数组中的任一下标 i （0 <= i < nums.length），可以将 x 设为下述任一值：
 * x + nums[i]
 * x - nums[i]
 * x ^ nums[i]（按位异或 XOR）
 * 注意，你可以按任意顺序使用每个 nums[i] 任意次。使 x 越过 0 <= x <= 1000 范围的运算同样可以生效，但该该运算执行后将不能执行其他运算。
 * 返回将 x = start 转化为 goal 的最小操作数；如果无法完成转化，则返回 -1
 * 输入：nums = [1,3], start = 6, goal = 4
 * 输出：2
 * 解释：
 * 可以按 6 → 7 → 4 的转化路径进行，只需执行下述 2 次运算：
 *   6 + 1 = 7
 *   7 - 3 = 4
 */
public class 转化数字的最小运算数 {
    int[] nums;
    public int minimumOperations(int[] _nums, int s, int t) {
        nums=_nums;
        Deque<Long> d1=new ArrayDeque<>(),d2=new ArrayDeque<>();
        Map<Long,Integer> m1=new HashMap<>(),m2=new HashMap<>();
        d1.addLast(s*1L);
        d2.addLast(t*1L);
        m1.put(s*1L,0);
        m2.put(t*1L,0);
        while (!d1.isEmpty() && !d2.isEmpty()){
            if (d1.size() < d2.size()){
                int ans=update(d1,m1,d2,m2,true);
                if (ans!=-1) return ans;
            }else {
                int ans=update(d2,m2,d1,m1,false);
                if (ans!=-1) return ans;
            }
        }
        return -1;
    }
    int update(Deque<Long> d1,Map<Long,Integer>m1,Deque<Long> d2,Map<Long,Integer>m2,boolean flag) {
        long cur = d1.pollFirst();
        int step = m1.get(cur);
        for (int i : nums) {
            if (flag) {
                // 正向搜索：进行出队检查，只有出队元素符合条件，才能使用出队元素往下拓展
                if (0<=cur && cur<=1000){
                    long[] result=new long[]{cur+i,cur-i,cur^i};
                    for (long next:result){
                        if (m2.containsKey(next)) return step+1+m2.get(next);
                        if (!m1.containsKey(next)){
                            d1.addLast(next);
                            m1.put(next,step+1);
                        }
                    }
                }
            }else {
                // 反向搜索：进行入队检查，只有拓展元素符合条件，才能将拓展元素入队
                long[] result=new long[]{cur+i,cur-i,cur^i};
                for (long next:result){
                    if (0<=cur && cur<=1000){
                        if (m2.containsKey(next)) return step+1+m2.get(next);
                        if (!m1.containsKey(next)){
                            d1.addLast(next);
                            m1.put(next,step+1);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
