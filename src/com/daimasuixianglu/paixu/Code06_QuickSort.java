package com.daimasuixianglu.paixu;

public class Code06_QuickSort {

    //快排1.0
    public static void quick_1_0(int[]arr,int L,int R){
        process(arr,0,arr.length-1);
    }
    //arr[L..R]范围上，根据arr[R]来做划分值，进行partition
    //arr = [... 4 5 7 1 0 6 5...]
    //index: ... 3 4 5 6 7 8 9...
    //           L            R
    //以arr[9]也就是最后一个5作划分值
    //先调整成：
    //arr = [...4 5 0 1 7 6 5...]
    //arr = [...4 5 0 1 5 6 7...]
    //index: ...3 4 5 6 7 8 9...
    public static int process(int[] arr,int L,int R){
        //以arr[R]做划分
        //假设最后arr[R]来到了index位置
        int index=L;
        process(arr, L,index-1);
        process(arr, index+1, R);
        return index;
    }
}
class  Solution{

    public static void quick_3_0(int[]arr, int L, int R){
        process(arr,0,arr.length-1);
    }
    //快排3.0
    //arr[l..r]排好序
    public static void process(int[] arr,int L,int R){
        if (L == R){
            return;
        }
        if (L<R){
            swap(arr, (int) (L+Math.random()*(R-L+1)),R);
            int[] p=partition(arr,L,R);
            process(arr, L, p[0]-1);// < 区
            process(arr, p[1]+1, R);// >区
        }
    }

    //这是一个处理arr[l..r]的函数
    //默认以arr[r]做划分，arr[r] -> p  <p == >p
    //返回等于区域(左边界，右边界)，返回一个长度为2的数组res,res[0] res[1]
    public static int[] partition(int[] arr,int L,int R){
        int less=L - 1;//<区右边界
        int more=R;//>区左边界
        while (L < more){// L表示当前数的位置 arr[R] -> 划分值
            if (arr[L] < arr[R]){//当前数 < 划分值
                swap(arr,++less,L++);
            }else if (arr[L] > arr[R]){
                swap(arr,--more,L);
            }else {
                L++;
            }
        }
        swap(arr,more,R);
        return new int[]{less+1,more};
    }
    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
