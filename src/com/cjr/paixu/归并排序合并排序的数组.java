package com.cjr.paixu;

public class 归并排序合并排序的数组 {
    public void merge(int[] A, int m, int[] B, int n) {
        int i=m-1;
        int j=n-1;
        int end=m+n-1;
        while (j>=0){
            A[end--]=(i>=0 &&A[i]>A[j])?A[i--]:A[j--];
        }
    }
}
