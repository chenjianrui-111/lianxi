package com.daimasuixianglu.acmshuru;

import java.util.Scanner;

public class 二维数组 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int r=sc.nextInt();
            int c=sc.nextInt();
            int[][] martix=new int[r][c];
            sc.nextLine();//跳过行数和列数后的换行符
            for (int i = 0; i <r ; i++) {
                for (int j = 0; j <c ; j++) {
                    martix[i][j] = sc.nextInt();
                }
            }
        }
    }
}
