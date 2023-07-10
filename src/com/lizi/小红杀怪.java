package com.lizi;

import java.util.Scanner;

public class 小红杀怪 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextByte();
        int x = sc.nextInt();
        int y = sc.nextInt();
        if (a > 20 || a < 1 && b < 1 || b > 20 && x < 1 || x > 20 && y < 1 || y > 20) return;
        if (x < y) {
            if (a < b) {
                if (b % y == 0) {
                    System.out.println(b / y);
                } else {
                    System.out.println(b / y + 1);
                }
            } else {
                if (a % y == 0) {
                    System.out.println(a / y);
                } else {
                    System.out.println(a / y + 1);
                }
            }
        } else {
            if ((a + b) % x == 0) {
                System.out.println((a + b) / x);
            } else {
                System.out.println((a + b) / x + 1);
            }
        }
    }
}
