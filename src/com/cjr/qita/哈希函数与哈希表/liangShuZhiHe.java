package com.cjr.qita.哈希函数与哈希表;

import java.util.HashMap;

/**
 * 给出一个整型数组 numbers 和一个目标值 target，请在数组中找出两个加起来等于目标值的数的下标，返回的下标按升序排列。
 * （注：返回的数组下标从1开始算起，保证target一定可以由数组里面2个数字相加得到）
 * 数据范围：，，
 * 要求：空间复杂度 ，时间复杂度
 * 示例1
 * 输入
 * [3,2,4],6
 * 输出
 * [2,3]
 * 说明
 * 因为 2+4=6 ，而 2的下标为2 ， 4的下标为3 ，又因为 下标2 < 下标3 ，所以返回[2,3]
 * 示例2
 * 输入
 * [20,70,110,150],90
 * 输出
 * [1,2]
 * 说明
 * 20+70=90
 */
public class liangShuZhiHe {

    public int[] twoSum (int[] numbers, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<numbers.length;i++){
            if (map.containsKey(target-numbers[i])){
                return new int[]{map.get(target-numbers[i])+1,i+1};
            }else {
                map.put(numbers[i],i);
            }
        }
        throw new IllegalArgumentException("No solution");
    }

}
