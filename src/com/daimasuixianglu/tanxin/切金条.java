package com.daimasuixianglu.tanxin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金 条，不管切成长度多大的两半，都要花费20个铜板。
 * 一群人想整分整块金条，怎么分最省铜板? 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为10+20+30=60。 金条要分成10,20,30三个部分。
 * 如果先把长度60的金条分成10和50，花费60； 再把长度50的金条分成20和30，花费50；一共花费110铜板。 但是如果先把长度60的金条分成30和30，花费60；
 * 再把长度30金条分成10和20， 花费30；一共花费90铜板。 输入一个数组，返回分割的最小代价。
 * 输入描述:
 * 第一行输入一个n代表把金条切分成多少段（n  <= 200）
 * 第二行输入n个数，代表金条切分n段后，每一段的长度
 * 输出描述:
 * 输出分割的最小代价
 * 示例1
 * 输入
 * 3
 * 10 20 30
 * 输出
 * 90
 */
public class 切金条 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(bf.readLine());
        int[] arr=new int[n];
        String[] strs=bf.readLine().split(" ");
        for (int i = 0; i <n ; i++) {
            arr[i]= Integer.parseInt(strs[i]);
        }
        int lessMoney=getLessMoney(arr);
        System.out.println(lessMoney);
    }
    public static int getLessMoney(int[]arr){
        //1.定义小根堆
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        //2.将所有数字放入小根堆
        for (int i = 0; i <arr.length ; i++) {
            pq.add(arr[i]);
        }
        int sum=0;
        int cur=0;
        while (pq.size() > 1){
            //合并
            cur = pq.poll() + pq.poll();
            //计算代价
            sum += cur;
            //合并的数加回小顶堆
            pq.add(cur);
        }
        //返回最小代价
        return sum;
    }
}
