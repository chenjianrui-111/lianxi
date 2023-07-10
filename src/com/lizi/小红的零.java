package com.lizi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class 小红的零 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr=new int[n];

        for(int i=0;i<n;i++){
            arr[i]=scan.nextInt();
        }
        int count=0;
        int res=0;
        int m;
        for(int i=0;i<n;i++){
            res=arr[i];
            if(res%10==0){
                while(res!=0){
                    m = res%10;//得到最后一位数字赋值给m
                    if(m==0){count++;}
                    else{ break; }
                    res = res/10;
                }
            }
        }
        int a=0;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                res=1;
                a=i;
                while(a<=j){
                    res*=arr[a];
                    a++;
                }
                    if(res%10==0){
                        while(res!=0){
                            m = res%10;//得到最后一位数字赋值给m
                            if(m==0){count++;}
                            else{ break;}
                            res = res/10;
                        }
                    }
            }
        }
        System.out.println(count);
    }
}
