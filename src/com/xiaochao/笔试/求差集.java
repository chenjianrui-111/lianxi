package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 求差集 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(";");
        String s1 = strArr[0].substring(1, strArr[0].length() - 1);
        String s2 = strArr[1].substring(1, strArr[0].length() - 1);
        String[] ss1 = s1.split(",");
        String[] ss2 = s2.split(",");
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (String s : ss1) {
            list1.add(s);
        }
        for (String s : ss2) {
            list2.add(s);
        }
        Integer[] m = (Integer[]) list1.toArray();
        Integer[] n = (Integer[])list2.toArray();
        Integer[] c = getC(m, n);
        for (Integer integer : c) {
            System.out.print(integer);
        }
    }
    private static Integer[] getC(Integer[] m,Integer[] n){
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(m.length > n.length ? m : n));
        for (Integer i : m.length > n.length ? n : m){
            if (set.contains(i)){
                set.remove(i);
            }else {
                set.add(i);
            }
        }
        Integer[] arr = {};
        return set.toArray(arr);
    }
}
