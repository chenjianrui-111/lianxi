package com.cjr.bishi;

import java.util.HashMap;
import java.util.Scanner;

public class Main01 {
    public static void main(String[] args){
        HashMap map=new HashMap();
        Scanner sc=new Scanner(System.in);
        int size=sc.nextInt();
        int []arr=new int[size];
        for(int i=0;i<size;i++){
            arr[i]=sc.nextInt();
        }
        int count=0;
        for (int i = 0; i <size ; i++) {
            int num=1;
            for (int j = i; j <size ; j++) {
                num=num * arr[j];
                if (num > 0){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
