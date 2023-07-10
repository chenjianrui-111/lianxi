package com.lizi;

public class 质数下标 {
    public static int getNumber (int[] a) {
        // write code here
        int temp[] =new int[a.length];
        int index=0;
        for (int i=1;i<a.length;i++){
            for (int j = 2; j <i ; j++) {
                if (i % j != 0){
                    temp[index]=a[i];
                    index++;
                }else {
                    continue;
                }
            }
        }
       return temp[0];
    }

    public static void main(String[] args) {
        int [] a=new int[]{1,2,3,4};
        int res=getNumber(a);
        System.out.println(res);
    }
}
