package com.xiaochao.笔试;

import java.util.*;

public class 考点评估 {
    public int halfQuestions (int[] questions) {

        int[] nums = new int[1001];
        for (int x : questions) nums[x]++;
        int sum = 0;
        int count = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; i++) {
            sum += nums[i];
            count++;
            if (sum >= questions.length / 2) break;
        }
        return count;
    }
}
