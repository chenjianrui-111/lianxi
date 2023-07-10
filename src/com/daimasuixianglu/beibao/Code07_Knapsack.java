package com.daimasuixianglu.beibao;

public class Code07_Knapsack {
    public static int getMaxValue(int[] w,int[]v,int bag){
        return process(w,v,0,0,bag);
    }
    //index...最大值
    //w[index]当前货物的重量
    //v[index]当前货物的价值
    //index:当前来到的货物号，index...后续货物自由选择
    //alreadyW:0...index-1,已经做的决定，形成的目前重量
    //index...后续货物自由选择，返回最大价值
    public static int process(int[]w,int[]v,int index,int alreadyW,int bag){
        if (alreadyW > bag){
            return -1;
        }
        //重量没超
        if (index == w.length){
            return 0;
        }
        //不要当前货物，获得的最大价值
        int p1=process(w, v, index + 1, alreadyW, bag);
        //要当前的货物，后续获得的最大价值
        //要当前货物，当前货物的价值+后续得到的价值
        int p2next=process(w, v, index + 1, alreadyW+w[index], bag);
        int p2=-1;
        if (p2next != -1){
            p2=v[index] + p2next;
        }
        return Math.max(p1,p2);
    }

    public static int getMaxValue1(int[] w,int [] v,int bag){
        return process(w,v,0,bag);
    }
    public static int process(int[]w,int[]v,int index,int rest){
        if (rest<=0){
            return 0;
        }
        if (index == w.length){
            return  0;
        }
        //有货也有空间
        int p1=process(w, v, index+1, rest);
        int p2=Integer.MIN_VALUE;
        if (rest >=w[index]){
            p2=v[index]+process(w, v, index+1, rest-w[index]);
        }
        return Math.max(p1,p2);
    }
}
