package com.xiaochao.数组链表;

public class 生成达标数组 {
    //生成长度为 size 的达标数组
    //达标：对于任意的 i < j,满足 [i] + [j] != [k] * 2
    //左侧放奇数，右侧放偶数
    public static int[] makeNo(int size){
        if (size == 1){
            return new int[]{1};
        }
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        // base -> 等长奇数达标
        int[] ans =new int[size];
        int index = 0;
        for (;index < halfSize;index++){
            ans[index] = base[index] * 2 + 1;
        }
        // base -> 等长偶数达标
        for (int i = 0;index  < size;index ++){
            ans[index] = base[i] * 2 ;
        }
        return ans;
    }
    //检验函数
    public static boolean isValid(int[] arr){
        int N = arr.length;
        for (int i = 0; i < N ; i++) {
            for (int k = i + 1; k <N ; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
