package com.xiaochao.笔试;

import java.util.Scanner;

public class 合法名字 {

public static int wenjuan(String[] str,int n){
    int count = 0;
    boolean b = true;
    for(int i=0;i<n;i++){
        b = true;
        if(str[i].length()<=10){
            str[i] = str[i].toLowerCase();
            for(int j =0 ;j<str[i].length();j++){
                if(str[i].charAt(j)<'a' || str[i].charAt(j) >'z' ){
                    b=false;
                }
            }
            if(b==true){
                count++;
            }
        }
    }
    return count;
}
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] str = new String[n];
        for(int i=0;i<n;i++){
            str[i] = sc.nextLine();
        }
        System.out.println(wenjuan(str,n));
    }

}
