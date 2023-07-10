package com.lizi;

import java.util.Scanner;

public class 数组修改 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int p=sc.nextByte();
        int x=sc.nextInt();

        int[] arr=new int[n];
        int sum=0;
        for (int i = 0; i <n ; i++) {
            int tmp = sc.nextInt();
            arr[i] = tmp;
            sum=sum + tmp;
            }
        int count=0;
        for (int i = 0; i <n ; i++) {
            int tmpSum = sum - arr[i];
            for (int j = 1; j <= p ; j++) {
                if (j == arr[i]) {
                    continue;
                }
                if ((tmpSum + j) % x == 0) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
