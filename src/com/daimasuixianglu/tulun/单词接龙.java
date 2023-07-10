package com.daimasuixianglu.tulun;

import java.util.*;

/**
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的「最短转换序列」中的「单词数目」。
 * 如果不存在这样的转换序列，返回 0。
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 思路
 * 双向 BFS
 * 经过分析，BFS 确实可以做，但本题的数据范围较大：
 * 朴素的 BFS 可能会引发「搜索空间爆炸」的问题。
 * 想象一下，如果我们的 wordList 足够丰富（包含了所有单词），对于一个长度为 10 的 beginWord 替换一次字符可以产生 10*25  个新单词（每个替换点可以替换另外 25 个小写字母），第一层就会产生 250 个单词；第二层会产生超过 6*10^4 个新单词 ...
 * 随着层数的加深，这个数字的增速越快，这就是「搜索空间爆炸」问题。
 *「双向 BFS」 可以很好的解决这个问题：
 * 同时从两个方向开始搜索，一旦搜索到相同的值，意味着找到了一条联通起点和终点的最短路径
 * 「双向 BFS」的基本实现思路如下：
 * 创建「两个队列」分别用于两个方向的搜索；
 * 创建「两个哈希表」用于「解决相同节点重复搜索」和「记录转换次数」；
 * 为了尽可能让两个搜索方向“平均”，每次从队列中取值进行扩展时，先判断哪个队列容量较少；
 * 如果在搜索过程中「搜索到对方搜索过的节点」，说明找到了最短路径。
 */
public class 单词接龙 {
    String s,e;
    Set<String> set=new HashSet<>();
    public int ladderLength(String _s, String _e, List<String> ws) {
        s=_s;
        e=_e;
        // 将所有 word 存入 set，如果目标单词不在 set 中，说明无解
        for (String w:ws) set.add(w);
        if (!set.contains(e)) return 0;
        int ans=bfs();
        return ans == -1 ? 0 : ans+1;
    }
    int bfs(){
        // d1 代表从起点 beginWord 开始搜索（正向）
        // d2 代表从结尾 endWord 开始搜索（反向）
        Deque<String> d1=new ArrayDeque<>(),d2=new ArrayDeque<>();
        /*
         * m1 和 m2 分别记录两个方向出现的单词是经过多少次转换而来
         * e.g.
         * m1 = {"abc":1} 代表 abc 由 beginWord 替换 1 次字符而来
         * m1 = {"xyz":3} 代表 xyz 由 endWord 替换 3 次字符而来
         */
        Map<String,Integer> m1=new HashMap<>(),m2=new HashMap<>();
        d1.add(s);
        m1.put(s,0);
        d2.add(e);
        m2.put(e,0);

        /*
         * 只有两个队列都不空，才有必要继续往下搜索
         * 如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点
         * e.g.
         * 例如，如果 d1 为空了，说明从 beginWord 搜索到底都搜索不到 endWord，反向搜索也没必要进行了
         */
        while (!d1.isEmpty() && !d2.isEmpty()) {
            int t = -1;
            // 为了让两个方向的搜索尽可能平均，优先拓展队列内元素少的方向
            if (d1.size() <= d2.size()) {
                t = update(d1, m1, m2);
            } else {
                t = update(d2, m2, m1);
            }
            if (t != -1) return t;
        }
        return -1;
    }
    // update 代表从 deque 中取出一个单词进行扩展，
    // cur 为当前方向的距离字典；other 为另外一个方向的距离字典
    int update(Deque<String> deque, Map<String, Integer> cur, Map<String, Integer> other) {
        // 获取当前需要扩展的原字符串
        String poll = deque.pollFirst();
        int n = poll.length();

        // 枚举替换原字符串的哪个字符 i
        for (int i = 0; i < n; i++) {
            // 枚举将 i 替换成哪个小写字母
            for (int j = 0; j < 26; j++) {
                // 替换后的字符串
                String sub = poll.substring(0, i) + String.valueOf((char)('a' + j)) + poll.substring(i + 1);
                if (set.contains(sub)) {
                    // 如果该字符串在「当前方向」被记录过（拓展过），跳过即可
                    if (cur.containsKey(sub)) continue;

                    // 如果该字符串在「另一方向」出现过，说明找到了联通两个方向的最短路
                    if (other.containsKey(sub)) {
                        return cur.get(poll) + 1 + other.get(sub);
                    } else {
                        // 否则加入 deque 队列
                        deque.addLast(sub);
                        cur.put(sub, cur.get(poll) + 1);
                    }
                }
            }
        }
        return -1;
    }
}
