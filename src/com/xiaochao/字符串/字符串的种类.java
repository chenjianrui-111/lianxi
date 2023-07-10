package com.xiaochao.字符串;

import java.util.HashSet;

//如果两个字符串，所含字符种类完全一样，
// 就算做一类只由小写字母(a~z)组成的一批字符串，都放在字符串类型的数组String[]arr中，返回arr中一共多少类
public class 字符串的种类 {
    public static int types1(String[] arr){
        //不同类的摘要，都放在types 中
        HashSet<String> set =new HashSet<>();
        for (String str:arr){
            char[] chs = str.toCharArray();
            boolean[] map= new boolean[26];
            for (int i = 0; i <chs.length ; i++) {
                map[chs[i] - 'a'] = true;
            }
            String key ="";
            for (int i = 0; i <26 ; i++) {
                if (map[i]){
                    key += String.valueOf((char)(i + 'a'));
                }
            }
            set.add(key);
        }
        return set.size();
    }
    //位运算优化
    public static int types2(String[] arr){
        //int 代表的摘要，都放在types 中
        HashSet<Integer> set =new HashSet<>();
        for (String s : arr) {
            char[] chs = s.toCharArray();
            int key = 0; //0000 0000 0000 0000 0000 0000 00
            for (int i = 0; i < chs.length ; i++) {
                key |= (1 << (chs[i] - 'a'));
            }
            set.add(key);
        }
        return set.size();
    }
}
