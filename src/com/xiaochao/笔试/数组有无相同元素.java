package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 在一个校园社交活动中，学生们通过抽签的方式获得了一个参与活动的编号。现在需要判断是否有学生抽到了相同的编号。给你一个整数数组nums，
 * 其中nums[i]表示第i个学生抽到的编号。如果数组中至少有一个编号出现了至少两次，返回true;如果数组中的所有编号都互不相同，则返回false
 * 输入描述:
 * 示例
 * 输入:nums =[1,2,3,1]
 * 输出:true
 * 输入:nums =[1,2,3,4]
 * 输出:false
 */
public class 数组有无相同元素 {
    public static boolean hasDuplicateNumbers(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            if (numSet.contains(num)) {
                return true;
            }
            numSet.add(num);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String line1 = line.substring(8,line.length() - 1);
        String[] splits  = line1.split(",");
        int[] nums = new int[splits.length];
        for (int i = 0; i < splits.length; i++) {
            nums[i] = Integer.parseUnsignedInt(splits[i].trim());
        }
        System.out.println(hasDuplicateNumbers(nums));
    }
}
