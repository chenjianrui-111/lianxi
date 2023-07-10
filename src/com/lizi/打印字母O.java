package com.lizi;

import java.util.Scanner;

public class 打印字母O {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for (int i = 0; i <n ; i++) {
            int count=n-i;
            for (int j = 0; j <4*n ; j++) {
                if (j<count || j>=4*n-count){
                    System.out.println(".");
                }else {
                    System.out.println("*");
                }
            }
            System.out.println("");
        }
        for (int j = 0; j <2*n ; j++) {
            for (int k = 0; k <4*n ; k++) {
                if (k<n || k>=(3*n)){
                    System.out.println("*");
                }else {
                    System.out.println(".");
                }
            }
            System.out.println("");
        }
        for (int i = 0; i <n ; i++) {
            int count=i+1;
            for (int j = 0; j <4*n ; j++) {
                if (j<count || j>=4*n-count){
                    System.out.println(".");
                }else {
                    System.out.println("*");
                }
            }
            System.out.println("");
        }
    }
}
