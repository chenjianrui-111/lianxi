package com.xiaochao.归并排序;

public class 排序数组 {
      public int[] sortArray(int[] nums) {
        Merge.sort(nums);
        return nums;
    }
}
class Merge{

    // ⽤于辅助合并有序数组
    private static int[] temp;
    public static void sort(int[] nums){
        //先给辅助数组开辟内存空间
        temp = new int[nums.length];
        //排序整个数组，原地修改
        sort(nums,0,nums.length-1);
    }
    //将子数组 nums[lo..hi]排序
    public static void sort(int[] nums,int lo,int hi){
        //单个元素不需要排序
        if (lo == hi){
            return;
        }
        int mid= lo + (hi - lo) / 2;
        //先对左半部分进行排序
        sort(nums, lo, mid);
        //在对右半部分进行排序
        sort(nums, mid+1, hi);
        // 将两部分有序数组合并成⼀个有序数组
        merge(nums, lo, mid, hi);
    }
    public static void merge(int[] nums,int lo,int mid,int hi){
        // 先把 nums[lo..hi] 复制到辅助数组中
        // 以便合并后的结果能够直接存⼊ nums
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }
        // 数组双指针技巧，合并两个有序数组
        int i = lo ,j =mid + 1;
        for (int p = lo; p <= hi ; p++) {
            if (i == mid + 1){
                // 左半边数组已全部被合并
                nums[p] = temp[j++];
            }else if (j == hi + 1){
                // 右半边数组已全部被合并
                nums[p] = temp[i++];
            }else if (temp[i] > temp[j]){
                nums[p] = temp[j++];
            }else {
                nums[p] = temp[i++];
            }
        }
    }
}
