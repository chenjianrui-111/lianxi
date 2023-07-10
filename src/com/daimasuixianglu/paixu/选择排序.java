package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 选择排序 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int size= Integer.parseInt(bf.readLine());
        String[] nums=bf.readLine().split(" ");
        int [] arr=new int[size];
        for (int i = 0; i <size ; i++) {
            arr[i]= Integer.parseInt(nums[i]);
        }
        for (int i = 0; i <size ; i++) {
            int minIndex=i;
            for (int j = i+1; j <size ; j++) {
                minIndex=arr[j] < arr[minIndex] ? j :minIndex;
            }
            swap(arr,i,minIndex);
        }
        //output
        StringBuilder sb=new StringBuilder();
        for (int i:arr){
            sb.append(i+" ");
        }
        System.out.println(sb.toString());
    }
    public static void swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
