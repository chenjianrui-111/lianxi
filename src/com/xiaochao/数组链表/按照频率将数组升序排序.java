package com.xiaochao.数组链表;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。 
 * 请你返回排序后的数组。
 * 示例 1：
 * 输入：nums = [1,1,2,2,2,3]
 * 输出：[3,1,1,2,2,2]
 * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
 * 示例 2：
 * 输入：nums = [2,3,1,3,2]
 * 输出：[1,3,3,2,2]
 * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
 * 示例 3：
 * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
 * 输出：[5,-1,4,4,-6,-6,1,1,1]
 * 提示：
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */

/**
 * 哈希表 + 排序 + 模拟
 * 根据题意，先使用哈希表进行词频统计，再以二元组 (x, cnt) 的形式转存到数组 list 中（其中 x为对应的 nums 中的数值，cnt 为数值 x 在 nums 中的出现次数），
 * 再根据题目给定对 list 进行排序，最后再构造出答案。

 */
public class 按照频率将数组升序排序 {
    HashMap<Integer,Integer> map= new HashMap<>();
    public int[] frequencySort(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        List<int[]> list = new ArrayList<>();
        for (Integer key : map.keySet()) {
            list.add(new int[]{key,map.get(key)});
        }
        Collections.sort(list,(a,b)->{
            return a[1] != b[1] ? a[1] - b[1] : b[0] - a[0];
        });
        int[] ans = new int[n];
        int idx = 0;
        for (int[] info : list) {
            while (info[1] -- > 0) ans[idx++] = info[0];
        }
        return ans;
    }
}
//时间复杂度：使用哈希表进行统计复杂度为 O(n)；根据规则进行排序复杂度为 O(nlogn)；构造答案复杂度为 O(n)。整体复杂度为 O(nlogn)
//空间复杂度：O(n)
