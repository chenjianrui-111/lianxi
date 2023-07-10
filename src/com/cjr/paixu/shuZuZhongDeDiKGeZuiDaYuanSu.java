package com.cjr.paixu;

public class shuZuZhongDeDiKGeZuiDaYuanSu {
    public int findKthLargest(int[] nums, int k) {
        int heapSize=nums.length;
        buildMaxHeap(nums,heapSize);
        for (int i=nums.length-1;i>=nums.length-k+1;i--){
            swap(nums,0,i);
            --heapSize;
            maxHeapify(nums,0,heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int []nums,int heapSize){
        for (int i = heapSize/2; i >=0 ; i--) {
            maxHeapify(nums,i,heapSize);
        }
    }

    public void maxHeapify(int []nums,int i,int heapSize){
        int l=2*i+1,right=2*i+2,largest=i;
        if (l<heapSize &&nums[l]>nums[largest]){
            largest=l;
        }
        if (right<heapSize && nums[right]>nums[largest]){
            largest=right;
        }
        if (largest!=i){
            swap(nums,i,largest);
            maxHeapify(nums,largest,heapSize);
        }
    }
    public void swap(int []nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

}
