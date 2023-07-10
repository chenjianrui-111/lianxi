package com.leixing.递归迭代.DFS;

import java.util.Arrays;

/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * 实现词典类 WordDictionary ：
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。
 * word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母
 *
 * 二维数组
 * 使用数组实现，需要预先估算数组大小。
 * 通常估算值会很大，直接使用估算值会 MLE。利用 TrieTrie 会有很多位置被共用，以及合格的测试用例，应该至少做到「查询」调用次数和「插入」调用次数相当，我们可以使用比估算值小的数（往下调整一个数量级），更详细的估算逻辑在 前置芝士 讲过，不再赘述。
 * 使用数组实现，还有一个可优化的地方是使用 static 修饰所有用到的数组，然后在初始化 Solution 的时候做清理工作，这样可以有效避免跑每个样例都创建大数组。
 * 实际测试使用 static 与否执行时间会相差超过一半。
 */
public class WordDictionary {
    static int N=25000;
    static int [][] tr=new int[N][26];
    static boolean [] isWord=new boolean[N];
    static int idx;

    public WordDictionary() {
        for (int i=0;i<idx;i++){
            Arrays.fill(tr[i],0);
        }
        Arrays.fill(isWord,false);
        idx=0;
    }
    public void addWord(String word) {
        int p=0;
        for (int i=0;i<word.length();i++){
            int u=word.charAt(i) - 'a';
            if (tr[p][u] == 0){
                tr[p][u] =++idx;
                p=tr[p][u];
            }
            isWord[p] =true;
        }
    }
    public boolean search(String word) {
        return dfs(word,0,0);
    }
    boolean dfs(String word,int trIdx,int sIdx){
        int n=word.length();
        if (n == sIdx){
            return isWord[trIdx];
        }
        char c=word.charAt(sIdx);
        if (c == '.'){
            for (int j=0;j<26;j++){
                if (tr[trIdx][j] != 0 && dfs(word, tr[trIdx][j], sIdx + 1)) {
                    return true;
                }
            }
            return false;
        }else {
            int u = c - 'a';
            if (tr[trIdx][u] == 0) {
                return false;
            }
            return dfs(word, tr[trIdx][u], sIdx + 1);
        }
    }
}
/**
 *时间复杂度：L 为字符串的最大长度，addWord 操作的复杂度为 O(L)；search 操作的复杂度为 O(C^L)
 * 其中 C 为字符集大小，固定为 26
 * 空间复杂度：静态数组大小固定，复杂度为 O(1e7)
 */
