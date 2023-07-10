package com.daimasuixianglu.tanxin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 给定一个长度为 n 的字符串数组 strs ，请找到一种拼接顺序，使得数组中所有的字符串拼接起来组成的字符串是所有拼接方案中字典序最小的，
 * 并返回这个拼接后的字符串。
 * 数据范围：1<=n<=1000000 ， 1<=len(strs)<=10
 * 进阶：空间复杂度 O(n) ， 时间复杂度 O(nlognlen(strs))
 * 示例1
 * 输入
 * ["abc","de"]
 * 输出
 * "abcde"
 * 示例2
 * 输入
 * ["a","a","b"]
 * 输出
 * "aab"
 */
public class 拼接字符串生成字典序最小的字符串 {
    /**
     * @param strs string字符串一维数组 the strings
     * @return string字符串
     */
    public class minStringComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }
    public String minString (String[] strs) {
        // write code here
        List<String> list=new ArrayList<>();
        for (String s:strs){
            list.add(s);
        }
        //对s进行排序,重新编译比较器
        list.sort(new minStringComparator());
        StringBuilder sb=new StringBuilder();
        for (String o:list){
            sb.append(o);
        }
        return sb.toString();
    }
}
