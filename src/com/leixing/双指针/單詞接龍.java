package com.leixing.双指针;

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
 * 示例 2：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 * 提示：
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 *
 * 解題思路：
 * 根据题意，每次只能替换一个字符，且每次产生的新单词必须在 wordList 出现过。
 *
 * 一个朴素的实现方法是，使用 BFS 的方式求解:
 * 从 beginWord 出发，枚举所有替换一个字符的方案，如果方案存在于 wordList 中，则加入队列中，这样队列中就存在所有替换次数为  的单词。
 * 然后从队列中取出元素，继续这个过程，直到遇到 endWord 或者队列为空为止 ...
 * 同时为了「防止重复枚举到某个中间结果」和「记录每个中间结果是经过多少次转换而来」，我们需要建立一个「哈希表」进行记录。
 * 哈希表的 KV 形式为 {单词:由多少次转换得到}。
 * 当枚举到新单词 str 时，需要先检查是否已经存在与「哈希表」中，如果不存在则更新「哈希表」并将新单词放入队列中。
 * 这样的做法可以确保「枚举到所有由 beginWord 到 endWord 的转换路径」，并且由 beginWord 到 endWord 的「最短转换路径」必然会最先被枚举到。
 *
 * 「双向 BFS」的基本实现思路如下：
 * 创建「两个队列」分别用于两个方向的搜索；
 * 创建「两个哈希表」用于「解决相同节点重复搜索」和「记录转换次数」；
 * 为了尽可能让两个搜索方向“平均”，每次从队列中取值进行扩展时，先判断哪个队列容量较少；
 * 如果在搜索过程中「搜索到对方搜索过的节点」，说明找到了最短路径。
 */
public class 單詞接龍 {

    String s,e;
    Set<String> set=new HashSet<>();

    public int ladderLength(String _s, String _e, List<String> ws){
        s=_s;
        e=_e;

        // 将所有 word 存入 set，如果目标单词不在 set 中，说明无解
        for (String w : ws){
            set.add(w);
        }
        if (!set.contains(e))
            return 0;
        int ans=bfs();
        return ans == -1 ? 0 :ans + 1;
    }

    int bfs(){
        // d1 代表从起点 beginWord 开始搜索（正向）
        // d2 代表从结尾 endWord 开始搜索（反向）

        Deque<String> d1=new ArrayDeque<>(),d2=new ArrayDeque<>();
        //m1 和 m2 分别记录两个方向出现的单词是经过多少次转换而来
        Map<String,Integer> m1=new HashMap<>(),m2=new HashMap<>();
        d1.add(s);
        m1.put(s,0);
        d2.add(e);
        m2.put(e,0);

        //只有两个队列都不空，才有必要继续往下搜索
        //如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点

        while (!d1.isEmpty() && !d2.isEmpty()){
            int t=-1;
            // 为了让两个方向的搜索尽可能平均，优先拓展队列内元素少的方向
            if (d1.size() < d2.size()){
                t=update(d1,m1,m2);
            }else {
                t=update(d2,m2,m1);
            }
            if (t == -1) return  t;
        }
        return -1;
    }

    // update 代表从 deque 中取出一个单词进行扩展，
    // cur 为当前方向的距离字典；other 为另外一个方向的距离字典
    private int update(Deque<String> deque, Map<String, Integer> cur, Map<String, Integer> other) {
        // 获取当前需要扩展的原字符串
        String poll=deque.pollFirst();
        int n=poll.length();

        //枚舉替換原來字符串的哪個字符
        for (int i=0 ;i < n;i++){
            // 枚举将 i 替换成哪个小写字母
            for (int j=0; j< 26; j++){
                //替換後的字符串
                String sub=poll.substring(0,i) + String.valueOf((char)('a'+ j ))+poll.substring(i+1);
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
        return -1;
    }

}
/**
 *这本质其实是一个「所有边权均为 1」最短路问题：将 beginWord 和所有在 wordList 出现过的字符串看做是一个点。每一次转换操作看作产生边权为 1 的边。问题求以 beginWord 为源点，以 endWord 为汇点的最短路径。
 * 借助这个题，我向你介绍了「双向 BFS」，「双向 BFS」可以有效解决「搜索空间爆炸」问题。
 * 对于那些搜索节点随着层数增加呈倍数或指数增长的搜索问题，可以使用「双向 BFS」进行求解
 */
