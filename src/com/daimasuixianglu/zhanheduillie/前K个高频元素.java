package com.daimasuixianglu.zhanheduillie;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 提示：
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * 思路：
 * 要统计元素出现频率
 * 对频率排序
 * 找出前K个高频元素
 * 首先统计元素出现的频率，这一类的问题可以使用map来进行统计。
 * 然后是对频率进行排序，这里我们可以使用一种 容器适配器就是优先级队列。
 */
public class 前K个高频元素 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result=new int[k];
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        Set<Map.Entry<Integer,Integer>> entries=map.entrySet();
        // 根据map的value值正序排，相当于一个小顶堆
        PriorityQueue<Map.Entry<Integer,Integer>> queue=new PriorityQueue<>((o1, o2) -> o1.getValue()-o2.getValue());
        for (Map.Entry<Integer,Integer> entry:entries){
            queue.offer(entry);
            if (queue.size() > k){
                queue.poll();
            }
        }
        for (int i = k-1; i >=0 ; i--) {
            result[i]=queue.poll().getKey();
        }
        return result;
    }
}
