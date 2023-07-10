package com.lizi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 大风车 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        for (int i = 0; i <n ; i++) {
            if (i==0) System.out.print("*");
            else {
                int k=n-2;
                for (int j = 0; j <k ; j++) {
                    System.out.print(" ");
                }
                for (int m = 0; m <n ; m++) {
                    System.out.print("*");
                }
            }

        }
    }
}
