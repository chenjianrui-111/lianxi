package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 你买了一个扫地机器人，你想要知道这个扫地机器人是否能够将房间打扫干净。
 * 为了简化问题，我们不妨假设房间被划分为n*m的方格。定义打扫干净为这n*m的方格全部被打扫过至少一次。
 * 你为扫地机器人下达了若干指令。每个指令为上下左右移动中的一种。机器人会将经过的路径上的方格打扫干净。
 * 初始时假设机器人处于第一行第一列的方格中。这个方格初始时会被机器人直接打扫干净。
 * 现在询问你机器人能否将房间打扫干净，能则输出Yes，不能则输出No。
 * 对于Yes的情况下，还要求你继续输出到哪个指令结束后，房间就打扫干净了。
 * 对于No的情况下，还要求你输出还有多少个地块没有打扫干净。
 * 保证机器人在打扫的过程中不会越过房间边界。换句话说机器人始终保持在n*m的方格图中。
 * 输入描述
 * 第一行三个正整数n, m, k，以空格分开，表示房间大小n*m，接下来会有长度为k的指令。
 * 第二行长度为k的一个字符串。字符串中仅有下面四种字符（注意：均为大写）
 *     W：表示下一步机器人将向上移动
 *     A：表示下一步机器人将向左移动
 *     S：表示下一步机器人将向下移动
 *     D：表示下一步机器人将向右移动
 *
 * 保证2 <= n, m <= 100, 指令长度 <= 100000
 *
 * 输出描述
 * 第一行一个字符串Yes或No表示能否打扫干净
 * 对于Yes的情况，第二行输出一个正整数，表示在第几个指令之后已经打扫干净了。
 * 注意指令编号从1开始而不是0。
 * 对于No的情况，第二行输出一个正整数，表示还剩几个地块没有打扫。
 */
public class 扫地机器人 {

    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        String[] line = cin.nextLine().split(" ");
        int n = Integer.parseInt(line[0]), m = Integer.parseInt(line[1]), k = Integer.parseInt(line[2]);
        String orders = cin.nextLine();
        int[][] room = new int[n][m];
        room[0][0] = 1;
        int count = 1, x = 0, y = 0;
        for (int i = 0; i < orders.length(); i++) {
            char ch = orders.charAt(i);
            if (ch == 'W') x--;
            else if (ch == 'A') y--;
            else if (ch == 'S') x++;
            else y++;
            if (room[x][y] == 0) {
                room[x][y] = 1;
                count++;
            }
            if (count == n * m) {
                System.out.println("Yes");
                System.out.println(i + 1);
                return;
            }
        }
        System.out.println("No");
        System.out.println(n * m - count);
    }
}
