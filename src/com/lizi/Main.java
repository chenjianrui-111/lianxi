package com.lizi;

import java.util.Scanner;

public class Main{
    static int add(int a, int b) {
        // Please fill this blank
        return a+b;
    }

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
        int ans=add(a,b);
        System.out.println(ans);
    }
}
