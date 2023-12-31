package com.cjr.paixu;

public class 堆排序数组中的第K个最大元素 {
    public int findKthLargest(int[] nums, int k) {
        int heapSize=nums.length;
        buildMaxHeap(nums,heapSize);
        //调整k-1次
        for (int i = nums.length-1; i>nums.length-k+1; i--) {
            swap(nums,0,i);
            --heapSize;
            maxHeapify(nums,0,heapSize);
        }
        return nums[0];
    }
    //构建大顶堆
    public void buildMaxHeap(int[]nums,int heapSize){
        //从最后一个非叶子节点开始调整大顶堆
        for (int i = nums.length/2; i >=0 ; i--) {
            maxHeapify(nums,i,heapSize);
        }
    }
    //调整大顶堆，第三个参数表示未排序的数字的数量，就是剩余堆的大小
    public void maxHeapify(int []nums,int i,int heapSize){
        int l=2*i+1,r=2*i+2;
        //记录左子节点，右子节点，根节点中最大值下标
        int largest=i;
        if (l<heapSize &&nums[l]>nums[largest]){
            largest=l;
        }
        if (r<heapSize &&nums[r]>nums[largest]){
            largest=r;
        }
        if (largest!=i){
            swap(nums,i,largest);
            maxHeapify(nums,largest,heapSize);
        }
    }
    public void swap(int[]arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

}
