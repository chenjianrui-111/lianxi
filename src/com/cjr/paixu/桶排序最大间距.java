package com.cjr.paixu;

import java.util.Arrays;

public class 桶排序最大间距 {
    public int maximumGap(int[] nums) {
        if (nums.length<2) return 0;
        int n=nums.length;

        int max=-1,min=Integer.MIN_VALUE;
        for (int i = 0; i <nums.length ; i++) {
            max=Math.max(nums[i],max);
            min=Math.min(nums[i],min);
        }
        if (max==min) return 0;//1 1 1 1

        //对桶内元素初始化
        int [] bucketMin=new int[n-1];
        int [] bucketMax=new int[n-1];
        Arrays.fill(bucketMax,-1);
        Arrays.fill(bucketMin,Integer.MAX_VALUE);

        //桶间距
        int interval= (int) Math.ceil((double) (max-min)/(n-1));
        for (int i = 0; i <nums.length ; i++) {
            int ID=(nums[i]-min)/interval;
            if (nums[i]==max ||nums[i]==min) continue;
            bucketMax[ID]=Math.max(nums[i],bucketMax[ID]);
            bucketMin[ID]=Math.min(nums[i],bucketMin[ID]);
        }

        //设前一桶最大元素为previousMax
        int maxGap=0,previousMax=min;
        for (int i = 0; i <n-1 ; i++) {
            if (bucketMax[i] == -1) continue;; //空桶情况 跳过
            maxGap=Math.max(bucketMin[i]-previousMax,maxGap);
            previousMax=bucketMax[i];
        }

        //1,10000 将之前忽略的最大值加入比较
        maxGap=Math.max(maxGap,max-previousMax);
        return maxGap;
    }
}
