package com.xiaochao.树;

//实现前缀树 code模式
public class Main {
    static class Trie {
        Node root;
        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node temp = root;
            for (char c : word.toCharArray()) {
                if (temp.nodes[c - 'a'] == null) {
                    temp.nodes[c - 'a'] = new Node();
                }
                temp = temp.nodes[c - 'a'];
            }
            temp.end = true;
        }

        public boolean search(String word) {
            Node temp = root;
            for (char c : word.toCharArray()) {
                if (temp.nodes[c - 'a'] == null) {
                    return false;
                }
                temp = temp.nodes[c - 'a'];
            }
            return temp.end;
        }

        public boolean startsWith(String prefix) {
            Node temp = root;
            for (char c : prefix.toCharArray()) {
                if (temp.nodes[c - 'a'] == null) {
                    return false;
                }
                temp = temp.nodes[c - 'a'];
            }
            return true;
        }


        class Node {
            boolean end;
            Node[] nodes;

            public Node() {
                this.nodes = new Node[26];
                this.end = false;
            }
        }
    }

}
