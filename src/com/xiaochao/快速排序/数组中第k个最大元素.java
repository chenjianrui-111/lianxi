package com.xiaochao.快速排序;

import java.util.PriorityQueue;
import java.util.Random;

public class 数组中第k个最大元素 {
    public int findKthLargest(int[] nums, int k) {
        // 小顶堆，堆顶是最小元素
        PriorityQueue<Integer>
                pq = new PriorityQueue<>();
        for (int e : nums) {
            // 每个元素都要过一遍二叉堆
            pq.offer(e);
            // 堆中元素多于 k 个时，删除堆顶元素
            if (pq.size() > k) {
                pq.poll();
            }
        }
        // pq 中剩下的是 nums 中 k 个最大元素，
        // 堆顶是最小的那个，即第 k 个最大元素
        return pq.peek();
    }
}
class Solution{
    public int findKthLargest(int[] nums, int k) {
        //首先随机打乱
        shuffle(nums);
        int lo = 0, hi = nums.length - 1;
        //转换成排名第 k 的元素
         k = nums.length -  k;
         while (lo <= hi){
             int p = partition(nums,lo,hi);
             if (p < k){
                 lo = p + 1;
             }else if (p > k){
                 hi = p - 1;
             }else {
                 return nums[p];
             }
         }
         return -1;
    }

    private static void shuffle(int[] nums){
        int n =nums.length;
        Random random =new Random();
        for (int i = 0; i <n ; i++) {
            int r = i + random.nextInt(n - i);
            swap(nums,i,r);
        }
    }
    private static void swap(int[] nums,int i, int r){
        int temp = nums[i];
        nums[i] = nums[r];
        nums[r] = temp;
    }

    private static int partition(int[] nums,int lo,int hi){
        int pivot =nums[lo];
        int i =lo + 1,j = hi;
        while (i <= j){
            while (i < hi && nums[i] <= pivot){
                i++;
            }
            while (j>lo && nums[j] >= pivot){
                j--;
            }
            if (i >= j){
                break;
            }
            swap(nums,i,j);
        }
        swap(nums,lo,j);
        return j;
    }
}
