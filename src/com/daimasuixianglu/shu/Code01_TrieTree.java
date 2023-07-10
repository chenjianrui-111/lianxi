package com.daimasuixianglu.shu;

import com.cjr.shu.TreeNode;

import java.util.HashMap;

public class Code01_TrieTree {
    public static class TrieNode{
        public int pass;
        public int end;
//      public HashMap<Character, TreeNode> nexts;
        public TrieNode[] nexts;
        public TrieNode(){
            pass=0;
            end=0;
            //nexts[0] == null没有走向'a'的路
            //nexts[0] !=null 有走向'a'的路
            //nexts[25] !=null 有走向'z'的路
            nexts=new TrieNode[26];
//          nexts=new HashMap<>();
//          nexts key 字符 value Node
        }
    }
    public static class Trie{
        private TrieNode root;
        public Trie(){
            root=new TrieNode();
        }
        public void insert(String word){
            if (word == null) return;
            char[] cs=word.toCharArray();
            TrieNode node=root;
            node.pass++;
            int path=0;
            for (int i = 0; i <cs.length ; i++) {//从左往右遍历字符
                path=cs[i] - 'a';//由字符，对应走向哪条路
                if (node.nexts[path] == null){
                    node.nexts[path] =new TrieNode();
                }
                node=node.nexts[path];
                node.pass++;
            }
            node.end++;
        }
        public void delete(String word) {
            if (search(word) != 0) {//确定树中确实有word
                char[]cs=word.toCharArray();
                TrieNode node=root;
                node.pass--;
                int path=0;
                for (int i = 0; i <cs.length ; i++) {
                    path=cs[i] - 'a';
                    if (--node.nexts[path].pass == 0){
                        node.nexts[path] =null;//下级节点的路标空
                        return;
                    }
                    node=node.nexts[path];
                }
                node.end--;
            }
        }
        //确定word中加过几次
        public int search(String word){
            if (word ==null) return 0;
            char cs[]=word.toCharArray();
            TrieNode node=root;
            int index=0;
            for (int i = 0; i <cs.length ; i++) {
                index=cs[i] - 'a';
                if (node.nexts[index] == null){
                    return 0;
                }
                node=node.nexts[index];
            }
            return node.end;
        }

        //所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre){
            if (pre == null) return 0;
            char [] cs=pre.toCharArray();
            TrieNode node=root;
            int index=0;
            for (int i = 0; i <cs.length ; i++) {
                index=cs[i] - 'a';
                if (node.nexts[index] == null){
                    return 0;
                }
                node=node.nexts[index];
            }
            return node.pass;
        }
    }

}
