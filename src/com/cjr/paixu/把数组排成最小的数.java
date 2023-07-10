package com.cjr.paixu;

import java.util.Arrays;

public class 把数组排成最小的数 {
    public String minNumber(int[] nums) {
        bubbleSort(nums);
        return Arrays.toString(nums).replaceAll("\\[|]|,|\\s","");
    }
    public void bubbleSort(int[] arr){
        boolean swapped=true;
        //最后一个没排序的元素的下标
        int indexOfLastUnsortElement=arr.length-1;
        //上次交换的位置
        int swappedIndex=-1;
        while (swapped){
           swapped=false;
            for (int i = 0; i <indexOfLastUnsortElement ; i++) {
                if ((""+arr[i]+arr[i+1]).compareTo(""+arr[i+1]+arr[i])>0){
                    //如果""+arr[i]+arr[i+1]组成的字符串大于""+arr[i+1]+arr[i]组成的字符串，则交换
                    swap(arr,i,i+1);
                    //发生了交换
                    swapped=true;
                    //更新交换的位置
                    swappedIndex=i;
                }
            }
            //最后一个没排序的元素的下标就是最后一次发生交换的位置
            indexOfLastUnsortElement=swappedIndex;
        }
    }
    public void swap(int[] arr,int i,int j){
        arr[i]=arr[i]+arr[j];
        arr[j]=arr[i]-arr[j];
        arr[i]=arr[i]-arr[j];
    }
}
