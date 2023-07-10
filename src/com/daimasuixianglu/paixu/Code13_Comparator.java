package com.daimasuixianglu.paixu;

import java.util.Arrays;
import java.util.Comparator;

public class Code13_Comparator {

    //对于任意比较器，首先需要指定两个对象，O1 O2
    //如果返回负数，认为第一个参数排在前面
    //如果返回正数，认为第二个参数排在前面
    //返回0，则谁放前面都行

    //重载比较运算符
    public static class AComp implements Comparator<Integer>{

        //如果返回负数，认为第一个参数排在前面
        //如果返回正数，认为第二个参数排在前面
        //返回0，则谁放前面都行
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2){
                return -1;
            }
            if (o2 > o1){
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{5,6,7,9,0,2,3};
        Arrays.sort(arr,new AComp());
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
