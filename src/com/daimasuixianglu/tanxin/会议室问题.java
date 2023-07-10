package com.daimasuixianglu.tanxin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。 给你每一个项目开始的时间和结束的时间(给你一个数 组，里面是一个个具体 的项目)，
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。 返回这个最多的宣讲场次。
 * 输入描述:
 * 第一行输入一个n代表有n场演讲（n <= 200）
 * 下面n行需要输入两个整数 start、end代表会议开始时间和结束时间，其中(1<= start<=end <= 24)
 * 输出描述:
 * 输出一个整数，这个整数代表最多的宣讲场次
 * 示例1
 * 输入
 * 3
 * 1 10
 * 11 20
 * 10 11
 * 输出
 * 3
 * 示例2
 * 输入
 * 3
 * 6 12
 * 7 8
 * 8 9
 * 输出
 * 2
 */
public class 会议室问题 {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(bfr.readLine());
        int[][] time=new int[n][2];
        for (int i = 0; i < n ; i++) {
            String []chars=bfr.readLine().split(" ");
            for (int j = 0; j <chars.length ; j++) {
                time[i][j] = Integer.parseInt(chars[j]);
            }
        }
        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
               if (o1[0] == o2[0]){
                   //若俩数组的第一个元素相等，则比较它们的第二个元素
                   return o1[1] - o2[1];
               }else {
                   // 若俩数组的第一个元素不相等，则按从小到大的顺序排列
                   return o1[0] - o2[0];
               }
            }
        });
        int lastMeeting=0;
        int sum=1;
        int endTime=time[0][1];
        for (int i = 1; i <n ; i++) {
            if (time[i][0] < endTime) {//有冲突了，比较上一个回会议和这个会议的停止时间
                if (time[lastMeeting][1] > time[i][1]) { //上一次的结束时间更晚，就放弃上一次会议
                    lastMeeting = i;
                    endTime = time[i][1];
                }
            }else {
                    //没有冲突，则加入这次会议
                    lastMeeting=i;
                    endTime=time[i][1];
                    sum++;
                }
            }
            System.out.println(sum);
        }
}
