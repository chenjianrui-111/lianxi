package com.daimasuixianglu.tulun;


import java.util.Scanner;

public class biantu {
    public static int MaxValue=200000;
    public static int[][] path;

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //顶点数
        int vertex=sc.nextInt();
        //边数
        int edge=sc.nextInt();
        int[][] matrix =new int[vertex][vertex];
        for (int i = 0; i < vertex ; i++) {
            for (int j = 0; j <vertex ; j++) {
                matrix[i][j] = MaxValue;
            }
        }
        path = new int[matrix.length][matrix.length];
        //初始化边权值
        for (int i = 0; i <edge ; i++) {
            int source=sc.nextInt();
            int target=sc.nextInt();
            int weight=sc.nextByte();
            matrix[source][target] = weight;
        }
        //调用算法计算最短路径
        floyd(matrix);
    }

    public static void floyd(int[][] matrix){
        for (int i = 0; i <matrix.length ; i++) {
            for (int j=0;j<matrix.length;j++){
                path[i][j] = i;
            }
        }
        for (int m = 0; m <matrix.length ; m++) {
            for (int i = 0; i <matrix.length ; i++) {
                for (int j=0;j<matrix.length;j++){
                    if (matrix[i][m] + matrix[m][j] < matrix[i][j]){
                        matrix[i][j] = matrix[i][m] + matrix[m][j];
                        //记录哪个点可达
                        path[i][j] = m;
                    }
                }
            }
        }
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix.length ; j++) {
                if (i != j){
                    if (matrix[i][j] == MaxValue){
                        System.out.println("-1");
                    }else {
                        int min=Integer.MAX_VALUE;
                        System.out.println(Math.min(min,matrix[i][j]));
                    }
                }
            }
        }
    }
}
