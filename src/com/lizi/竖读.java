package com.lizi;

import java.io.IOException;
import java.util.Scanner;

public class 竖读 {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int r=sc.nextInt();
            int[][] martix=new int[r][r+1];
            sc.nextLine();//跳过行数和列数后的换行符
            for (int i = 0; i <r ; i++) {
                for (int j = 0; j <r+1 ; j++) {
                    martix[i][j] = sc.nextInt();
                }
            }
            StringBuilder sb=new StringBuilder();
            for (int j=0;j<r;j++){
                for (int i=0;i<r+1;i++){
                    if (martix[j][0] != 0){
                        sb.append(martix[j][0]);
                    }else {
                        sb.append(martix[j][i]);
                    }
                }
            }
            System.out.println(sb.toString());
        }
    }
}
