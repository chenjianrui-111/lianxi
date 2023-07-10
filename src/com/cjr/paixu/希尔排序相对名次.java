package com.cjr.paixu;

import java.util.Arrays;
import java.util.HashMap;

public class 希尔排序相对名次 {
    public String[] findRelativeRanks(int[] score) {
        int [] arr=score.clone();
        shellSort(arr);
        //建立每个运动员成绩与名次的关系
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i <arr.length ; i++) {
            map.put(arr[i],i+1);
        }
        String[] result = Arrays.stream(score).mapToObj(String::valueOf).toArray(String[]::new);
        for (int i = 0; i <score.length ; i++) {
            if (score[i]==arr[0])
                result[i] = "Gold Medal";
            else if (score[i] == arr[1])
                result[i] = "Silver Medal";
            else if (score[i] == arr[2])
                result[i] = "Bronze Medal";
            else result[i] = String.valueOf(map.get(score[i]));
        }
        return result;
    }
    public void shellSort(int[] arr){
        for (int gap=arr.length/2;gap>0;gap/=2){
            for (int i = gap; i <arr.length ; i++) {
                int currentNumber=arr[i];
                int preIndex=i-gap;
                while (preIndex>=0&&currentNumber<arr[preIndex]){
                    //后移
                    arr[preIndex+gap]=arr[preIndex];
                    preIndex=preIndex-gap;
                }
                //currentNumber当前位置
                arr[preIndex+gap]=currentNumber;
            }
        }
    }

}
