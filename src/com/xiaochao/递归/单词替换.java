package com.xiaochao.递归;

import java.util.List;

/**
 * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
 * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。
 * 如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 你需要输出替换之后的句子。
 * 示例 1：
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 */
public class 单词替换 {
    String replaceWords(List<String> dict, String sentence) {
        // 先将词根都存入 TrieSet
        TrieSet2 set = new TrieSet2();
        for (String key : dict) {
            set.add(key);
        }
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");
        // 处理句子中的单词
        for (int i = 0; i < words.length; i++) {
            // 在 Trie 树中搜索最短词根（最短前缀）
            String prefix = set.shortestPrefixOf(words[i]);
            if (!prefix.isEmpty()) {
                // 如果搜索到了，改写为词根
                sb.append(prefix);
            } else {
                // 否则，原样放回
                sb.append(words[i]);
            }

            if (i != words.length - 1) {
                // 添加单词之间的空格
                sb.append(' ');
            }
        }

        return sb.toString();
    }
}
class TrieSet2 {
    // ASCII 码个数
    private static final int R = 256;
    // 当前存在 Map 中的键值对个数
    private int size = 0;
    // Trie 树的根节点
    private TrieNode root = null;

    public static class TrieNode {
        TrieNode[] next = new TrieNode[R];
        boolean isEnd = false;
    }

    public TrieSet2() {
    }

    public boolean add(String key){
        if (key == null){
            throw new IllegalArgumentException("key is null");
        }
        if (contains(key)){
            return false;
        }
        root = add(root,key,0);
        size++;
        return true;
    }

    private TrieNode add(TrieNode node,String key,int i){
        if (node == null){
            node = new TrieNode();
        }
        if (i == key.length()){
            node.isEnd = true;
            return node;
        }
        char c = key.charAt(i);
        node.next[c] = add(node.next[c],key,i+1);
        return node;
    }

    public boolean contains(String key){
        if (key == null){
            throw new IllegalArgumentException("key is null");
        }
        TrieNode x =getNode(root,key);
        //要存在这个路径，并且是字符串末尾
        return  x!= null && x.isEnd;
    }

    public TrieNode getNode(TrieNode node,String key){
        TrieNode p =node;
        for (int i = 0; i < key.length() ; i++) {
            char c = key.charAt(i);
            if (p == null){
                return null;
            }
            p = p.next[c];
        }
        return p;
    }

    public String shortestPrefixOf(String key) {
        if (key == null) {
            return "";
        }
        TrieNode p = root;
        int i = 0;
        while (i < key.length() && p != null) {
            if (p.isEnd) {
                break;
            }

            char c = key.charAt(i);
            i++;
            p = p.next[c];
        }
        if (p != null && p.isEnd) {
            return key.substring(0, i);
        }
        return "";
    }
}
