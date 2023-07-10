package com.xiaochao.笔试;

import java.util.Arrays;

public class demo3 {
    public static int answer1(int[] x){
        if (x.length < 2) return 0;
        Arrays.sort(x);
        int max = 0;
        int tmp = 0;
        for (int i = 1; i < x.length - 1; i++) {
            tmp = x[i] - x[i - 1];
            max = Math.max(max,tmp);
        }
        return  max;
    }
    public static int answer2(int x){
        if (x < 0) return 0;
        if (x > 1000) return 1000;
        int count = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int a = x;
        int b = 2 * x;
        int c = 3 * x;
        int d = 4 * x;
        String aa = String.valueOf(a);
        String bb = String.valueOf(b);
        String cc = String.valueOf(c);
        String dd = String.valueOf(d);
//        for (int i = 0; i < aa.length(); i++) {
//            if ('1' == aa.charAt(i)) count++;
//        }
//        for (int i = 0; i < bb.length(); i++) {
//            if ('1' == bb.charAt(i)) count1++;
//        }
//        for (int i = 0; i < cc.length(); i++) {
//            if ('1' == cc.charAt(i)) count2++;
//        }
//        for (int i = 0; i < dd.length(); i++) {
//            if ('1' == dd.charAt(i)) count3++;
//        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dd.length(); i++) {
            sb.append(i);
        }
        for (int i = 0; i < sb.length() ; i++) {
            if ('1' == sb.charAt(i)) count++;
        }
        return count;
    }
    public  static int[] answer3(int x){
        int num = 10;
        Math.abs(x);
        StringBuilder sb = new StringBuilder();
        while (num -- > 0){
            sb.append(x);
        }
        String ss = sb.toString();
        int[] arr = new int[ss.length()];
        for (int i = 0; i < ss.length(); i++) {
            arr[i] = ss.charAt(i);
        }
//        StringBuilder sb2 = new StringBuilder();
//        for (int i = 0; i < ss.length(); i++) {
//            if (i == 0){
//                sb2.append("[" + ss.charAt(i) + ",");
//            }else if (i == ss.length() - 1){
//                sb2.append(ss.charAt(i) + "]");
//            }else {
//                sb2.append(ss.charAt(i) +",")
//            }
//        }
//        String s = sb2.toString();
        return arr;
    }
    public static int[] answer(int[] x){
        int i = answer1(x);
        System.out.println(i);
        int i2 = answer2(i);
        System.out.println(i2);
        int[] ints = answer3(i2);
        return ints;
    }

    public static void main(String[] args) {
        int [] a = new int[]{3,6,9,1,2,6};
        int[] answer = answer(a);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i]);
        }
        System.out.println(answer2(3));

//        System.out.println(answer2(12));
    }
}
