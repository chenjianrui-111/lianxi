package com.xiaochao.前缀和;

/**
 * sum[i][j] = g[i][j] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1]
 * 当 i = 0 且 j = 0
 * sum[0][0] = g[0][0]
 * 当i = 0
 * sum[0][i] = sum[0][i-1] + g[0][j]
 * sum{0,y1}{0,y2} = sum[0][y2] - sum[0][y1-1]
 * 当 j = 0
 * sum[i][0] = sum[i-1][0] + g[i][0]
 * sum{x1,0}{x2,0} = sum[x2][0] - sum[x1-1][0]
 */
public class Demo2维 {
    static int n = 3 ,m = 4;
    static int[][] sum = new int[n][m];
    static int g[][] ={
        {1,5,6,8},
        {9,6,7,3},
        {5,3,2,4}
    };
    public static void main(String[] args) {
        pre_sum();
       int a = get_sum(1,1,2,2);
       int b =  get_sum(0,1,1,3);
        System.out.println(a + " " + b);
    }
    static void pre_sum(){
        sum[0][0] = g[0][0];
        for (int i = 1; i <n ; i++) {//第一列
            sum[i][0] = sum[i-1][0] + g[i][0];
        }
        for (int j = 1; j <m ; j++) {//第一行
            sum[0][j] = sum[0][j-1] + g[0][j];
        }
        for (int i = 1; i < n ; i++) {
            for (int j = 1; j < m ; j++) {
                sum[i][j] = g[i][j] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
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
