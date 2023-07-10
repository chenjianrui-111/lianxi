package com.daimasuixianglu.paixu;

public class Code11_HeapSort {

    public static void HeapSort(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }
        //O(logN*N)一个个的heapInsert
        for (int i = 0; i <arr.length ; i++) {//O(N)
            heapInsert(arr,i);//O(logN)
        }
        //O(n)这一步让它成为大根堆，数据一步提供
//        for (int i = arr.length-1; i >=0 ; i++) {
//            heapify(arr,i,arr.length);
//        }

        int heapSize=arr.length;
        swap(arr,0,--heapSize);
        while (heapSize > 0){//O(N)
            heapify(arr,0,heapSize);//O(logN)
            swap(arr,0,--heapSize);//O(1)
        }
    }
    //arr[0...index-1]已经是大根堆了，某个数现在处在index位置，往上继续移动
    public static void heapInsert(int[] arr,int index){
        while (arr[index] > arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index=(index-1)/2;
        }
    }
    //某个数在index位置，看看能否往下沉，往下移动
    public static void heapify(int[]arr,int index,int heapSize){
        int left=index *2 + 1;
        while (left < heapSize){//下方还有孩子的时候
            //当下方还有孩子的时候,谁的值大，把下标给largest
            int largest=left + 1 <heapSize && arr[left+1] >arr[left] ?left+1 :left;
            //父和较大的孩子之间，谁的值大，把下标给largest
            largest=arr[largest] > arr[index] ? largest :index;
            if (largest == index){
                break;
            }
            swap(arr,largest,index);
            index=largest;
            left= index * 2 + 1;
        }
    }
    public static void swap(int[] arr,int i, int j){
        arr[i]=arr[i] ^ arr[j];
        arr[j]=arr[i] ^ arr[j];
        arr[i]=arr[i] ^ arr[j];
    }

}
