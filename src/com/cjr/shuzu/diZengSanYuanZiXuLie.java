package com.cjr.shuzu;

public class diZengSanYuanZiXuLie {
    public boolean increasingTriplet(int[] nums) {
        //边界条件
        if (nums.length<3){
            return false;
        }
        //3个数字，small记录最小值，mid记录第二小的值
        //隐藏条件如果num值比mid小，证明此时small已经被赋值了
        int small=Integer.MAX_VALUE,mid=Integer.MAX_VALUE;
        for (int num:nums){
            if (num<=small){
                small=num;
            }else if (num<=mid){
                mid=num;
            }else {
                //找到最小三元子序列
                return true;
            }
        }
        return false;
    }
}
