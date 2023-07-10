package com.xiaochao.图.BFS;

import java.util.*;

//双向BFS
public class 打开转盘锁 {
    String t, s;
    Set<String> set = new HashSet<>();
    public int openLock(String[] _ds, String _t) {
        s = "0000";
        t = _t;
        if (s.equals(t)) return 0;
        for (String d : _ds) set.add(d);
        if (set.contains(s)) return -1;
        int ans = bfs();
        return ans;
    }
    int bfs() {
        // d1 代表从起点 s 开始搜索（正向）
        // d2 代表从结尾 t 开始搜索（反向）
        Deque<String> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        /*
         * m1 和 m2 分别记录两个方向出现的状态是经过多少次转换而来
         * e.g
         * m1 = {"1000":1} 代表 "1000" 由 s="0000" 替换 1 次字符而来
         * m2 = {"9999":3} 代表 "9999" 由 t="9996" 替换 3 次字符而来
         */
        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        d1.addLast(s);
        m1.put(s, 0);
        d2.addLast(t);
        m2.put(t, 0);

        /*
         * 只有两个队列都不空，才有必要继续往下搜索
         * 如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点
         * e.g.
         * 例如，如果 d1 为空了，说明从 s 搜索到底都搜索不到 t，反向搜索也没必要进行了
         */
        while (!d1.isEmpty() && !d2.isEmpty()) {
            int t = -1;
            if (d1.size() <= d2.size()) {
                t = update(d1, m1, m2);
            } else {
                t = update(d2, m2, m1);
            }
            if (t != -1) return t;
        }
        return -1;
    }
    int update(Deque<String> deque, Map<String, Integer> cur, Map<String, Integer> other) {
        String poll = deque.pollFirst();
        char[] pcs = poll.toCharArray();
        int step = cur.get(poll);
        // 枚举替换哪个字符
        for (int i = 0; i < 4; i++) {
            // 能「正向转」也能「反向转」，这里直接枚举偏移量 [-1,1] 然后跳过 0
            for (int j = -1; j <= 1; j++) {
                if (j == 0) continue;

                // 求得替换字符串 str
                int origin = pcs[i] - '0';
                int next = (origin + j) % 10;
                if (next == -1) next = 9;

                char[] clone = pcs.clone();
                clone[i] = (char)(next + '0');
                String str = String.valueOf(clone);

                if (set.contains(str)) continue;
                if (cur.containsKey(str)) continue;

                // 如果在「另一方向」找到过，说明找到了最短路，否则加入队列
                if (other.containsKey(str)) {
                    return step + 1 + other.get(str);
                } else {
                    deque.addLast(str);
                    cur.put(str, step + 1);
                }
            }
        }
        return -1;
    }
}
/**
 *AStar 算法
 * 可以直接根据本题规则来设计 A* 的「启发式函数」。
 * 比如对于两个状态 a 和 b 可直接计算出「理论最小转换次数」：不同字符的转换成本之和。
 * 需要注意的是：由于我们衡量某个字符 str 的估值是以目标字符串 target 为基准，因此我们只能确保 target 出队时为「距离最短」，
 * 而不能确保中间节点出队时「距离最短」，因此我们不能单纯根据某个节点是否「曾经入队」而决定是否入队，还要结合当前节点的「最小距离」是否被更新而决定是否入队。
 * 这一点十分关键，在代码层面上体现在 map.get(str).step > poll.step + 1 的判断上。
 * 注意：本题用 A* 过了，但通常我们需要先「确保有解」，A* 的启发搜索才会发挥真正价值。而本题，除非 t 本身在 deadends 中，其余情况我们无法很好提前判断「是否有解」。对于无解的情况 A* 效果不如「双向 BFS」。
 */
class Solution {
    class Node {
        String str;
        int val, step;
        /**
         *  str : 对应字符串
         *  val : 估值（与目标字符串 target 的最小转换成本）
         *  step: 对应字符串是经过多少步转换而来
         */
        Node(String _str, int _val, int _step) {
            str = _str;
            val = _val;
            step = _step;
        }
    }
    int f(String str) {
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            int cur = str.charAt(i) - '0', target = t.charAt(i) - '0';
            int a = Math.min(cur, target), b = Math.max(cur, target);
            // 在「正向转」和「反向转」之间取 min
            int min = Math.min(b - a, a + 10 - b);
            ans += min;
        }
        return ans;
    }
    String s, t;
    Set<String> set = new HashSet<>();
    public int openLock(String[] ds, String _t) {
        s = "0000";
        t = _t;
        if (s.equals(t)) return 0;
        for (String d : ds) set.add(d);
        if (set.contains(s)) return -1;

        PriorityQueue<Node> q = new PriorityQueue<>((a,b)->a.val-b.val);
        Map<String, Node> map = new HashMap<>();
        Node root = new Node(s, f(s), 0);
        q.add(root);
        map.put(s, root);
        while (!q.isEmpty()) {
            Node poll = q.poll();
            char[] pcs = poll.str.toCharArray();
            int step = poll.step;
            if (poll.str.equals(t)) return step;
            for (int i = 0; i < 4; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (j == 0) continue;
                    int cur = pcs[i] - '0';
                    int next = (cur + j) % 10;
                    if (next == -1) next = 9;

                    char[] clone = pcs.clone();
                    clone[i] = (char)(next + '0');
                    String str = String.valueOf(clone);

                    if (set.contains(str)) continue;
                    // 如果 str 还没搜索过，或者 str 的「最短距离」被更新，则入队
                    if (!map.containsKey(str) || map.get(str).step > step + 1) {
                        Node node = new Node(str, step + 1 + f(str), step + 1);
                        map.put(str, node);
                        q.add(node);
                    }
                }
            }
        }
        return -1;
    }
}
