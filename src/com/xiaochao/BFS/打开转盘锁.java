package com.xiaochao.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * 示例 1:
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 */
public class 打开转盘锁 {
    public int openLock(String[] deadends, String target) {
        //记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();
        for (String s: deadends) deads.add(s);
        //记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q= new LinkedList<>();
        //从起点开始广度优先探索
        int step = 0;
        q.offer("0000");
        visited.add("0000");
        while (!q.isEmpty()){
            int sz =q.size();
            for (int i = 0; i <sz ; i++) {
                String cur =q.poll();
                if (deads.contains(cur))
                    continue;
                if (cur.equals(target))
                    return step;
                for (int j = 0; j < 4 ; j++) {
                    String up =upOne(cur,j);
                    if (!visited.contains(up)){
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = downOne(cur,j);
                    if (!visited.contains(down)){
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }
    //将s[j]向上拨动一次
    String upOne(String s,int j){
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }
    //将s[j]向下拨动一次
    String downOne(String s,int j){
        char[] ch =s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }
}

/**
 * 传统的 BFS 框架就是从起点开始向四周扩散，遇到终点时停⽌；⽽双向 BFS 则是从起点和终点同时开始扩散，当两边有交集的时候停⽌。
 */
class Solution{

    int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        // ⽤集合不⽤队列，可以快速判断元素是否存在
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q1.add("0000");
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 哈希集合在遍历的过程中不能修改，⽤ temp 存储扩散结果
            Set<String> temp = new HashSet<>();
            /* 将 q1 中的所有节点向周围扩散 */
            for (String cur : q1) {
                /* 判断是否到达终点 */
                if (deads.contains(cur))
                    continue;
                if (q2.contains(cur))
                    return step;

                visited.add(cur);
                /* 将⼀个节点的未遍历相邻节点加⼊集合 */
                for (int j = 0; j < 4; j++) {
                    String up = upOne(cur, j);
                    if (!visited.contains(up))
                        temp.add(up);
                    String down = downOne(cur, j);
                    if (!visited.contains(down))
                        temp.add(down);
                }
            }
            /* 在这⾥增加步数 */
            step++;
            // temp 相当于 q1
            // 这⾥交换 q1 q2，下⼀轮 while 就是扩散 q2
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }
    //将s[j]向上拨动一次
    String upOne(String s,int j){
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }
    //将s[j]向下拨动一次
    String downOne(String s,int j){
        char[] ch =s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }
}
