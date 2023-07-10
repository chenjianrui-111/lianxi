package com.cjr.qita.哈希函数与哈希表;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 给出N个数，要求把其中重复的去掉，只保留第一次出现的数。
 * 例如，给出的数为1 2 18 3 3 19 2 3 6 5 4，其中2和3有重复，去除后的结果为1 2 18 3 19 6 5 4。
 * 输入描述:
 * 输入第一行为正整数T，表示有T组数据。
 * 接下来每组数据包括两行，第一行为正整数N，表示有N个数。
 * 第二行为要去重的N个正整数。
 * 输出描述:
 * 对于每组数据，输出一行，为去重后剩下的数字，数字之间用一个空格隔开。
 * 示例1
 * 输入
 * 2
 * 11
 * 1 2 18 3 3 19 2 3 6 5 4
 * 6
 * 1 2 3 4 5 6
 * 输出
 * 1 2 18 3 19 6 5 4
 * 1 2 3 4 5 6
 * 备注:
 * 对于30% 的数据，。
 * 对于60% 的数据，
 * 对于100% 的数据，，给出的数在32位有符号整数范围内。
 */
public class buChongFuShuZu {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        for (int i = 0;i<num;i++){
            HashMap<String,Integer> map = new HashMap<>();
            int numElem = Integer.parseInt(br.readLine());
            String[] str = br.readLine().split(" ");
            for (int j =0;j<numElem;j++){
                if (!map.containsKey(str[j])){
                    map.put(str[j],j);
                    System.out.print(str[j] + " ");
                }
            }
            System.out.print("\n");
        }
    }
}
