package com.daimasuixianglu.lianbiao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 反转链表acm {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String readLine=br.readLine();
        readLine=readLine.substring(1,readLine.length()-1);
        StringBuilder sb=new StringBuilder();
        String[] split=readLine.split(",");
        for (int i = split.length-1; i >=0 ; i--) {
            if (i == 0){
                sb.append(split[i]);
            }else {
                sb.append(split[i]+",");
            }
        }
        System.out.println("{" + sb +"}");
    }
}
