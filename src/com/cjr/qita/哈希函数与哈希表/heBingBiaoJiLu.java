package com.cjr.qita.哈希函数与哈希表;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 数据表记录包含表索引和数值（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 * 提示:
 * 0 <= index <= 11111111
 * 1 <= value <= 100000
 * 输入描述:
 * 先输入键值对的个数n（1 <= n <= 500）
 * 然后输入成对的index和value值，以空格隔开
 * 输出描述:
 * 输出合并后的键值对（多行）
 * 示例1
 * 输入
 * 4
 * 0 1
 * 0 2
 * 1 2
 * 3 4
 * 输出
 * 0 3
 * 1 2
 * 3 4
 * 示例2
 * 输入
 * 3
 * 0 1
 * 0 2
 * 8 9
 * 输出
 * 0 3
 * 8 9
 */
public class heBingBiaoJiLu {
    public static void main(String[] args) throws IOException {
        BufferedReader bfd=new BufferedReader(new InputStreamReader(System.in));
        //输入键值对的个数n
        int count= Integer.parseInt(bfd.readLine());
        //实现相同key值，合并value，同时按照key值排序
        TreeMap<Integer, Integer> map = new TreeMap<>();
        //存放每行的数据
        String str;
        //存放被空格分开后的数据
        String[] strs;
        //strs对应整型数组
        int[] nums = new int[2];
        //存放键对应的值
        Integer value;
        for (int i=0;i<count;i++){
            str=bfd.readLine();
            strs=str.split(" ");
            nums[0]=Integer.parseInt(strs[0]);
            nums[1]=Integer.parseInt(strs[1]);
            value=map.get(nums[0]);
            if (value !=null){
                nums[1]+=value;
            }
            map.put(nums[0],nums[1]);
        }
        //输出键排序，值合并的结果
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        //存放键值对
        Map.Entry<Integer, Integer> next;
        while (iterator.hasNext()){
            next=iterator.next();
            System.out.println(next.getKey()+" "+next.getValue());
        }
        bfd.close();
    }
}
