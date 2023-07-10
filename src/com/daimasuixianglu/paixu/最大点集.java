package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 请实现代码找到集合P中的所有”最大“点的集合并输出。
 * 输入描述:
 * 第一行输入点集的个数N， 接下来N行，每行两个数字代表点的X轴和Y轴。1 ≤ n ≤ 500000
 * 输出描述:
 * 输出“最大的”点集合， 按照X轴从小到大的方式输出，每行两个数字分别代表点的X轴和Y轴。
 * 示例1
 * 输入
 * 5
 * 1 2
 * 5 3
 * 4 6
 * 7 5
 * 9 0
 * 输出
 * 4 6
 * 7 5
 * 9 0
 * 备注:
 * 输出结果按照x轴排序
 */
public class 最大点集 {
    private static class Point{
        int x;
        int y;
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        int max_x = Integer.MIN_VALUE;
        int max_y = Integer.MIN_VALUE;
        while((str = br.readLine())!=null){
            int size = Integer.parseInt(str);
            // 大根堆
            PriorityQueue<Point> queue = new PriorityQueue<>((o1, o2) -> (o2.y - o1.y));
            for(int i=0;i<size;++i){
                String[] arr = br.readLine().split(" ");
                int x = Integer.parseInt(arr[0]);
                int y = Integer.parseInt(arr[1]);
                // 用于快速筛选
                if(x < max_x && y < max_y){
                    continue;
                }
                queue.offer(new Point(x,y));
                if(x >= max_x && y >= max_y){
                    max_x = x;
                    max_y = y;
                }
            }
            int sizes = queue.size();
            int curX = Integer.MIN_VALUE;
            for(int i = 0;i < sizes;++i){
                Point p = queue.poll();
                if(p.x >= curX){
                    System.out.println(p.x + " "+ p.y);
                    curX = p.x;
                }
            }
        }
    }
}
