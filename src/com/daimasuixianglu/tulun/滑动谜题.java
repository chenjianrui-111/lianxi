package com.daimasuixianglu.tulun;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 */
public class 滑动谜题 {
    class Node {
        String str;
        int x, y;
        Node(String _str, int _x, int _y) {
            str = _str; x = _x; y = _y;
        }
    }
    int n = 2, m = 3;
    String s, e;
    int x, y;
    public int slidingPuzzle(int[][] board) {
        s = "";
        e = "123450";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                s += board[i][j];
                if (board[i][j] == 0) {
                    x = i; y = j;
                }
            }
        }
        int ans = bfs();
        return ans;
    }
    int[][] dirs=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        int bfs(){
            Deque<Node> d=new ArrayDeque<>();
            Map<String,Integer> map=new HashMap<>();
            Node root=new Node(s,x,y);
            d.addLast(root);
            map.put(s,0);
            while (!d.isEmpty()){
                Node poll=d.pollFirst();
                int step=map.get(poll.str);
                if (poll.str.equals(e)) return step;
                int dx=poll.x,dy=poll.y;
                for (int[] di:dirs){
                    int nx=dx+di[0],ny=dy+di[1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    String nStr=update(poll.str,dx,dy,nx,ny);
                    if (map.containsKey(nStr)) continue;
                    Node next=new Node(nStr,nx,ny);
                    d.addLast(next);
                    map.put(nStr,step+1);
                }
            }
            return -1;
        }
    String update(String cur, int i, int j, int p, int q) {
        char[] cs = cur.toCharArray();
        char tmp = cs[i * m + j];
        cs[i * m + j] = cs[p * m + q];
        cs[p * m + q] = tmp;
        return String.valueOf(cs);
    }
}
