package com.lizi;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class lowestScore {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int x=Integer.parseInt(s[1]);
        int y=Integer.parseInt(s[2]);
        int[] arr = new int[n];
        s=br.readLine().split(" ");
        for(int i=0; i <arr.length;++i){
            arr[i]=Integer.parseInt(s[i]);
        }

        Arrays.sort(arr);

        //范围
        if( n<2*x || n>2*y){
            System.out.println(-1);
            return;
        }

        //二分淘汰人数
        int left = x;
        int right = y+1;
        while(left < right){
            int mid = left+(right-left)/2;
            if(n-mid >=x || n-mid <=y){
                right =mid;
            }else{
                left =mid+1;
            }
        }
        System.out.println(arr[left-1]);
    }

}
