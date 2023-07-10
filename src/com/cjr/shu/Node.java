package com.cjr.shu;

import java.util.List;

public class Node {
    public int id;
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    public Node(int _id,int _val, Node _left, Node _right, Node _next,List<Node> _children) {
        id =_id;
        val = _val;
        left = _left;
        right = _right;
        next = _next;
        children = _children;
    }
}
