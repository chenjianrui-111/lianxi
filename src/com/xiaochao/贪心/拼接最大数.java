package com.xiaochao.贪心;

/**
 * 如果从一个数组获得k位最大数，那么402. 移掉 K 位数字差不多，我的402题解，
 * 在单调栈比较时只不过把大于小于符号换一下
 * 但是两个数组的话，更麻烦了
 * 从一个数组取出i位数，另一个数组去出k - i位数，合并两个数构成k位数
 * 但是位数是动态变化的，需要比较不同的k位数组成的数的大小，既是比较两个数组的大小，
 * 例如从nums1取出1位，从nums2取出k - 1位构成的最大数，需要和从nums1取出2位，
 * 从nums2取出k - 2位构成的k位最大数进行比较，我们只需维护最大数即可
 */
public class 拼接最大数 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        if (n + m == k){
            return mergeTwoArr(nums1,nums2,k);
        }
        int[] res = new int[k];
        for (int i = 0; i <= k ; i++) {
            //一个为 i 位数，另一个为 k - i位
            //共同构成k位最大数
            //每个组成的数长度都不能超过对应数组的长度
            if (i <= n && k-i <= m){
                int[] maxArr =mergeTwoArr(getMaxArr(nums1,i),getMaxArr(nums2,k-i),k);
                if (compareTwoArr(maxArr,0,res,0)){
                    res = maxArr;
                }
            }
        }
        return res;
    }


    //合并两个数组，得到长度为k的数组
    private int[] mergeTwoArr(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int index = 0;
        int i = 0;
        int j = 0;
        while (index < k){
            if (compareTwoArr(nums1,i,nums2,j)){
                res[index] = nums1[i];
                i++;
            }else {
                res[index] = nums2[j];
                j++;
            }
            index++;
        }
        return res;
    }
    //比较两个数组组成的数字大小 true代表nums1组成的数大 否则nums2组成的数大
    private boolean compareTwoArr(int[] nums1, int i, int[] nums2, int j) {
        int n = nums1.length;
        int m = nums2.length;
        while (i < n && j < m && nums1[i] == nums2[j]){
            i++;
            j++;
        }
        if (i == n){
            //前面都相等 位数少的小
            return false;
        }else if (j == m){//前面都相等 位数少的小
            return true;
        }else if (nums1[i] > nums2[j]){//前面都相等  当前数字大于 则大
            return true;
        }//前面都相等  当前数字小于 则小
        return false;
    }
    //len代表需要多少位，或者得到len位的最大数
    //k = n - len代表需要移除多少位
    //有点像402题，只不过402是拼接最小数
    private int[] getMaxArr(int[] nums, int len) {
        int n = nums.length;
        if (len == 0){
            return new int[0];
        }
        if (n == len){
            return nums;
        }
        int k = n - len;//移除 k 个数字
        int[] res = new int[len];
        int top = 0;
        for (int i = 0; i < n ; i++) {
            while (top > 0 && res[top - 1] < nums[i] && k > 0){
                top--;
                k--;
            }
            if (top < len){
                res[top] = nums[i];
                top++;
            }else {
                k--;
            }
        }
        return res;
    }
}
