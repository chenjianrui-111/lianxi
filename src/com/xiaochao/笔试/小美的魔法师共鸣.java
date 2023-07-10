package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 小美的魔法石共鸣
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 *         小美有n块魔法石，每块魔法石都有正反两面，每一面上都刻有一个魔法阵，初始状态下，n块魔法石都是正面向上。这n块魔法石的能量刚好可以构建一个大型魔法阵，但是需要至少一半的魔法石向上的一面铭刻的阵法相同才能触发大型魔法阵的效果。
 *         小美希望翻转最少数量的魔法石，使得这个大型魔法阵被触发，请问她最少需要翻转多少块魔法石。
 * 输入描述
 * 输入第一行包含一个正整数n，表示魔法石的数量。(1<=n<=100000)
 * 输入第二行包含n个正整数，表示n块魔法石正面铭刻的魔法阵种类，由于魔法书上记载的魔法阵数量太多，所以魔法阵编号可能是从1到10^9中的任何一个正整数。
 * 输入第三行包含n个正整数，表示n块魔法石反面铭刻的魔法阵种类，魔法阵编号同样在1到10^9之间。
 * 数字间两两有空格隔开
 * 输出描述
 * 输出仅包含一个整数，如果有解则输出最少翻转的魔法石数量，如果无解则输出-1。
 */
public class 小美的魔法师共鸣 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nums = Integer.parseInt(sc.nextLine());
        int[] front = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] back = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Map<Integer,Integer> frontNum = new HashMap<>();//记录正面的个数 key 种类 value 个数
        Map<Integer,Integer> AllNum = new HashMap<>(); //记录全部的无重复的个数 key种类 value 正反不重复个数
        for(int num:front){
            frontNum.put(num,frontNum.getOrDefault(num,0)+1);
        }
        AllNum.putAll(frontNum);//frontnum 的map进行 深拷贝
        for(int i=0;i<back.length;i++){
            if(back[i]!=front[i]) AllNum.put(back[i],AllNum.getOrDefault(back[i],0)+1);
        }
        int target = (nums+1)/2;
        int res =Integer.MAX_VALUE;
        for(int i:AllNum.keySet()){
            if(AllNum.getOrDefault(i,0)>=target)//如果总数不足target 直接排除掉
                if(frontNum.getOrDefault(i,0)>=target){ //如果正面已经超过了target 不需要再做改变
                    res = 0;
                    break;
                }else {//否则就需要翻转target-正面朝上的数目
                    res = Math.min(res,target-frontNum.getOrDefault(i,0));
                }
        }
        res= res == Integer.MAX_VALUE?-1:res;
        System.out.println(res);

    }
}
