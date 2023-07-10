package com.xiaochao.笔试;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给你一个整数数组nums，和一个目标整数target。请你从nums中找出所有和等于target的非空连续子数组的数量输入描述:
 * 例:[10,9,25,3,7,101,18,1],19->3
 * 10,9,2,5,3,7,101,18,1,19
 * 输出 3
 */
public class 和等于target的非空连续数组 {
    public static int findTargetSubarrays(int[] nums, int target) {
        int count = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixSumCounts = new HashMap<>();
        prefixSumCounts.put(0, 1);

        for (int num : nums) {
            prefixSum += num;
            count += prefixSumCounts.getOrDefault(prefixSum - target, 0);
            prefixSumCounts.put(prefixSum, prefixSumCounts.getOrDefault(prefixSum, 0) + 1);
        }

        return count - 1;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String[] splits = str1.split(",");
        int[] arr = new int[splits.length];
        int target = 0;
        for (int i = 0; i < splits.length; i++) {
            arr[i] = Integer.parseUnsignedInt(splits[i].trim());
            if (i == splits.length - 1){
                target = Integer.parseInt(splits[i]);
            }
        }
        System.out.println(findTargetSubarrays(arr,target));
    }
}
