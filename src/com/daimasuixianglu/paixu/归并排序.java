package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 归并排序 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(bf.readLine());
        String[] nums=bf.readLine().split(" ");
        int []arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]= Integer.parseInt(nums[i]);
        }
        mergeSort(arr);
        StringBuilder sb=new StringBuilder();
        for (int i:arr)
            sb.append(i+" ");

        System.out.println(sb.toString());
    }
    public static void mergeSort(int[] arr){
        if (arr==null ||arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }
    public static void process(int[]arr,int left,int right){
        if (left == right){
            return;
        }
        int mid=left+((right-left)>>1);
        process(arr, left, mid);
        process(arr, mid+1, right);
        merge(arr,left,mid,right);
    }
    public static void merge(int[]arr,int left,int mid,int right){
        int[] help=new int[right-left+1];
        int i=0;
        int p1=left;
        int p2=mid+1;
        while (p1<=mid && p2<=right){
            help[i++] =arr[p1] < arr[p2] ?arr[p1++] : arr[p2++];
        }
        while (p1<=mid){
            help[i++]=arr[p1++];
        }
        while (p2<=mid){
            help[i++]=arr[p2++];
        }
        for (int j = 0; j <help.length ; j++) {
            arr[left+j]=help[j];
        }
    }
}
// public static void main(String[] args) throws IOException {
//        //input
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
//        String[] s = br.readLine().split(" ");
//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++){
//            arr[i] = Integer.parseInt(s[i]);
//        }
//
//        //mergeSort
//        mergeSort(arr);
//
//        //output
//        StringBuilder sb = new StringBuilder();
//        for (int i: arr)
//            sb.append(i + " ");
//        System.out.println(sb.toString());
//    }
//
//    public static void mergeSort(int[] arr){
//        if (arr == null || arr.length < 2){
//            return;
//        }
//        process(arr, 0, arr.length - 1);
//    }
//
//    public static void process(int[] arr, int L, int R){
//        if (L == R)
//            return ;
//
//        int M = L + ((R - L) >> 1);
//        process(arr, L, M);
//        process(arr, M + 1, R);
//        merge(arr, L, M, R);
//    }
//
//    public static void merge(int[] arr, int L, int M, int R){
//        int[] help = new int[R - L + 1];
//        int i = 0;
//        int p1 = L;
//        int p2 = M + 1;
//        while(p1 <= M && p2 <= R){
//            help[i++] = arr[p1] < arr[p2] ? arr[p1++]: arr[p2++];
//        }
//        while(p1 <= M){  //一定是p1 <= M, p2 = R + 1 情况
//            help[i++] = arr[p1++];
//        }
//        while(p2 <= R){
//            help[i++] = arr[p2++];
//        }
//        for (i = 0; i < help.length; i++){
//            arr[L + i] = help[i];
//        }
//    }
