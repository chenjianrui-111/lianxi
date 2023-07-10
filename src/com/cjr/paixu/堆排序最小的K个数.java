package com.cjr.paixu;

public class 堆排序最小的K个数 {
    public int[] getLeastNumbers(int[] arr, int k) {
        int heapSize=arr.length;
        buildMinHeap(arr,heapSize);
        //调整K-1次
        for (int i =arr.length-1; i >arr.length-1-k ; i--) {
            swap(arr,0,i);
            --heapSize;
            minHeapify(arr,0,i);
        }
        //取出arr末尾的K个元素
        int[] result=new int [k];
        System.arraycopy(arr,arr.length-k,result,0,k);
        return result;
    }
    //构建最小堆
    public void buildMinHeap(int[]arr,int heapSize){
        //从最后一个非空叶子节点开始调整最小堆
        for (int i = arr.length/2-1; i>=0 ; i--) {
            minHeapify(arr,i,heapSize);
        }
    }
    //调整堆
    public void minHeapify(int[]arr,int i,int heapSize){
        int l=2*i+1,r=l+1,smallest=i;
        if (l<heapSize && arr[l]<arr[smallest]){
            smallest=l;
        }
        if (r<heapSize &&arr[r]<arr[smallest]){
            smallest=r;
        }
        if (smallest!=i){
            swap(arr,i,smallest);
            minHeapify(arr,smallest,heapSize);
        }
    }
    public void swap(int [] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

}
