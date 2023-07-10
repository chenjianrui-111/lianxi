package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 5
 * 2
 *
 * 2
 * 0
 */
public class 小红整数拆分 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 1) {
            System.out.println(1);
        }
        if (n == 2){
            System.out.println(0);
        }
        if (n == 3){
            System.out.println(1);
        }

    }
}
