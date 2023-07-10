package com.xiaochao.递归;

import java.util.LinkedList;
import java.util.List;

public class TrieSet3 {
    // ASCII 码个数
    private static final int R = 256;
    // 当前存在集合中元素个数
    private int size = 0;
    // Trie 树的根节点
    private TrieNode  root = null;

    public static class TrieNode<V>{
        TrieNode<V>[] next = new TrieNode[R];
        boolean isEnd = false;
    }
    public TrieSet3(){};

    /***** 增 ****/
    //在set中添加 key,如果成功则返回true
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

    //在 node开始插入 key[i...]
    private TrieNode add(TrieNode node,String key ,int i){
        if (node == null){
            node = new TrieNode();
        }
        if (i == key.length()){
            node.isEnd = true;
            return node;
        }
        char c = key.charAt(i);
        //从node开始插入 key[i]
        //递归从 node.next 开始插入key[i+1...]
        node.next[c] = add(node.next[c],key,i+1);
        return node;
    }

    public boolean remove(String key){
        if (key == null){
            throw new IllegalArgumentException("key is null");
        }
        if (!contains(key)){
            return false;
        }
        root = remove(root,key,0);
        size--;
        return true;
    }

    private TrieNode remove(TrieNode node,String key ,int i){
        if (i == key.length()){
            node.isEnd = false;
        }else{
            char c =key.charAt(i);
            node.next[c] = remove(node.next[c],key,i+1);
        }
        //检查TrieNode是否是标志位
        if (node.isEnd) return node;
        //检查该TrieNode 后面是否还有后缀
        for (int c = 0;c < R;c++){
            if (node.next[c] != null){
                return node;
            }
        }
        //既不是标志位，也没有后缀，删除该节点
        return null;
    }

    public boolean contains(String key){
        if (key == null){
            throw new IllegalArgumentException("key is null");
        }
        TrieNode x =getNode(root,key);
        //要存在这个路径，并且是字符串末尾
        return  x!= null && x.isEnd;
    }

    public boolean startsWith(String prefix){
        TrieNode x =getNode(root,prefix);
        return x != null;
    }

    public String shortestPrefixOf(String query){
        if (query == null){
            return "";
        }
        TrieNode p =root;
        int i = 0;
        while (i < query.length() && p != null){
            if (p.isEnd){
                break;
            }

            char c = query.charAt(i);
            i++;
            p = p.next[c];
        }
        if (p != null && p.isEnd){
            return query.substring(0,i);
        }
        return "";
    }

    public String longestPrefixOf(String query){

        if (query == null){
            return "";
        }

        TrieNode p =root;
        int i = 0;
        int max_len = 0;
        while (i < query.length() && p != null){
            if (p.isEnd){
                max_len = i;
            }

            char c = query.charAt(i);
            i++;
            p = p.next[c];
        }
        if (p != null && p.isEnd){
            max_len = i;
        }
        return query.substring(0,max_len);
    }

    //搜索前缀为 prefix的所有字符串
    public List<String> keysWithPrefix(String prefix){
        List<String> res =new LinkedList<>();
        //找到匹配 prefix 的那个节点
        TrieNode x =getNode(root,prefix);
        if (x == null){
            return res;
        }
        //DFS遍历多叉树
        traverse(x,new StringBuilder(prefix),res);
        return res;
    }

    //遍历以node 节点为根的 Trie树，找到所有字符串
    private void traverse(TrieNode node, StringBuilder path, List<String> res){
        if (node == null){
            return;
        }
        if (node.isEnd){
            res.add(path.toString());
        }
        //多叉树遍历框架
        for (char c = 0;c < R;c++){
            path.append(c);
            traverse(node.next[c],path,res);
            path.deleteCharAt(path.length() - 1);
        }
    }
    //通配符 . 匹配任意字符
    public List<String> keysWithPattern(String pattern){
        List<String> res =new LinkedList<>();
        traverse(root,new StringBuilder(),pattern,0,res);
        return res;
    }

    //以浓的为根的Trie 树中，匹配pattern[i..]
    private void traverse(TrieNode node, StringBuilder path,String pattern ,int i,List<String> res){
        if (node == null){
            return;
        }
        //匹配完成
        if (i == pattern.length()){
            if (node.isEnd){
                res.add(path.toString());
            }
            return;
        }
        char c =pattern.charAt(i);
        if (c == '.'){
            for (int j = 0; j < R ; j++) {
                path.append(j);
                traverse(node.next[j],path,pattern,i+1,res);
                path.deleteCharAt(path.length() - 1);
            }
        }else {
            path.append(c);
            traverse(node.next[c],path,pattern,i+1,res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    //通配符 . 是否匹配任意字符
    public boolean matches(String pattern){
        if (pattern == null){
            throw new IllegalArgumentException("pattern is null");
        }
        return matches(root,pattern,0);
    }
    //函数定义从 node节点开始匹配pattern[i..]
    private boolean matches(TrieNode node,String pattern,int i){
        if (node == null){
            return false;
        }
        if (i == pattern.length()){
            return node.isEnd;
        }
        char c = pattern.charAt(i);
        if (c == '.'){
            for (int j = 0; j < R; j++) {
                if (matches(node.next[i],pattern,i+1)){
                    return true;
                }
            }
        }
        //没有遇到通配符
        return matches(node.next[c],pattern,i+1);
    }

    // 判断是和否存在前缀为 prefix 的键
    public boolean hasKeyWithPattern(String pattern) {
        // 从 root 节点开始匹配 pattern[0..]
        return hasKeyWithPattern(root, pattern, 0);
    }

    // 函数定义：从 node 节点开始匹配 pattern[i..]，返回是否成功匹配
    private boolean hasKeyWithPattern(TrieNode node, String pattern, int i) {
        if (node == null) {
            // 树枝不存在，即匹配失败
            return false;
        }
        if (i == pattern.length()) {
            // 模式串走到头了，看看匹配到的是否是一个键
            return node.isEnd;
        }
        char c = pattern.charAt(i);
        // 没有遇到通配符
        if (c != '.') {
            // 从 node.children[c] 节点开始匹配 pattern[i+1..]
            return hasKeyWithPattern(node.next[c], pattern, i + 1);
        }
        // 遇到通配符
        for (int j = 0; j < R; j++) {
            // pattern[i] 可以变化成任意字符，尝试所有可能，只要遇到一个匹配成功就返回
            if (hasKeyWithPattern(node.next[j], pattern, i + 1)) {
                return true;
            }
        }
        // 都没有匹配
        return false;
    }

    private TrieNode getNode(TrieNode node,String key){
        TrieNode p =node;
        for (int i = 0; i <key.length() ; i++) {
            char c = key.charAt(i);
            if (p == null){
                return null;
            }
            p = p.next[c];
        }
        return p;
    }

    private int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
