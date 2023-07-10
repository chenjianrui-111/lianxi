package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 给你一个以秒为单位的时间长度，要求返回h小时m分钟s秒格式化字符串，忽略为0的字段
 */
public class 时间格式化 {
    public static String formatDuration(int seconds) {
        int hours = seconds / 3600;
        int remainder = seconds % 3600;
        int minutes = remainder / 60;
        int remainingSeconds = remainder % 60;

        StringBuilder formattedParts = new StringBuilder();

        if (hours > 0) {
            formattedParts.append(hours).append("小时");
        }

        if (minutes > 0) {
            formattedParts.append(minutes).append("分钟");
        }

        // 对于秒数为0的情况，我们始终要显示
        formattedParts.append(remainingSeconds).append("秒");

        return formattedParts.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sencond = sc.nextInt();
        System.out.println(formatDuration(sencond));
        //        int duration1 = 6;
//        String formattedDuration1 = formatDuration(duration1);
//        System.out.println(formattedDuration1);  // 输出: 6秒
//
//        int duration2 = 66;
//        String formattedDuration2 = formatDuration(duration2);
//        System.out.println(formattedDuration2);  // 输出: 1分钟6秒
//
//        int duration3 = 3606;
//        String formattedDuration3 = formatDuration(duration3);
//        System.out.println(formattedDuration3);  // 输出: 1小时6秒
    }
}
