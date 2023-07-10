package com.xiaochao.笔试;

import java.util.*;

/**
 * 德州扑克的花型由N张扑克牌组成 N < 8，可以组成的牌型按照价值从高到低来区分分别为：
 * 1.皇家同花顺：最高Ace(一点)的同花顺
 * 如A K Q J 10 的同花顺
 * 2.同花顺：同一花色，顺序的牌
 * 如：K Q J 10 9的同花顺
 * 3.四条:有四张同一点数的牌
 * 如： 4 4 4 4 9
 * 4.葫芦：三张同一点数的牌，加一对其他点数的牌
 * 如： 3 3 3 10 10
 * 5.同花：五张同一花色的牌
 * 如： J 10 8 7 5 全是桃红的牌
 * 6.顺子：五张顺连的牌
 * 如： 5 4 3 2 A的非同花牌（此牌型为最小的顺子）
 * 7.三条：仅有三张同一点数的牌，其余两张点数不同
 * 如： 9 9 9 5 3
 * 8.两对：两张相同点数的牌，加另外两张相同点数的牌
 * 如：K K 5 5 2
 * 9.一对：仅有两张相同点数的牌
 * 如：10 10 9 4 2
 * 10.高牌：不符合上面任何一种牌型的牌，由单牌且不连续不同花的组成，由单牌且不连续不同花组成，以点数决定大小。
 * 如： A 10 9 5 3 的非同花牌
 * 这十种牌型分别输出 HuangJiaTongHuaShun、TongHuaShun、SiTiao、HuLu、TongHua、ShunZi、SanTiao、LiangDui、YiTiao
 * 扑克牌有四种花色，分别为（S黑桃、H红心、C草花、D方片）
 * 本题输入为任意小于8的N张牌
 * 得到的结果为这些牌中排序考前的类型
 */
public class 德州扑克 {
    public static String showDown(String inHand) {
        // write code here
        int[] c = new int[4]; // 花色
        int[] k = new int[14]; // 牌
        int[] p = new int[5]; // 对数
        int[] ans = new int[11];
        Map<Character, List<Integer>> map = new HashMap<>();
        map.put('S', new ArrayList<>());
        map.put('H', new ArrayList<>());
        map.put('C', new ArrayList<>());
        map.put('D', new ArrayList<>());
        String[] str = inHand.split(" ");
        int n = str.length;
        for (int i = 0; i < n; i++) {
            String s = str[i];
            char c1 = s.charAt(0), c2 = s.charAt(1);
            if (c1 == 'S') c[0]++;
            else if (c1 == 'H') c[1]++;
            else if (c1 == 'C') c[2]++;
            else if (c1 == 'D') c[3]++;
            if (c2 == 'J') {
                k[11]++;
                map.get(c1).add(11);
            } else if (c2 == 'Q') {
                k[12]++;
                map.get(c1).add(12);
            } else if (c2 == 'K') {
                k[13]++;
                map.get(c1).add(13);
            } else if (c2 == 'A') {
                k[1]++;
                map.get(c1).add(1);
            } else {
                if (s.length() == 3) {
                    k[10]++;
                    map.get(c1).add(10);
                } else {
                    k[c2 - '0']++;
                    map.get(c1).add(c2 - '0');
                }
            }
        }
        for (int i = 0; i < 14; i++) {
            p[k[i]]++;
            int cnt = 0;
            for (int j = i; j < i + 5 && j < 14; j++) {
                if (k[j] > 0) cnt++;
            }
            if (cnt == 5)
                ans[6] = 1;
            cnt = 0;
            if (i == 10) {
                for (int j = i; j < i + 4 && j < 14; j++) {
                    if (k[j] > 0) cnt++;
                }
                if (cnt == 4 && p[1] > 0) ans[6] = 1;
            }
        }
        if (p[4] > 0) ans[3] = 1;
        if (p[3] > 0 && p[2] > 0) ans[4] = 1;
        if (p[3] > 0) ans[7] = 1;
        if (p[2] > 1) ans[8] = 1;
        if (p[2] > 0) ans[9] = 1;
        for (char c0 : map.keySet()) {
            List<Integer> list = map.get(c0);
//            Set<Integer> set = new HashSet<>(l);
//            List<Integer> list = new ArrayList<>(set);
//            map.put(c0, list);
            Collections.sort(list);
            int t = list.size();
            if (t >= 5) {
                ans[5] = 1;//"TongHua"
                 for (int i = 0; i < t - 4; i++) {
                    if (list.get(i) + 4 == list.get(i + 4)) ans[2] = 1;//"TongHuaShun"

                }
                if (list.get(0) == 1) {
                    for (int i = 1; i < t - 3; i++) {
                        if (list.get(i) == 10 && i == t - 4) {
                            ans[1] = 1;
                            //"HuangJiaTongHuaShun"
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(p));
        System.out.println(Arrays.toString(k));
        System.out.println(map);
        Map<Integer, String> amap = new HashMap<>();
        amap.put(1, "HuangJiaTongHuaShun");
        amap.put(2, "TongHuaShun");
        amap.put(3, "SiTiao");
        amap.put(4, "HuLu");
        amap.put(5, "TongHua");
        amap.put(6, "ShunZi");
        amap.put(7, "SanTiao");
        amap.put(8, "LiangDui");
        amap.put(9, "YiDui");
        for (int i = 0; i < 11; i++) {
            if (ans[i] != 0) return amap.get(i);
        }
        return "GaoPai";
    }
}
