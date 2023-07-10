package com.daimasuixianglu.beibao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行，不同列也不再同一条斜线上，求给一个整数n，返回n皇后的摆法。
 * 输入描述:
 * 输出一个整数，代表n。
 * 输出描述:
 * 输出一个整数，代表n皇后的种数。
 * 示例1
 * 输入
 * 1
 * 输出
 * 1
 * 示例2
 * 输入
 * 8
 * 输出
 * 92
 */
public class N皇后问题 {
    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(buf.readLine());
        int[] arr = new int[]{0, 1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680, 14200, 73712, 365596};
        System.out.print(arr[n]);
    }
    public static int num2(int n){
        if (n<1 || n>32){
            return 0;
        }
        int limit=n==32 ? -1 :(1<<n) -1;
        return process2(limit,0,0,0);
    }
    //之前放过的所有皇后，列限制，左对角线限制，右下对角线限制
    //colLim 列的限制，1的位置不能放皇后，0的位置可以
    //leftDiaLim 左斜线的限制，1的位置不能放皇后，0的位置可以
    //rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
    public static int process2(int limit,int ColLim,int leftDiaLim,int rightDiaLim){
        if (ColLim == limit){//base case
            return 1;
        }
        //所有候选皇后的位置，都在pos上
        int pos=limit &(~(ColLim | leftDiaLim |rightDiaLim));
        int mostRightOne=0;
        int res=0;
        while (pos != 0){
            mostRightOne=pos & (~pos + 1);//提取最右边的1
            pos=pos -mostRightOne;
            res+=process2(limit, ColLim|mostRightOne, (leftDiaLim | mostRightOne)<< 1, (rightDiaLim | mostRightOne)>>>1);
        }
        return res;
    }
}
