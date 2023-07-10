package com.xiaochao.前缀和.差分;

/**
 * 二维差分
 * d[x1]py1] += v;
 * d[x2+1][y1] -=v;
 * d[x1][y1+1] -=v;
 * d[x2+1][y2+1] +=v;
 */
public class Demo二维 {
    static int n = 3 ,m = 4;
    static int[][] sum = new int[n][m];
    static int[][] d = new int[n+1][m+1];
    static int g[][] ={
            {1,5,6,8},
            {9,6,7,3},
            {5,3,2,4}
    };
    public static void main(String[] args) {
        add(0,0,2,1,3);
        add(1,1,2,2,-1);
        pre_sum();
        printD(d);
    }
    static void add(int x1,int y1,int x2,int y2,int value){
        d[x1][y1] += value;
        d[x2+1][y1] -=value;
        d[x1][y2+1] -=value;
        d[x2+1][y2+1] +=value;
    }
    static void printD(int[][] d){
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m ; j++) {
                System.out.print(d[i][j] + " ");
            }
        }
    }
    static void pre_sum(){
        sum[0][0] = g[0][0];
        for (int i = 1; i <n ; i++) {//第一列
            sum[i][0] = sum[i-1][0] + d[i][0];
        }
        for (int j = 1; j <m ; j++) {//第一行
            sum[0][j] = sum[0][j-1] + d[0][j];
        }
        for (int i = 1; i < n ; i++) {
            for (int j = 1; j < m ; j++) {
                sum[i][j] = d[i][j] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
            }
        }
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m ; j++) {
                g[i][j] += sum[i][j]; //将前缀和数组映射到原数组
            }
        }
    }
    static int get_sum(int x1,int y1,int x2,int y2){
        if (x1 == 0 && y1 == 0) return sum[x2][y2];//左上角为 0,0
        if (x1 == 0) return sum[x2][y2] - sum[x2][y1 - 1];//左上角行为 0 抽象成多行的一维前缀和
        if (y1 == 0) return sum[x2][y2] - sum[x1-1][y2];//左上角列为 0 抽象成多列的一维前缀和
        return sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
    }
}
