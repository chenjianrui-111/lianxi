package com.lizi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 小红打O {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(bf.readLine());
        for (int i = 0; i <n ; i++) {
            System.out.print(".");
            for (int j = 0; j <2*n ; j++) {
                System.out.print("*");
            }
            System.out.print(".");
        }
        for (int i = 0; i <n ; i++) {
            System.out.println("*");
            for (int j = 0; j <2*n ; j++) {
                System.out.print(".");
            }
        }
        for (int i = 0; i <n ; i++) {
            System.out.println("*");
            for (int j = 0; j <2*n ; j++) {
                System.out.print(".");
            }
        }
        for (int i = 0; i <n ; i++) {
            System.out.println(".");
            for (int j = 0; j <2*n ; j++) {
                System.out.print("*");
            }
        }
    }
}
