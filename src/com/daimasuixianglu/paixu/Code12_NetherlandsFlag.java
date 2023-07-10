package com.daimasuixianglu.paixu;

public class Code12_NetherlandsFlag {

    //荷兰国旗问题 分三块
    //在arr[L..R]范围上，根据p分块，<p 在左边，==p 在中间，>p 在右边
    //返回值的含义是：我一定会返回长度为2的数组，等于区域的左边界和右边界
    //arr = [...3 5 5 4 6 7 ..]
    //index:    4 5 6 7 8 9
    //p=5
    //arr = [...(34)5 5 （67）...]
    //              6 7
    //返回一个长度为2的数组[6,7]
    //如果无等于区域，返回值，左边界 > 右边界
    public static int[] partition(int[] arr,int L,int R,int p){
        int less=L - 1;// <区的右边界
        int more=R + 1;//>区的左边界
        int index=L;
        while (index < more){//L是当前数的下标
            if (arr[index] < p){
                swap(arr,++less,L++);
            }else if (arr[index] > p){
                swap(arr,--more,index);
            }else {
                index++;
            }
        }
        //标记等于区域范围
        return new int[]{less+1,more-1};
    }
    public static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
