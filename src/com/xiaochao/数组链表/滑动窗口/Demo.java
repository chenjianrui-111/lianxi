package com.xiaochao.数组链表.滑动窗口;

import java.util.HashMap;

import static jdk.nashorn.internal.objects.Global.print;

public class Demo {
    /* 滑动窗口算法框架 */
//    void slidingWindow(String s, String t) {
//        HashMap<Character, Integer> need= new HashMap<Character, Integer>();
//        HashMap<Character, Integer> window= new HashMap<Character, Integer>();
//        char[] t1 =t.toCharArray();
//        char[] s1=s.toCharArray();
//        for (char c : t1) {
//            need.put(c,need.getOrDefault(c,0) + 1);
//        }
//
//        int left = 0, right = 0;
//        int valid = 0;
//        while (right < s.length()) {
//            // c 是将移入窗口的字符
//            char c = s1[right];
//            // 增大窗口
//            right++;
//            // 进行窗口内数据的一系列更新
//        ...
//
//            /*** debug 输出的位置 ***/
//            print("window: [%d, %d)\n", left, right);
//            /********************/
//
//            // 判断左侧窗口是否要收缩
//            while (window needs shrink) {
//                // d 是将移出窗口的字符
//                char d = s1[left];
//                // 缩小窗口
//                left++;
//                // 进行窗口内数据的一系列更新
//            ...
//            }
//        }
//    }
}
