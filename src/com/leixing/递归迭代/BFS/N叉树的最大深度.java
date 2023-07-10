package com.leixing.递归迭代.BFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 *可以使用 BFS 进行求解：其本质是对多叉树进行层序处理，当 BFS 过程结束，意味着达到最大层数（深度），
 * 所记录的最大层数（深度）即是答案
 */
public class N叉树的最大深度 {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int ans=0;
        Deque<Node> d=new ArrayDeque<>();
        d.addLast(root);
        while (!d.isEmpty()){
            int size=d.size();
            while (size-- > 0){
                Node poll=d.pollFirst();
                for (Node node : poll.children){
                    d.addLast(node);
                }
            }
            ans++;
        }
        return ans;
    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
