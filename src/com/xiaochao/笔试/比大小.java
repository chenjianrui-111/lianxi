package com.xiaochao.笔试;

import java.util.Scanner;

public class 比大小 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len1 = sc.nextInt();
        int k1 = sc.nextInt();
        int len2 = sc.nextInt();
        int k2 = sc.nextInt();
        String s = "";
        int num11 = sc.nextInt();
        String s1 = s + num11;
        int num22 = sc.nextInt();
        String s2 = s + num22;
        int num1 = transRedix(s1, k1);
        int num2 = transRedix(s2, k2);
        if (num1 > num2){
            System.out.println(">");
        }else if (num1 < num2){
            System.out.println("<");
        }else {
            System.out.println("=");
        }
    }
     static int transRedix(String num,int fromRadix){
         int i = Integer.parseInt(num, fromRadix);
         return i;
     }
}
