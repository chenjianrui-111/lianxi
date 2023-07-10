package com.daimasuixianglu.haxibiao;

import java.util.HashMap;
import java.util.Map;

/**
 *给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * 示例 1：
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * 本题解题步骤：
 * 首先定义 一个map，key放a和b两数之和，value 放a和b两数之和出现的次数。
 * 遍历大A和大B数组，统计两个数组元素之和，和出现的次数，放到map中。
 * 定义int变量count，用来统计a+b+c+d = 0 出现的次数。
 * 在遍历大C和大D数组，找到如果 0-(c+d) 在map中出现过的话，就用count把map中key对应的value也就是出现次数统计出来。
 * 最后返回统计值 count 就可以了
 */
public class 四数相加二 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer,Integer> map=new HashMap<>();
        int temp;
        int res=0; // 统计a+b+c+d = 0 出现的次数
        //统计两个数组中的元素之和，同时统计出现的次数，放入map
        for (int i:nums1){
            for (int j:nums2){
                temp=i+j;
                if (map.containsKey(temp)){
                    map.put(temp,map.get(temp)+1);
                }else {
                    map.put(temp,1);
                }
            }
        }
        //统计剩余的两个元素的和，在map中找是否存在相加为0的情况，同时记录次数
        for (int i:nums3){
            for (int j:nums4){
                temp=i+j;
                if (map.containsKey(0-temp)){
                   res+=map.get(0-temp);
                }
            }
        }
        return res;
    }
}
