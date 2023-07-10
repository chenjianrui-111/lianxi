package com.daimasuixianglu.paixu;

public class Code07_EvenTimesOddTimes {
    public static void printOddTimesNum1(int[] arr){
        int eor = 0;
        for (int cur:arr){
            eor ^= cur;
        }
        System.out.println(eor);
    }
    //arr中一定有两个数出现了奇数次，其他数都出现了偶数次
    public static void printOddTimesNum2(int[] arr){
        int eor=0;
        for (int i = 0; i <arr.length ; i++) {
            eor ^=arr[i];
        }
        //eor = a ^ b
        //eor ！= 0
        //eor必然有一个位置是1
        int rightOne=eor & (~eor + 1);//提取出最右侧的1 00001000
        //                                             10010000
        //只想要当前位置为1的数，放入onlyOne中
        int onlyOne = 0;//eor'
        for (int cur : arr) {
            if ((cur & rightOne) != 0){
                onlyOne ^=cur;
            }
        }
        System.out.println(onlyOne  +" " +(eor ^ onlyOne));
    }
}
