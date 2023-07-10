package com.daimasuixianglu.paixu;

public class Code10_SmallSum {
    public static int smallSum(int[] arr){
        if (arr == null || arr.length<2){
            return 0;
        }
        return process(arr,0,arr.length-1);
    }
    //arr[L..R]既要排序好，也要返回小和
    public static int process(int[] arr,int L,int R){
        if (L == R){
            return  0;
        }
        int mid= L+((R-L) >> 1);
        return process(arr, L, mid) +process(arr, mid+1, R) +merge(arr,L,mid,R);
    }
    public static int merge(int[] arr,int L,int mid,int R){
        int[] help=new int[R-L+1];
        int i=0;
        int p1=L;
        int p2=mid+1;
        int res=0;
        while (p1 <=mid && p2<=R){
            res+=arr[p1] < arr[p2] ?(R-p2+1) * arr[p1] : 0;
            help[i++]=arr[p1] < arr[p2] ?arr[p1++] : arr[p2++];
        }
        while (p1 <= mid){
            help[i++]=arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        for (int j = 0; j <help.length ; j++) {
            arr[L + i] =help[i];
        }
        return  res;
    }
}
