package com.cjr.shuzu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sanShuZhiHe {

    public List<List<Integer>> threeSum(int [] nums) {
        if (nums.length == 0 || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //去重
                    while (left < right && nums[left] == nums[++left]) ;
                    while (left < right && nums[right] == nums[--right]) ;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }

        }

        return new ArrayList<>(res);
    }

}
