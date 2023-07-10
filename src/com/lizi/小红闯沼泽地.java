package com.lizi;

import java.util.Scanner;

public class 小红闯沼泽地 {
    static int count;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        totalMethod(n,m);
        System.out.println(count);
    }
    public static int totalMethod(int n,int m){
        int count=0;
        if (n==0 && m==0){
            count++;
            return 0;
        }
        if (n==0 || m==0){
            count+=2;
            return 1;
        }
        return totalMethod(n-1,m)+totalMethod(n,m-1);
    }
}
