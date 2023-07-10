package com.daimasuixianglu.beibao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左和最右的纸牌，玩家A和玩家B绝顶聪明。请返回最后的获胜者的分数。
 * 输入描述:
 * 输出包括两行，第一行一个整数n，代表数组arr长度，第二行包含n个整数，第i个代表arr[i]。
 * 输出描述:
 * 输出一个整数，代表最后获胜者的分数。
 * 示例1
 * 输入
 * 4
 * 1 2 100 4
 * 输出
 * 101
 * 备注:
 * 时间复杂度,空间复杂度
 */
public class 排成一条线的纸牌博弈问题 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[] str=bf.readLine().split(" ");
        int cards[]=new int[n];
        for (int i = 0; i <n ; i++) {
            cards[i]= Integer.parseInt(str[i]);
        }
    //动态规划
    int[][] df = new int[n][n];
    int[][] ds = new int[n][n];
        for(int right = 0;right<n;right++){
        for(int left = right;left>=0;left--){
            if(right == left){
                df[left][right] = cards[left];
                ds[left][right] = 0;
            }else if(right > left){
                df[left][right] = Math.max(cards[left]+ds[left+1][right],cards[right]+ds[left][right-1]);
                ds[left][right] = Math.min(df[left+1][right],df[left][right-1]);
            }
        }
    }
        System.out.print(Math.max(df[0][n-1],ds[0][n-1]));
    }
}
