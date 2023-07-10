package com.xiaochao.数组链表.查分数组;

public class 区间加法 {
    int[] getModifiedArray(int length, int[][] updates) {
        int[] nums = new int[length];
        //构造差分解法
        Difference1 df1=new Difference1(nums);
        for (int[] update : updates){
            int i =update[0];
            int j =update[1];
            int val =update[2];
            df1.increment(i,j,val);
        }
        return df1.result();
    }
}
class Difference1{
    private int[] diff;

    public Difference1(int[] nums){
        assert nums.length > 0;
        diff = new int[nums.length];

        diff[0] = nums[0];
        for (int i = 1; i <nums.length ; i++) {
            diff[i] = nums[i] - nums[i-1];
        }
    }
    public void increment(int i,int j,int val){
        diff[i] += val;
        if (j+1 < diff.length){
            diff[j+1] -=val;
        }
    }
    public int[] result(){
        int []res =new int[diff.length];
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i-1] + diff[i];
        }
        return res;
    }
}
